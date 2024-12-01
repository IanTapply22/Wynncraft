package com.iantapply.wynncraft.database.model;

import com.iantapply.wynncraft.database.Database;
import com.iantapply.wynncraft.database.table.Column;
import com.iantapply.wynncraft.rank.NonPurchasableRank;
import com.iantapply.wynncraft.rank.PurchasableRank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class PlayerModel implements Model {
    private String uuid;
    /* These are stored separately to give players permissions based on things that other ranks doesn't have **/
    private PurchasableRank purchasedRank;
    private NonPurchasableRank nonPurchasedRank;
    private double combatLevelXp; // The amount of XP the player has towards their next combat level, this will be converted to a level in the service
    private String classes; // The profiles the player has on their account, this is stored as a JSON string and only has IDs

    public PlayerModel(Integer someNumber, String someString) {
    }

    /**
     * Used only for running the migrate and revert methods
     */
    public PlayerModel() {}

    /**
     * The UUID of the model. This does not change
     * @return The UUID of the model
     */
    @Override
    public String uuid() {
        return "163d5af6-43dd-4c82-8da7-df40bddb7d62";
    }

    /**
     * The database, or table that the model is the child of. This is also referred to as
     * the parent of the model.
     * @return The Database object of the table
     */
    @Override
    public Database database() {
        return null;
    }

    /**
     * The table the migration will be run on inside the parent
     * @return The name of the table in the parent database
     */
    @Override
    public String table() {
        return null;
    }

    /**
     * The columns that will appear in order and in the specified parent
     * @return An arraylist of column objects the contain the information of the column
     */
    @Override
    public ArrayList<Column> columns() {
        return null;
    }

    /**
     * The name of the model, used in the initialization of the database and debugging
     *
     * @return The name of the model as a string
     */
    @Override
    public String name() {
        return "Player";
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
