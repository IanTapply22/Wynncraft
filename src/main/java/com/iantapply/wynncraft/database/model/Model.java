package com.iantapply.wynncraft.database.model;

import com.iantapply.wynncraft.database.Database;
import com.iantapply.wynncraft.database.DatabaseHelpers;
import com.iantapply.wynncraft.database.table.Column;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
     * @throws SQLException If the statement cannot be executed or close. This will be removed
     * as there will be handles for this error.
     */
    default String migratedVersion() throws SQLException {
        // TODO: Handle SQLException properly in selectRow method
        Model migrationModel = new MigrationModel();
        // Use model UUID as this is its ID and will remain static
        String uuidSearch = "uuid = CAST(? AS UUID)";
        String[] migrationRow = DatabaseHelpers.selectRow(migrationModel.database().connect(true), migrationModel.table(), uuidSearch, this.uuid());

        if (migrationRow == null) return "-1";

        migrationModel.database().disconnect();
        // Pull migration version, the index is the order in which is appears in MigrationModel
        return migrationRow[1];
    }

    /**
     * Migrates the model on a table and creates the columns by default
     * @throws SQLException If the statement cannot be executed or close. This will be removed
     * as there will be handles for this error.
     */
    default void migrate(boolean init) throws SQLException {
        // TODO: Remove SQLException in parallel with createColumn method
        if (init) {
            // If it is initial run, we need a table so we will migrate automatically
            DatabaseHelpers.createTable(this.database().connect(true), this.table(), this.columns());
        } else {
            // Loops over each column and adds the column to the specified table in the model
            // This is restricted to only run on the specified table in the specified database.
            for (Column column : columns()) {
                // Catch clause to catch if there's no default value
                if (column.getDefaultValue() == null) {
                    DatabaseHelpers.createColumn(database().connect(true), table(), column.getName(), column.getType().getType());
                    database().disconnect();
                    continue;
                }

                // Create the column with a default value if there's one present
                DatabaseHelpers.createColumn(database().connect(true), table(), column.getName(), column.getType().getType(), column.getDefaultValue());
                database().disconnect();
            }
        }

        // Update migration status
        MigrationModel migrationModel = new MigrationModel();
        Connection connection = migrationModel.database().connect(true);
        DatabaseHelpers.insertRow(connection, migrationModel.table(), new String[]{"uuid", "version"}, new String[]{this.uuid(), this.version()});
    }

    /**
     * Reverts the migration to the specified state
     * <p>
     * This has no action by default as it can be intrusive to assume actions.
     */
    // TODO: Handle reversion on failure of migration
    default void revert() {
        Logger.log(LoggingLevel.INFO, "No reversion is available. Please set one.");
    }
}
