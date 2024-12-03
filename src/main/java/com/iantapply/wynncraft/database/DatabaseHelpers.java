package com.iantapply.wynncraft.database;

import com.iantapply.wynncraft.database.table.Column;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;

import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * A collection of helper methods for database related tasks including
 * but not limited to building URLs, finishing queries, and interacting
 * with tables
 * <p>
 * Note: All warning in regard to unsafe SQL statements can be ignored, we are sanitizing
 * our queries before execution and none of the helpers are faced towards the consumers.
 */
// TODO: Move safeget to util and clean up methods here
public class DatabaseHelpers {

    /**
     * Builds a database URL from the given database information
     * @param databaseInformation The information to build the URL from
     * @return The built URL as a string
     */
    public static String buildDatabaseUrl(DatabaseInformation databaseInformation) {
        return String.format("%s%s:%s/%s", databaseInformation.getUrlPrefix(), databaseInformation.getHost(), databaseInformation.getPort(), databaseInformation.getName());
    }

    /**
     * Builds a database URL from the given database information and a flag
     * of if the database is the fallback database.
     * @param databaseInformation The information to build the URL from
     * @param fallbackDatabase Whether the database is the fallback database
     * @return The built URL as a string
     */
    public static String buildDatabaseUrl(DatabaseInformation databaseInformation, boolean fallbackDatabase) {
        String databaseName = fallbackDatabase ? databaseInformation.getFallbackName() : databaseInformation.getName();
        return String.format("%s%s:%s/%s", databaseInformation.getUrlPrefix(), databaseInformation.getHost(), databaseInformation.getPort(), databaseName);
    }

    /**
     * Finishes a query by executing the statement and closing it without returning a result
     * @param queryStatement The statement to finish
     */
    public static void finishQueryWithoutResult(PreparedStatement queryStatement) {
        try {
            queryStatement.execute();
            queryStatement.close();
        } catch (SQLException e) {
            Logger.log(LoggingLevel.ERROR, e.getMessage());
        }
    }

    /**
     * Finishes a query by executing the statement and closing it
     * @param queryStatement The statement to finish
     * @return The result of the query. An empty array if there is an SQL error that occurs
     */
    public static String[] finishQuery(PreparedStatement queryStatement) throws SQLException {
        ResultSet resultSet = queryStatement.executeQuery();
        String[] result = new String[resultSet.getMetaData().getColumnCount()];

        if (resultSet.next()) {
            for (int i = 0; i < result.length; i++) {
                result[i] = resultSet.getString(i + 1);
            }
        }

        resultSet.close();
        queryStatement.close();
        return result;
    }

    /**
     * Executes a query on the provided database name and with the provided query
     * @param databaseClassName The name of the Java class that appears in the database subfolder.
     *                          This is the name of the class itself, not the name of the physical database as there can be duplicates.
     * @param query The query that is executed on the specified database
     */
    public static void executeQuery(String databaseClassName, String query) {
        if (databaseClassName == null || databaseClassName.isEmpty() || !databaseClassName.matches("^[a-zA-Z0-9_]+$")) {
            Logger.log(LoggingLevel.ERROR, "Invalid database class name.");
            return;
        }

        try {
            Class<?> databaseClass = Class.forName(String.format("com.iantapply.wynncraft.database.database.%s", databaseClassName));

            if (!Database.class.isAssignableFrom(databaseClass)) {
                Logger.log(LoggingLevel.ERROR, "The provided class does not extend the Database base class.");
                return;
            }

            Constructor<?> constructor = databaseClass.getDeclaredConstructor();
            Database databaseInstance = (Database) constructor.newInstance();

            try (Connection connection = databaseInstance.connection();
                 Statement statement = connection.createStatement()) {

                if (query == null || query.trim().isEmpty()) {
                    Logger.log(LoggingLevel.ERROR, "The provided query is empty or null.");
                    return;
                }

                statement.execute(query);

            } catch (SQLException e) {
                Logger.log(LoggingLevel.ERROR, "Failed to execute query: " + e.getMessage());
            }

        } catch (ClassNotFoundException e) {
            Logger.log(LoggingLevel.ERROR, "The database class could not be found! Be sure to match a class name in the database folder.");
        } catch (ReflectiveOperationException e) {
            Logger.log(LoggingLevel.ERROR, "Failed to instantiate the database class: " + e.getMessage());
        }
    }

    /**
     * Creates a database with the given database name.
     *
     * @param connection The database connection to use.
     * @param database   The name of the database to create.
     * @throws SQLException If an SQL error occurs.
     */
    public static void createDatabase(Connection connection, String database) throws SQLException {
        Pattern validIdentifierPattern = Pattern.compile("^[a-zA-Z0-9_]+$");

        if (!validIdentifierPattern.matcher(database).matches()) {
            throw new IllegalArgumentException("Database name contains invalid characters.");
        }
        
        String query = "CREATE DATABASE " + database;

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }

    /**
     * Drops a database with the given database name.
     *
     * @param connection The database connection to use.
     * @param database   The name of the database to drop.
     * @throws SQLException If an SQL error occurs.
     */
    public static void dropDatabase(Connection connection, String database) throws SQLException {
        Pattern validIdentifierPattern = Pattern.compile("^[a-zA-Z0-9_]+$");

        if (!validIdentifierPattern.matcher(database).matches()) {
            throw new IllegalArgumentException("Database name contains invalid characters.");
        }

        String query = "DROP DATABASE " + database;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }

    /**
     * Inserts a row into a table with the given columns and values.
     *
     * @param connection The database connection to use.
     * @param table      The name of the table to insert the row into.
     * @param columns    The columns to insert the values into.
     * @param values     The values to insert into the columns.
     * @throws SQLException If an SQL error occurs.
     */
    public static void insertRow(Connection connection, String table, String[] columns, Object[] values) throws SQLException {
        if (columns == null || values == null || columns.length != values.length || columns.length == 0) {
            throw new IllegalArgumentException("Columns and values must be non-null and have the same non-zero length.");
        }

        Pattern validIdentifierPattern = Pattern.compile("^[a-zA-Z0-9_]+$");

        if (!validIdentifierPattern.matcher(table).matches()) {
            throw new IllegalArgumentException("Table name contains invalid characters.");
        }

        for (String column : columns) {
            if (!validIdentifierPattern.matcher(column).matches()) {
                throw new IllegalArgumentException("Column name contains invalid characters: " + column);
            }
        }

        String columnString = String.join(", ", columns);
        String valuePlaceholderString = String.join(", ", Collections.nCopies(values.length, "?"));
        String query = "INSERT INTO " + table + " (" + columnString + ") VALUES (" + valuePlaceholderString + ")";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            statement.executeUpdate();
        }
    }

    /**
     * Updates a row in a table with the given columns and values.
     *
     * @param connection The database connection to use.
     * @param table      The table to update the row in.
     * @param columns    The columns to update.
     * @param values     The values to update the columns with.
     * @param condition  The condition to check for when updating a row (e.g., "id = ?").
     * @param conditionValues The values to bind to the condition's placeholders.
     * @throws SQLException If an SQL error occurs.
     */
    public static void updateRow(Connection connection, String table, String[] columns, Object[] values, String condition, Object... conditionValues) throws SQLException {
        if (columns == null || values == null || columns.length != values.length || columns.length == 0) {
            throw new IllegalArgumentException("Columns and values must be non-null and have the same non-zero length.");
        }

        Pattern validIdentifierPattern = Pattern.compile("^[a-zA-Z0-9_]+$");

        if (!validIdentifierPattern.matcher(table).matches()) {
            throw new IllegalArgumentException("Table name contains invalid characters.");
        }

        for (String column : columns) {
            if (!validIdentifierPattern.matcher(column).matches()) {
                throw new IllegalArgumentException("Column name contains invalid characters: " + column);
            }
        }

        StringBuilder setClause = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            setClause.append(columns[i]).append(" = ?");
            if (i < columns.length - 1) {
                setClause.append(", ");
            }
        }

        String query = "UPDATE " + table + " SET " + setClause + " WHERE " + condition;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int parameterIndex = 1;
            for (Object value : values) {
                statement.setObject(parameterIndex++, value);
            }

            for (Object value : conditionValues) {
                statement.setObject(parameterIndex++, value);
            }

            statement.executeUpdate();
        }
    }

    /**
     * Deletes a row from a table with the given condition.
     *
     * @param connection The database connection to use.
     * @param table      The table to delete the row from.
     * @param condition  The condition to check for when deleting a row (e.g., "id = ?").
     * @param conditionValues The values to bind to the condition's placeholders.
     * @throws SQLException If an SQL error occurs.
     */
    public static void deleteRow(Connection connection, String table, String condition, Object... conditionValues) throws SQLException {
        Pattern validIdentifierPattern = Pattern.compile("^[a-zA-Z0-9_]+$");

        if (!validIdentifierPattern.matcher(table).matches()) {
            throw new IllegalArgumentException("Table name contains invalid characters.");
        }

        String query = "DELETE FROM " + table + " WHERE " + condition;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < conditionValues.length; i++) {
                statement.setObject(i + 1, conditionValues[i]);
            }

            statement.executeUpdate();
        }
    }

    /**
     * Creates a table with the given columns and types.
     *
     * @param connection The database connection to use.
     * @param table      The name of the table to create.
     * @param columns    The ArrayList of Column objects containing column definitions.
     * @throws SQLException If an SQL error occurs.
     */
    public static void createTable(Connection connection, String table, ArrayList<Column> columns) throws SQLException {
        if (columns == null || columns.isEmpty()) {
            throw new IllegalArgumentException("The columns list cannot be null or empty.");
        }

        Pattern validIdentifierPattern = Pattern.compile("^[a-zA-Z0-9_]+$");

        if (!validIdentifierPattern.matcher(table).matches()) {
            throw new IllegalArgumentException("Table name contains invalid characters.");
        }

        StringBuilder columnString = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);

            if (!validIdentifierPattern.matcher(column.getName()).matches()) {
                throw new IllegalArgumentException("Column name contains invalid characters: " + column.getName());
            }

            columnString.append(column.getName()).append(" ").append(column.getType());
            if (i < columns.size() - 1) {
                columnString.append(", ");
            }
        }

        String query = "CREATE TABLE IF NOT EXISTS " + table + " (" + columnString + ")";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        }
    }

    /**
     * Drops a table from the database.
     *
     * @param connection The database connection to use.
     * @param table      The name of the table to drop.
     * @throws SQLException If an SQL error occurs.
     */
    public static void dropTable(Connection connection, String table) throws SQLException {
        Pattern validIdentifierPattern = Pattern.compile("^[a-zA-Z0-9_]+$");

        if (!validIdentifierPattern.matcher(table).matches()) {
            throw new IllegalArgumentException("Table name contains invalid characters.");
        }

        String query = "DROP TABLE IF EXISTS " + table;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }

    /**
     * Checks if a table exists in a database connection
     *
     * @param connection The database connection
     * @param table The name of the table to check for existence
     * @return A boolean indicating if the table exists. Returns false upon SQL error.
     */
    public static boolean checkTableExists(Connection connection, String table) throws SQLException {
        // Use database metadata to check for the table's existence
        try (ResultSet tables = connection.getMetaData().getTables(null, null, table, null)) {
            return tables.next(); // Returns true if a table with the specified name exists
        }
    }

    /**
     * Selects an entire row from a table with the given condition.
     *
     * @param connection      The database connection to use.
     * @param table           The table to select the row from.
     * @param condition       The condition to check for when selecting a row (e.g., "uuid = ?").
     * @param conditionValues The values to bind to the condition's placeholders.
     * @return The row's data in a String array, or null if no row is found.
     * @throws SQLException If an SQL error occurs.
     */
    public static String[] selectRow(Connection connection, String table, String condition, Object... conditionValues) throws SQLException {
        Pattern validIdentifierPattern = Pattern.compile("^[a-zA-Z0-9_]+$");

        if (!validIdentifierPattern.matcher(table).matches()) {
            throw new IllegalArgumentException("Table name contains invalid characters.");
        }

        String query = "SELECT * FROM " + table + " WHERE " + condition;

        try (PreparedStatement queryStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < conditionValues.length; i++) {
                queryStatement.setObject(i + 1, conditionValues[i]);
            }

            try (ResultSet resultSet = queryStatement.executeQuery()) {
                if (resultSet.next()) {
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    String[] row = new String[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        row[i] = resultSet.getString(i + 1); // ResultSet columns are 1-based
                    }
                    return row;
                }
            }
        }

        return null;
    }

    /**
     * Selects an entire and latest row from a table with the given condition.
     *
     * @param connection      The database connection to use.
     * @param table           The table to select the row from.
     * @param condition       The condition to check for when selecting a row (e.g., "uuid = ?").
     * @param conditionValues The values to bind to the condition's placeholders.
     * @return The row's data in a String array, or null if no row is found.
     * @throws SQLException If an SQL error occurs.
     */
    public static String[] selectRowWithLatestCreatedAt(Connection connection, String table, String condition, Object... conditionValues) throws SQLException {
        Pattern validIdentifierPattern = Pattern.compile("^[a-zA-Z0-9_]+$");

        if (!validIdentifierPattern.matcher(table).matches()) {
            throw new IllegalArgumentException("Table name contains invalid characters.");
        }

        // Add ORDER BY created_at DESC and LIMIT 1 to fetch the latest row
        String query = "SELECT * FROM " + table + " WHERE " + condition + " ORDER BY created_at DESC LIMIT 1";

        try (PreparedStatement queryStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < conditionValues.length; i++) {
                queryStatement.setObject(i + 1, conditionValues[i]);
            }

            try (ResultSet resultSet = queryStatement.executeQuery()) {
                if (resultSet.next()) {
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    String[] row = new String[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        row[i] = resultSet.getString(i + 1);
                    }
                    return row;
                }
            }
        }

        return null;
    }

    /**
     * Creates a column in a table with the given type.
     *
     * @param connection The database connection to use.
     * @param table      The table to create the column in.
     * @param column     The name of the column to create.
     * @param type       The type of the column to create (e.g., "INT", "VARCHAR(255)").
     * @throws SQLException If an SQL error occurs.
     */
    public static void createColumn(Connection connection, String table, String column, String type) throws SQLException {
        Pattern validIdentifierPattern = Pattern.compile("^[a-zA-Z0-9_]+$");

        if (!validIdentifierPattern.matcher(table).matches() || !validIdentifierPattern.matcher(column).matches()) {
            throw new IllegalArgumentException("Table or column name contains invalid characters.");
        }

        String query = "ALTER TABLE " + table + " ADD COLUMN " + column + " " + type;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }

    /**
     * Checks if a column in a table exists and returns a boolean
     * @param connection The database connection to use
     * @param table The name of the table that the column appears in
     * @param column The name of the column to search for
     * @return Whether the column exists as a boolean. Returns false upon SQL error
     */
    public static boolean checkColumnExists(Connection connection, String table, String column) throws SQLException {
        if (table == null || table.isEmpty() || column == null || column.isEmpty()) {
            throw new IllegalArgumentException("Table or column name cannot be null or empty.");
        }

        String query = "SELECT COUNT(*) FROM information_schema.columns " +
                "WHERE table_schema = 'public' " +
                "AND LOWER(table_name) = LOWER(?) " +
                "AND LOWER(column_name) = LOWER(?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, table);
            statement.setString(2, column);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }

        return false;
    }

    /**
     * Creates a column in a table with the given type and default value.
     *
     * @param connection  The database connection to use.
     * @param table       The table to create the column in.
     * @param column      The name of the column to create.
     * @param type        The type of the column to create (e.g., "INT", "VARCHAR(255)").
     * @param defaultValue The default value that should be inserted in the column.
     * @throws SQLException If an SQL error occurs.
     */
    public static void createColumn(Connection connection, String table, String column, String type, String defaultValue) throws SQLException {
        Pattern validIdentifierPattern = Pattern.compile("^[a-zA-Z0-9_]+$");

        if (!validIdentifierPattern.matcher(table).matches() || !validIdentifierPattern.matcher(column).matches()) {
            throw new IllegalArgumentException("Table or column name contains invalid characters.");
        }

        String query = "ALTER TABLE " + table + " ADD COLUMN " + column + " " + type + " DEFAULT ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, defaultValue);
            statement.executeUpdate();
        }
    }

    public static void updateColumnValue(
            Connection connection,
            String table,
            String columnToUpdate,
            Object newValue,
            String condition,
            Object... conditionValues
    ) throws SQLException {
        // Validate the table and column names
        Pattern validIdentifierPattern = Pattern.compile("^[a-zA-Z0-9_]+$");

        if (!validIdentifierPattern.matcher(table).matches()) {
            throw new IllegalArgumentException("Table name contains invalid characters.");
        }
        if (!validIdentifierPattern.matcher(columnToUpdate).matches()) {
            throw new IllegalArgumentException("Column name contains invalid characters.");
        }

        // Build the SQL query
        String query = "UPDATE " + table + " SET " + columnToUpdate + " = ? WHERE " + condition;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the new value
            statement.setObject(1, newValue);

            // Set the condition parameters
            for (int i = 0; i < conditionValues.length; i++) {
                statement.setObject(i + 2, conditionValues[i]); // Offset by 2 because the first parameter is the new value
            }

            statement.executeUpdate();
        }
    }

    public static <T> T safeGet(T value) {
        return value != null ? value : null;
    }
}
