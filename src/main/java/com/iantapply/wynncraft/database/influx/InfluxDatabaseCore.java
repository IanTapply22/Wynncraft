package com.iantapply.wynncraft.database.influx;

import lombok.Getter;

import java.util.ArrayList;

/**
 * Handles all influx databases and registers them into an array and initializes the clients and connections
 */
@Getter
public class InfluxDatabaseCore {
    private final ArrayList<InfluxDatabase> influxDatabases;

    public InfluxDatabaseCore() {
        this.influxDatabases = new ArrayList<>();
    }

    /**
     * Initializes the influx database core and stages all create influx databases
     * Only call this method if you are providing a previous list of influx databases
     */
    public void initialize() {
        if (this.influxDatabases.isEmpty()) return;

        for (InfluxDatabase influxDatabase : this.influxDatabases) {
            this.stageInfluxDatabase(influxDatabase);
        }
    }

    /**
     * Stages the influx databases that are to be registered
     */
    public void stageInfluxDatabases() {
        // TODO: Create a proper collection of orgs and buckets
        this.stageInfluxDatabase(new InfluxDatabase("wynncraft", "wynncraft"));
    }

    /**
     * Adds an influx database to the list of influx databases
     * @param influxDatabase The influx database to add
     */
    public void stageInfluxDatabase(InfluxDatabase influxDatabase) {
        this.influxDatabases.add(influxDatabase);
    }

    /**
     * Registers all influx databases and initializes them
     */
    public void registerDatabases() {
        for (InfluxDatabase influxDatabase : this.influxDatabases) {
            influxDatabase.initialize();
        }
    }

    /**
     * Unregisters all influx databases and closes the connections
     */
    public void unregisterDatabases() {
        for (InfluxDatabase influxDatabase : this.influxDatabases) {
            influxDatabase.close();
        }
        this.influxDatabases.clear();
    }
}
