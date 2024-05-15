package com.iantapply.wynncraft.database.models;

/**
 * Represents a model that contains various data on an object
 */
public interface Model {

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
}
