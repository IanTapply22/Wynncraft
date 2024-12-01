package com.iantapply.wynncraft.database.model;

import com.iantapply.wynncraft.database.Database;
import com.iantapply.wynncraft.database.database.ExampleDatabase;
import com.iantapply.wynncraft.database.database.WynncraftDatabase;
import com.iantapply.wynncraft.database.table.Column;
import com.iantapply.wynncraft.database.table.DataType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents an example model that can be used to demonstrate
 * how to create a model for the database.
 */
@Getter @Setter
public class ExampleModel implements Model {
    private Integer someNumber;
    private String someString;

    public ExampleModel(Integer someNumber, String someString) {
        this.someNumber = someNumber;
        this.someString = someString;
    }

    /**
     * Used only for running the migrate and revert methods
     */
    public ExampleModel() {}

    /**
     * The UUID of the model. This does not change
     * @return The UUID of the model
     */
    @Override
    public String uuid() {
        return "9b794d87-1fb4-4405-a16c-b95e64542d4b";
    }

    /**
     * The database, or table that the model is the child of. This is also referred to as
     * the parent of the model.
     * @return The Database object of the table
     */
    @Override
    public Database database() {
        return new ExampleDatabase();
    }

    /**
     * The table the migration will be run on inside the parent
     * @return The name of the table in the parent database
     */
    @Override
    public String table() {
        return "example";
    }

    /**
     * The columns that will appear in order and in the specified parent
     * @return An arraylist of column objects the contain the information of the column
     */
    @Override
    public ArrayList<Column> columns() {
        ArrayList<Column> columns = new ArrayList<>();
        columns.add(new Column("example", DataType.TEXT));

        return columns;
    }

    /**
     * The name of the model, used in the initialization of the database and debugging
     *
     * @return The name of the model as a string
     */
    @Override
    public String name() {
        return "Example";
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
