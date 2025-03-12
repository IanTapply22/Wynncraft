package com.iantapply.wynncraft.database.database;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.configuration.ConfigurationCore;
import com.iantapply.wynncraft.database.PGSQLDatabaseInformation;
import com.iantapply.wynncraft.database.pgsql.PGSQLDatabase;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;

/**
 * The Wynncraft database object that connects to the Wynncraft database
 * and provides a connection to the data available in the database
 */
public class WynncraftDatabase extends PGSQLDatabase {
    private final ConfigurationCore configurationCore = Wynncraft.getInstance().getConfigurationCore();

    /**
     * The information associated with the database
     *
     * @return The information as a DatabaseInformation object
     */
    @Override
    public PGSQLDatabaseInformation databaseInformation() {
        return new PGSQLDatabaseInformation(this.configurationCore.getString("WYNNCRAFT_DATABASE_ADDRESS"),
                                        this.configurationCore.getString("WYNNCRAFT_DATABASE_PORT"),
                                        this.configurationCore.getString("WYNNCRAFT_DATABASE_NAME"),
                                        this.configurationCore.getString("WYNNCRAFT_DATABASE_DISPLAY_NAME"),
                                        this.configurationCore.getString("WYNNCRAFT_DATABASE_USERNAME"),
                                        this.configurationCore.getString("WYNNCRAFT_DATABASE_PASSWORD"));
    }

    /**
     * Initializes the database
     */
    @Override
    public void initialize() {
        Logger.log(LoggingLevel.INFO, "Initializing the" + databaseInformation().getDisplayName() + "database...");
    }
}
