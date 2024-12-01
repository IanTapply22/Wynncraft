package com.iantapply.wynncraft.database.database;

import com.iantapply.wynncraft.database.Database;
import com.iantapply.wynncraft.database.DatabaseInformation;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MigrationsDatabase extends Database {

    /**
     * The information associated with the database
     *
     * @return The information as a DatabaseInformation object
     */
    @Override
    public DatabaseInformation databaseInformation() {
        return new DatabaseInformation("localhost", "5432", "migrations", "Migrations", "root", "wynncraft");
    }

    /**
     * Initializes the database
     */
    @Override
    public void initialize() {
        Logger.log(LoggingLevel.INFO, "Initializing the" + databaseInformation().getDisplayName() +"database...");
    }
}
