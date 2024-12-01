package com.iantapply.wynncraft.database.model;

import com.iantapply.wynncraft.database.Database;
import com.iantapply.wynncraft.database.database.MigrationsDatabase;
import com.iantapply.wynncraft.database.table.Column;
import com.iantapply.wynncraft.database.table.DataType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * A model that is used to track the versioning of models as they are migrated.
 * <p>
 * This is used to notify administrators of migrations that are needed to be performed
 * on the database.
 */
@Getter @Setter
public class MigrationModel implements Model {
    public String uuid;
    public String version;
    public String createdAt;

    public MigrationModel(String uuid, String version, String createdAt) {
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
    public Database database() {
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
}
