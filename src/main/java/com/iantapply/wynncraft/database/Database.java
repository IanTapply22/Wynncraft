package com.iantapply.wynncraft.database;

import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Represents a database, its information, and the connection involved
 * with the object
 */
public abstract class Database {
    Connection connection;

    /**
     * The information associated with the database
     * @return The information as a DatabaseInformation object
     */
    public abstract DatabaseInformation databaseInformation();

    /**
     * The connection that is used to execute queries on the database table
     * @return A Connection object containing a connection to the database session
     */
    public Connection connection() {
        return this.connection;
    }

    /**
     * Connects to the database
     */
    public void connect() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            Logger.log(LoggingLevel.ERROR, e.getMessage());
        }

        // If the connection is already established, return the current connection
        if (this.connection != null) return;

        // Build the database connection URL based off of the database information
        String connectionUrl = DatabaseHelpers.buildDatabaseUrl(databaseInformation());

        // Attempt to connect
        try {
            this.connection = DriverManager.getConnection(connectionUrl, databaseInformation().getUsername(), databaseInformation().getPassword());
        } catch (SQLException e) {
            Logger.log(LoggingLevel.ERROR, "Failed to connect to database: " + e.getMessage());
            Logger.log(LoggingLevel.INFO, "Attempting to create database...");

            String fallbackUrl = DatabaseHelpers.buildDatabaseUrl(databaseInformation(), true);

            try (Connection fallbackConnection = DriverManager.getConnection(fallbackUrl, databaseInformation().getUsername(), databaseInformation().getPassword())) {
                DatabaseHelpers.createDatabase(fallbackConnection, databaseInformation().getName());
            } catch (SQLException fallbackException) {
                Logger.log(LoggingLevel.ERROR, "Failed to create database: " + fallbackException.getMessage());
                throw new RuntimeException("Cannot proceed without the database.", fallbackException);
            }

            // Retry connecting to the newly created database
            try {
                this.connection = DriverManager.getConnection(connectionUrl, databaseInformation().getUsername(), databaseInformation().getPassword());
            } catch (SQLException retryException) {
                Logger.log(LoggingLevel.ERROR, "Failed to reconnect after creating database: " + retryException.getMessage());
                throw new RuntimeException("Cannot connect to the newly created database.", retryException);
            }
        }

        Logger.log(LoggingLevel.INFO, "Connected to " + databaseInformation().getDisplayName() + " database");
    }

    /**
     * Disconnects from the database
     */
    public void disconnect() {
        // If the connection is null or already closed, return
        if (this.connection() == null) return;
        try {
            if (this.connection().isClosed()) return;
        } catch (SQLException e) {
            Logger.log(LoggingLevel.ERROR, "Failed to check if database is closed: " + e.getMessage());
        }

        try {
            this.connection().close();
        } catch (SQLException e) {
            Logger.log(LoggingLevel.ERROR, "Failed to close database connection: " + e.getMessage());
        }

        Logger.log(LoggingLevel.INFO, "Disconnected from" + this.databaseInformation().getDisplayName() + "database");
    }

    /**
     * Initializes the database
     */
    public abstract void initialize();
}
