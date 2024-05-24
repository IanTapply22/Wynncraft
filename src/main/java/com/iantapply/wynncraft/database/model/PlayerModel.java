package com.iantapply.wynncraft.database.model;

import com.iantapply.wynncraft.rank.NonPurchasableRank;
import com.iantapply.wynncraft.rank.PurchasableRank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerModel implements Model {
    private String id;
    /* These are stored separately to give players permissions based on things that other ranks doesn't have **/
    private PurchasableRank purchasedRank;
    private NonPurchasableRank nonPurchasedRank;
    private double combatLevelXp; // The amount of XP the player has towards their next combat level, this will be converted to a level in the service
    private String classes; // The profiles the player has on their account, this is stored as a JSON string and only has IDs

    public PlayerModel(Integer someNumber, String someString) {
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
