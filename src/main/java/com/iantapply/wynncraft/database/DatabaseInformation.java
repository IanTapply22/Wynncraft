package com.iantapply.wynncraft.database;

import lombok.Getter;
import lombok.Setter;

/**
 * Contains a plethora of information needed to establish and
 * create a connection to a database
 */
@Getter @Setter
public class DatabaseInformation {
    /** General information **/
    private String displayName;

    /** Connection related information */
    private String urlPrefix = "jdbc:mysql://";
    private String host = "localhost";
    private String port = "3306";

    /** Database/permission related information */
    private String name;
    private String username = "root";
    private String password = "";

    /**
     * Creates a new ConnectionInformation object with the given connection information for a database
     * @param urlPrefix The prefix of the database URL (e.g. jdbc:mysql://)
     * @param host The host of the database
     * @param port The port of the database
     * @param name The name of the database
     * @param displayName The display name of the database
     * @param username The username to connect to the database
     * @param password The password to connect to the database
     */
    public DatabaseInformation(String urlPrefix, String host, String port, String name, String displayName, String username, String password) {
        this.urlPrefix = urlPrefix;
        this.host = host;
        this.port = port;
        this.name = name;
        this.displayName = displayName;
        this.username = username;
        this.password = password;
    }

    /**
     * Creates a new ConnectionInformation object with the given connection information for a database
     * @param host The host of the database
     * @param port The port of the database
     * @param name The name of the database
     * @param displayName The display name of the database
     * @param username The username to connect to the database
     * @param password The password to connect to the database
     */
    public DatabaseInformation(String host, String port, String name, String displayName, String username, String password) {
        this.host = host;
        this.port = port;
        this.name = name;
        this.displayName = displayName;
        this.username = username;
        this.password = password;
    }

    /**
     * Creates a new ConnectionInformation object with the given connection information for a database
     * @param host The host of the database
     * @param name The name of the database
     * @param displayName The display name of the database
     * @param username The username to connect to the database
     * @param password The password to connect to the database
     */
    public DatabaseInformation(String host, String name, String displayName, String username, String password) {
        this.host = host;
        this.name = name;
        this.displayName = displayName;
        this.username = username;
        this.password = password;
    }

    /**
     * Creates a new ConnectionInformation object with the given connection information for a database
     * @param name The name of the database
     * @param displayName The display name of the database
     * @param username The username to connect to the database
     * @param password The password to connect to the database
     */
    public DatabaseInformation(String name, String displayName, String username, String password) {
        this.name = name;
        this.displayName = displayName;
        this.username = username;
        this.password = password;
    }

    /**
     * Creates a new ConnectionInformation object with the given connection information for a database
     * @param name The name of the database
     * @param displayName The display name of the database
     */
    public DatabaseInformation(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }
}
