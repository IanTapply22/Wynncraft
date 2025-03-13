package com.iantapply.wynncraft.database.database;

import com.iantapply.wynncraft.database.PGSQLDatabaseInformation;
import com.iantapply.wynncraft.database.pgsql.PGSQLDatabase;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * An example implementation of a database object that connects to a database
 */
@Setter @Getter
public class ExampleDatabase extends PGSQLDatabase {

    /**
     * The information associated with the database
     *
     * @return The information as a DatabaseInformation object
     */
    @Override
    public PGSQLDatabaseInformation databaseInformation() {
        return new PGSQLDatabaseInformation("localhost", "1111", "example", "Example", "root", "");
    }

    /**
     * Initializes the database
     */
    @Override
    public void initialize() {
        Logger.log(LoggingLevel.INFO, "Initializing the" + databaseInformation().getDisplayName() +"database...");
    }
}
