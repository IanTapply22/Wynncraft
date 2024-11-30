package com.iantapply.wynncraft.database.model;

import com.iantapply.wynncraft.database.Database;
import com.iantapply.wynncraft.database.DatabaseHelpers;
import com.iantapply.wynncraft.database.table.Column;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents a model that contains various data on an object
 */
public interface Model {

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
     * Migrates the model on a table and creates the columns by default
     * @throws SQLException If the statement cannot be executed or close. This will be removed
     * as there will be handles for this error.
     */
    default void migrate() throws SQLException {
        // TODO: Remove SQLException in parallel with createColumn method
        // Loops over each column and adds the column to the specified table in the model
        // This is restricted to only run on the specified table in the specified database.
        for (Column column : columns()) {
            // Catch clause to catch if there's no default value
            if (column.getDefaultValue() == null) {
                DatabaseHelpers.createColumn(database().connection(), table(), column.getName(), column.getType().getType());
                continue;
            }

            // Create the column with a default value if there's one present
            DatabaseHelpers.createColumn(database().connection(), table(), column.getName(), column.getType().getType(), column.getDefaultValue());
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
