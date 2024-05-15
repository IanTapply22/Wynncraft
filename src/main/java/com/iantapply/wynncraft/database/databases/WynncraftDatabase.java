package com.iantapply.wynncraft.database.databases;

import com.iantapply.wynncraft.database.Database;
import com.iantapply.wynncraft.database.DatabaseHelpers;
import com.iantapply.wynncraft.database.DatabaseInformation;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The Wynncraft database object that connects to the Wynncraft database
 * and provides a connection to the data available in the database
 */
@Setter @Getter
public class WynncraftDatabase extends Database {
    Connection connection;

    /**
     * The information associated with the database
     *
     * @return The information as a DatabaseInformation object
     */
    @Override
    public DatabaseInformation databaseInformation() {
        return new DatabaseInformation("localhost", "3306", "wynncraft", "Wynncraft Core", "root", "");
    }

    /**
     * Connects to the database
     *
     * @return The connection instance to the database
     */
    @Override
    public Connection connect() throws SQLException {
        // If the connection is already established, return the current connection
        if (getConnection() != null) return getConnection();

        // Build the database connection URL based off of the database information
        String connectionUrl = DatabaseHelpers.buildDatabaseUrl(databaseInformation());

        // Set the connection instance of the database
        this.setConnection(DriverManager.getConnection(connectionUrl, databaseInformation().getUsername(), databaseInformation().getPassword()));

        Logger.log(LoggingLevel.INFO, "Connected to" + databaseInformation().getDisplayName() + "database");

        // Return the new connection instance
        return getConnection();
    }

    /**
     * Disconnects from the database
     */
    @Override
    public void disconnect() throws SQLException {
        // If the connection is null or already closed, return
        if (this.getConnection() == null) return;
        if (this.getConnection().isClosed()) return;

        this.getConnection().close();

        Logger.log(LoggingLevel.INFO, "Disconnected from" + databaseInformation().getDisplayName() + "database");
    }

    /**
     * Initializes the database
     */
    @Override
    public void initialize() {
        Logger.log(LoggingLevel.INFO, "Initializing the" + databaseInformation().getDisplayName() + "database...");
    }
}
