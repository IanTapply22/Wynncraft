package com.iantapply.wynncraft.database.model;

import com.iantapply.wynncraft.database.Database;
import com.iantapply.wynncraft.database.DatabaseHelpers;
import com.iantapply.wynncraft.database.table.Column;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a model that contains various data on an object
 */
public interface Model {

    /**
     * The UUID of the model. This does not change
     * @return The UUID of the model
     */
    // TODO: Automate UUID assignments for models
    String uuid();

    /**
     * The database, or table that the model is the child of. This is also referred to as
     * the parent of the model.
     * @return The Database object of the table
     */
    Database database();

    /**
     * The table the migration will be run on inside the parent
     * @return The name of the table in the parent database
     */
    String table();

    /**
     * The columns that will appear in order and in the specified parent
     * @return An arraylist of column objects the contain the information of the column
     */
    ArrayList<Column> columns();

    /**
     * The name of the model, used in the initialization of the database and debugging
     * @return The name of the model as a string
     */
    String name();

    /**
     * The version of the model, should be updated when changes have been
     * made to the model that require a database migration.
     * <p>
     * The version should be in the format of "major.minor.patch" (e.g. "7.7.2")
     * @return The version of the model as a string
     */
    String version();

    /**
     * The method used to populate a row of data with this model in the table
     */
    void populate() throws SQLException;

    /**
     * Used to retrieve a value from the DB in relation to the primary key
     */
    Object getModelValue(String key) throws SQLException;

    /**
     * Whether the model should be migrated automatically upon creation or a modification.
     * @return A boolean representing if it should be able to automatically migrate. This
     * is by default false.
     */
    default boolean automaticallyMigrate() {
        return false;
    }

    /**
     * Gets the migrated version that has been last used for a model
     * @return The version as a String type. -1 is returned upon no row being found.
     */
    default String migratedVersion() {
        Model migrationModel = new MigrationModel();
        String uuidSearch = "uuid = CAST(? AS UUID)";
        try {
            String[] migrationRow = DatabaseHelpers.selectRowWithLatestCreatedAt(
                    migrationModel.database().connect(true),
                    migrationModel.table(),
                    uuidSearch,
                    this.uuid()
            );
            if (migrationRow == null) return "-1";

            migrationModel.database().disconnect();
            return migrationRow[1];
        } catch (SQLException e) {
            Logger.log(LoggingLevel.ERROR, String.format("Unable to select row for migrated version check: %s", e.getMessage()));
        }

        return "-1";
    }

    /**
     * Migrates the model on a table and creates the columns by default
     *
     * @param init Whether this is the initial round of migration and will migrate the model
     */
    default void migrate(boolean init) {
        if (init) {
            // If it is initial run, we need a table so we will migrate automatically
            try {
                DatabaseHelpers.createTable(this.database().connect(true), this.table(), this.columns());
            } catch (Exception e) {
                Logger.log(LoggingLevel.ERROR, String.format("Creating table for migration failed. Skipping migration with error: %s", e.getMessage()));
                this.database().disconnect();
                return;
            }
        } else {
            // Loops over each column and adds the column to the specified table in the model
            // This is restricted to only run on the specified table in the specified database.
            for (Column column : columns()) {
                // Skip if column already exists;
                // In the case you want to change the type and change the data in a column you must use a manual query
                // TODO: Migrations support for custom data manipulation
                try {
                    if (DatabaseHelpers.checkColumnExists(this.database().connect(true), this.table(), column.getName())) continue;
                } catch (SQLException e) {
                    Logger.log(LoggingLevel.ERROR, String.format("Failed to check if column exists for database table: %s", e.getMessage()));
                    this.database().disconnect();
                    return;
                }

                // Catch clause to catch if there's no default value
                if (column.getDefaultValue() == null) {
                    try {
                        DatabaseHelpers.createColumn(database().connect(true), table(), column.getName(), column.getType().getType());
                    } catch (SQLException e) {
                        Logger.log(LoggingLevel.ERROR, String.format("Failed to create column: %s", e.getMessage()));
                        this.database().disconnect();
                        continue;
                    }
                } else {
                    try {
                        DatabaseHelpers.createColumn(database().connect(true), table(), column.getName(), column.getType().getType(), column.getDefaultValue());
                    } catch (SQLException e) {
                        Logger.log(LoggingLevel.ERROR, String.format("Failed to create column with default value: %s", e.getMessage()));
                        this.database().disconnect();
                        continue;
                    }
                }

                // Create the column with a default value if there's one present
                try {
                    DatabaseHelpers.createColumn(database().connect(true), table(), column.getName(), column.getType().getType(), column.getDefaultValue());
                } catch (SQLException e) {
                    Logger.log(LoggingLevel.ERROR, String.format("Failed to create column for table: %s", e.getMessage()));
                    database().disconnect();
                }
                database().disconnect();
            }
        }

        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        try {
            // Update migration status in migrations table
            MigrationModel migrationModel = new MigrationModel(UUID.fromString(this.uuid()), this.version(), createdAt);
            migrationModel.populate();
        } catch (SQLException e) {
            Logger.log(LoggingLevel.ERROR, String.format("Failed to insert row into database: %s", e.getMessage()));
        }
    }

    /**
     * Reverts the migration to the specified state
     * <p>
     * This has no action by default as it can be intrusive to assume actions.
     */
    default void revert() {
        Logger.log(LoggingLevel.INFO, "No reversion is available. Please set one.");
    }
}
