package com.iantapply.wynncraft.database.model;

import lombok.Getter;
import lombok.Setter;

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
