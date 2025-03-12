package com.iantapply.wynncraft.database.model;

import com.iantapply.wynncraft.database.database.MigrationsDatabase;
import com.iantapply.wynncraft.database.pgsql.PGSQLDatabase;
import com.iantapply.wynncraft.database.pgsql.PGSQLDatabaseHelpers;
import com.iantapply.wynncraft.database.pgsql.table.Column;
import com.iantapply.wynncraft.database.pgsql.table.DataType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

/**
 * A model that is used to track the versioning of models as they are migrated.
 * <p>
 * This is used to notify administrators of migrations that are needed to be performed
 * on the database.
 */
@Getter @Setter
public class MigrationModel implements Model {
    public UUID uuid;
    public String version;
    public Timestamp createdAt;

    public MigrationModel(UUID uuid, String version, Timestamp createdAt) {
        this.uuid = uuid;
        this.version = version;
        this.createdAt = createdAt;
    }

    /**
     * Used only for running the migrate and revert methods
     */
    public MigrationModel() {}

    /**
     * The UUID of the model. This does not change
     * @return The UUID of the model
     */
    @Override
    public String uuid() {
        return "0091a112-8502-4a31-bc1f-71842f8f5cec";
    }

    /**
     * The database, or table that the model is the child of. This is also referred to as
     * the parent of the model.
     * @return The Database object of the table
     */
    @Override
    public PGSQLDatabase database() {
        return new MigrationsDatabase();
    }

    /**
     * The table the migration will be run on inside the parent
     * @return The name of the table in the parent database
     */
    @Override
    public String table() {
        return "migrations";
    }

    /**
     * The columns that will appear in order and in the specified parent
     * @return An arraylist of column objects the contain the information of the column
     */
    @Override
    public ArrayList<Column> columns() {
        ArrayList<Column> columns = new ArrayList<>();
        columns.add(new Column("uuid", DataType.UUID));
        columns.add(new Column("version", DataType.TEXT));
        columns.add(new Column("created_at", DataType.TIMESTAMP));

        return columns;
    }

    /**
     * The name of the model, used in the initialization of the database and debugging
     *
     * @return The name of the model as a string
     */
    @Override
    public String name() {
        return "Migration";
    }

    /**
     * The version of the model, should be updated when changes have been
     * made to the model that require a database migration.
     * <p>
     * The version should be in the format of "major.minor.patch" (e.g. "7.7.2")
     *
     * @return The version of the model as a string
     */
    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public Object getModelValue(String column) throws SQLException {
        // Get the value from the database
        Connection connection = this.database().connect(true);
        String condition = "uuid = CAST(? AS UUID)";
        String[] row = PGSQLDatabaseHelpers.selectRow(connection, this.table(), condition, this.getUuid());
        this.database().disconnect();

        // Find column index
        int index = -1;
        for (int i = 0; i < this.columns().size(); i++) {
            if (this.columns().get(i).getName().equalsIgnoreCase(column)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new SQLException("Column not found: " + column);
        }

        if (row == null) {
            return null;
        }

        // Convert the retrieved value to the correct type
        Object value = row[index];

        // Cast properly based on expected return type
        return PGSQLDatabaseHelpers.parseValue(value, this.columns().get(index).getType());
    }

    @Override
    public void populate() throws SQLException {
        // Copy column names to a string array
        ArrayList<String> columnNamesList = new ArrayList<>();
        for (Column column : columns()) {
            columnNamesList.add(column.getName());
        }
        String[] columnNames = columnNamesList.toArray(new String[0]);

        // Copy values in order to string array
        Object[] values = new Object[]{ this.getUuid(), this.getVersion(), this.getCreatedAt() };

        Connection connection = this.database().connect(true);
        PGSQLDatabaseHelpers.insertRow(connection, this.table(), columnNames, values);
        this.database().disconnect();
    }
}
