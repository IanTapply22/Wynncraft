package com.iantapply.wynncraft.database.table;

import lombok.Getter;

/**
 * A data type that is used in a column or cell of a PostgreSQL database.
 * <p>
 * For references to data types, please see <a href="https://www.postgresql.org/docs/current/datatype.html">PostgreSQL data types</a>
 */
@Getter
public enum DataType {
    BOOLEAN(0, "bool", "Logical Boolean (true/false)"),
    INTEGER(1, "integer", "Signed four-byte integer"),
    JSON(2, "json", "Textual JSON data"),
    JSONB(3, "jsonb", "Binary JSON data, decomposed"),
    TEXT(4, "text", "Variable-length character string"),
    UUID(5, "uuid", "Universally unique identifier"),
    DATE(6, "date", "Calendar date (year, month, day)"),
    CIDR(7, "cidr", "IPv4 or IPv6 network address");

    private final int id;
    private final String type;
    private final String description;

    /**
     * A data type that is used in a column or cell. It is primarily used for
     * migrations and table construction.
     * @param id The internal ID of the data type
     * @param type The type that is used in PostgreSQL to modify a column
     * @param description The internal description of the data type. Used only internally.
     */
    DataType(int id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }
}
