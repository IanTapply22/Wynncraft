package com.iantapply.wynncraft.database.database;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.configuration.ConfigurationCore;
import com.iantapply.wynncraft.database.Database;
import com.iantapply.wynncraft.database.DatabaseInformation;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MigrationsDatabase extends Database {
    private final ConfigurationCore configurationCore = Wynncraft.getInstance().getConfigurationCore();

    /**
     * The information associated with the database
     *
     * @return The information as a DatabaseInformation object
     */
    @Override
    public DatabaseInformation databaseInformation() {
        return new DatabaseInformation(this.configurationCore.getString("MIGRATIONS_DATABASE_ADDRESS"),
                                        this.configurationCore.getString("MIGRATIONS_DATABASE_PORT"),
                                        this.configurationCore.getString("MIGRATIONS_DATABASE_NAME"),
                                        this.configurationCore.getString("MIGRATIONS_DATABASE_DISPLAY_NAME"),
                                        this.configurationCore.getString("MIGRATIONS_DATABASE_USERNAME"),
                                        this.configurationCore.getString("MIGRATIONS_DATABASE_PASSWORD"));
    }

    /**
     * Initializes the database
     */
    @Override
    public void initialize() {
        Logger.log(LoggingLevel.INFO, "Initializing the" + databaseInformation().getDisplayName() +"database...");
    }
}
