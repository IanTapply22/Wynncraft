package com.iantapply.wynncraft.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Represents a database, its information, and the connection involved
 * with the object
 */
public abstract class Database {

    /**
     * The information associated with the database
     * @return The information as a DatabaseInformation object
     */
    public abstract DatabaseInformation databaseInformation();

    /**
     * Connects to the database
     * @return The connection instance to the database
     */
    public abstract Connection connect() throws SQLException;

    /**
     * Disconnects from the database
     */
    public abstract void disconnect() throws SQLException;

    /**
     * Initializes the database
     */
    public abstract void initialize();
}
