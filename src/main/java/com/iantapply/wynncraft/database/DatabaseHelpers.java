package com.iantapply.wynncraft.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

/**
 * A collection of helper methods for database related tasks including
 * but not limited to building URLs, finishing queries, and interacting
 * with tables
 */
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
     * Finishes a query by executing the statement and closing it without returning a result
     * @param queryStatement The statement to finish
     * @throws SQLException If the statement cannot be executed or closed
     */
    public static void finishQueryWithoutResult(PreparedStatement queryStatement) throws SQLException {
        queryStatement.execute();
        queryStatement.close();
    }

    /**
     * Finishes a query by executing the statement and closing it
     * @param queryStatement The statement to finish
     * @return The result of the query
     * @throws SQLException If the statement cannot be executed or closed
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
     * Inserts a row into a table with the given columns and values
     * <p>
     * Please be aware and cautious that this does not sanitize the input
     * nor does it check for the types of both the columns and values inserted.
     * @param table The table to insert the row into
     * @param columns The columns to insert the values into
     * @param values The values to insert into the columns
     */
    public static void insertRow(Connection connection, String table, String[] columns, String[] values) throws SQLException {
        // Prepare the SQL query
        String columnString = String.join(", ", columns);
        String valuePlaceholderString = String.join(", ", Collections.nCopies(values.length, "?"));
        String query = String.format("INSERT INTO %s (%s) VALUES (%s)", table, columnString, valuePlaceholderString);

        // Execute and finishes the query
        PreparedStatement queryStatement = connection.prepareStatement(query);
        for (int i = 0; i < values.length; i++) {
            queryStatement.setString(i + 1, values[i]);
        }

        finishQueryWithoutResult(queryStatement);
    }

    /**
     * Updates a row in a table with the given columns and values
     * @param connection The database connection to use
     * @param table The table to update the row in
     * @param columns The columns to update
     * @param values The values to update the columns with
     * @param condition The condition to check for when updating a row (e.g. "id = 1")
     * @throws SQLException If the statement cannot be executed or closed
     */
    public static void updateRow(Connection connection, String table, String[] columns, String[] values, String condition) throws SQLException {
        // Builds the set clause
        StringBuilder setClause = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            setClause.append(columns[i]).append(" = ?");
            if (i < columns.length - 1) {
                setClause.append(", ");
            }
        }

        // Tie together the final SQL query string
        String query = String.format("UPDATE %s SET %s WHERE %s", table, setClause, condition);

        // Execute and finishes the query
        PreparedStatement queryStatement = connection.prepareStatement(query);
        for (int i = 0; i < values.length; i++) {
            queryStatement.setString(i + 1, values[i]);
        }

        finishQueryWithoutResult(queryStatement);
    }

    /**
     * Deletes a row from a table with the given condition
     * @param connection The database connection to use
     * @param table The table to delete the row from
     * @param condition The condition to check for when deleting a row (e.g. "id = 1")
     * @throws SQLException If the statement cannot be executed or closed
     */
    public static void deleteRow(Connection connection, String table, String condition) throws SQLException {
        // Prepare the SQL query
        String query = String.format("DELETE FROM %s WHERE %s", table, condition);

        // Execute and finishes the query
        PreparedStatement queryStatement = connection.prepareStatement(query);
        finishQueryWithoutResult(queryStatement);
    }

    /**
     * Creates a table with the given columns and types
     * @param connection The database connection to use
     * @param table The table to create
     * @param columns The columns to create in the table (e.g. "id", "name", "age")
     * @param types The types of the columns to create (e.g. "INT", "VARCHAR(255)", "INT")
     * @throws SQLException If the statement cannot be executed or closed
     */
    public static void createTable(Connection connection, String table, String[] columns, String[] types) throws SQLException {
        // Builds the column string
        StringBuilder columnString = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            columnString.append(columns[i]).append(" ").append(types[i]);
            if (i < columns.length - 1) {
                columnString.append(", ");
            }
        }

        // Prepare the SQL query
        String query = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", table, columnString);

        // Execute and finishes the query
        PreparedStatement queryStatement = connection.prepareStatement(query);
        finishQueryWithoutResult(queryStatement);
    }

    /**
     * Drops a table from the database
     * @param connection The database connection to use
     * @param table The name of the table to drop
     * @throws SQLException If the statement cannot be executed or closed
     */
    public static void dropTable(Connection connection, String table) throws SQLException {
        // Prepare the SQL query
        String query = String.format("DROP TABLE IF EXISTS %s", table);

        // Execute and finishes the query
        PreparedStatement queryStatement = connection.prepareStatement(query);
        finishQueryWithoutResult(queryStatement);
    }

    /**
     * Selects an entire row from a table with the given condition
     * @param connection The database connection to use
     * @param table The table to select the row from
     * @param condition The condition to check for when selecting a row (e.g. "id = 1")
     * @return The rows data in a String array
     * @throws SQLException If the statement cannot be executed or closed
     */
    public static String[] selectRow(Connection connection, String table, String condition) throws SQLException {
        // Prepare the SQL query
        String query = String.format("SELECT * FROM %s WHERE %s", table, condition);

        // Execute and finishes the query
        PreparedStatement queryStatement = connection.prepareStatement(query);


        return finishQuery(queryStatement);
    }

    /**
     * Creates a column in a table with the given type
     * @param connection The database connection to use
     * @param table The table to create the column in
     * @param column The name of the column to create
     * @param type The type of the column to create (e.g. "INT", "VARCHAR(255)", "INT")
     * @throws SQLException If the statement cannot be executed or closed
     */
    public static void createColumn(Connection connection, String table, String column, String type) throws SQLException {
        // Prepare the SQL query
        String query = String.format("ALTER TABLE %s ADD COLUMN %s %s", table, column, type);

        // Execute and finishes the query
        PreparedStatement queryStatement = connection.prepareStatement(query);
        finishQueryWithoutResult(queryStatement);
    }

    /**
     * Creates a column in a table with the given type and default value
     * @param connection The database connection to use
     * @param table The table to create the column in
     * @param column The name of the column to create
     * @param type The type of the column to create (e.g. "INT", "VARCHAR(255)", "INT")
     * @param defaultValue The default value that should be inserted in the column
     * @throws SQLException If the statement cannot be executed or closed
     */
    public static void createColumn(Connection connection, String table, String column, String type, String defaultValue) throws SQLException {
        // Prepare the SQL query
        String query = String.format("ALTER TABLE %s ADD COLUMN %s %s DEFAULT %s", table, column, type, defaultValue);

        // Execute and finishes the query
        PreparedStatement queryStatement = connection.prepareStatement(query);
        finishQueryWithoutResult(queryStatement);
    }
}
