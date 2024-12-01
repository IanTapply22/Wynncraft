package com.iantapply.wynncraft.database;

import com.iantapply.wynncraft.database.model.MigrationModel;
import com.iantapply.wynncraft.database.model.Model;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Objects;

@Getter @Setter
public class DatabaseCore {
    private ArrayList<Model> models;

    public DatabaseCore() {
        this.models = new ArrayList<>();
    }

    /**
     * Initializes the database core by staging all models that are to
     * be activated or registered.
     */
    public void initialize() {
        this.stageModel(new MigrationModel());
    }

    /**
     * Stages a model to be checked for migrations and to be registered
     * @param model The model to stage
     */
    public void stageModel(Model model) {
        if (!models.contains(model)) {
            this.models.add(model);
        } else {
            Logger.log(LoggingLevel.WARNING, String.format("Model %s is already staged", model.name()));
        }
    }

    /**
     * Registers a model to the plugin by checking for migrations and migrating if available
     */
    public void registerModels() {
        // TODO: Handle reversion on failure of partial migration
        int migrationNeededCount = 0;
        int autoMigratedCount = 0;

        if (models.isEmpty()) {
            Logger.log(LoggingLevel.INFO, "No models staged. Skipping registration process.");
            return;
        }

        for (Model model : this.models) {
            Logger.log(LoggingLevel.INFO, String.format("Registering %s model...", model.name()));

            Connection connection = model.database().connect(true);
            // Check if table does not exist, if not then create the blank table
            if (!DatabaseHelpers.checkTableExists(connection, model.table())) {
                Logger.log(LoggingLevel.INFO, String.format("Initial running of migration, automatically migrating and creating table %s...", model.table()));
                model.migrate(true);
                Logger.log(LoggingLevel.INFO, String.format("Migrated and registered %s", model.name()));
                model.database().disconnect();
                continue;
            }

            // Check current version in comparison to migrated version
            if (Objects.equals(model.migratedVersion(), model.version())) {
                Logger.log(LoggingLevel.INFO, String.format("Registered %s model", model.name()));
            } else {
                // If we can automatically migrate upon registration
                if (model.automaticallyMigrate()) {
                    // Notifies of version mismatch
                    Logger.log(LoggingLevel.WARNING, String.format("Model migration version mismatch (current: %s, migrated: %s). Attempting to auto-migrate.", model.version(), model.migratedVersion()));

                    Logger.log(LoggingLevel.INFO, String.format("Model can be auto-migrated. Migrating %s...", model.name()));
                    model.migrate(false);
                    Logger.log(LoggingLevel.INFO, String.format("Migrated and registered %s", model.name()));
                    autoMigratedCount++;
                } else {
                    // In the case we can't auto migrate, advise manual migration rather than auto-migrating
                    Logger.log(LoggingLevel.WARNING, String.format("Model migration version mismatch (current: %s, migrated: %s). Please manually migrate the %s model.", model.version(), model.migratedVersion(), model.name()));
                    migrationNeededCount++;
                }
            }
        }

        Logger.log(LoggingLevel.SUCCESS, String.format("Registered %s models with %s needing manual migrations and %s auto-migrated upon registration.", models.size(), migrationNeededCount, autoMigratedCount));
    }
}
