package com.iantapply.wynncraft.database.pgsql.table;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a column in a table with the name and type
 * <p>
 * This is used to create a column for an existing or new database table.
 */
@Getter @Setter
public class Column {
    private String name;
    private DataType type;
    private String defaultValue;

    /**
     * A representation of a column in a database table
     * @param name The name of the column
     * @param type The PostgreSQL type that the column cells will always have
     */
    public Column(String name, DataType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * A representation of a column in a database table
     * @param name The name of the column
     * @param type The PostgreSQL type that the column cells will always have
     * @param defaultValue The default value that appears in the column when creating a row
     */
    public Column(String name, DataType type, String defaultValue) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
    }
}
