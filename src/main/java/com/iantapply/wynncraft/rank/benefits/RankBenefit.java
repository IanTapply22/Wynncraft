package com.iantapply.wynncraft.rank.benefits;

import java.util.ArrayList;

/**
 * An interface used to tailor the benefits of a rank
 * <p>
 * By default, all methods return the benefits of the VIP rank
 */
public interface RankBenefit {

    /**
     * A notification that is sent to the world that the player joins
     * @return true if the player should have the login notification displayed
     */
    default boolean loginNotifications() {
        return true;
    }

    /**
     * The number of daily mob totems the player should receive
     * @return the number of daily mob totems
     */
    default Integer dailyMobTotems() {
        return 2;
    }

    /**
     * The number of daily crates the player is allowed to open.
     * <p>
     * For VIP+ and above ranks, it should be used to give 1 tier and X amount of crates
     * @return the number of daily crates
     */
    default Integer dailyCrateCount() {
        return 0;
    }

    /**
     * An array of classes that can be reskinned
     * @return an array of class names that can be reskinned
     */
    default ArrayList<String> reskinnedClasses() {
        ArrayList<String> reskinnedClasses = new ArrayList<>();
        reskinnedClasses.add("Hunter");
        reskinnedClasses.add("Knight");
        return reskinnedClasses;
    }

    /**
     * Wether the player can fly in the Wynncraft pre-lobby with no invisibility
     * @return true if the player can fly in the lobby
     */
    default boolean lobbyFlightNoInvisibility() {
        return true;
    }

    /**
     * The tier of the reskinned class icons that should appear
     * @return the tier of the reskinned class icons. Up to tier 3
     */
    default Integer reskinnedClassIconsTier() {
        return 1;
    }

    /**
     * Wether the player should have access to the '/sign' command
     * <p>
     * Allows you to sign your name on a crafted item
     * @return true if the player should have access to the sign command
     */
    default boolean signCommandAccess() {
        return true;
    }

    /**
     * Wether the player should have access to the '/rename' command
     * <p>
     * Allows you to rename your crafted item
     * @return true if the player should have access to the rename command
     */
    default boolean renameCommandAccess() {
        return false;
    }

    /**
     * Wether the player should have access to the '/changetag' command
     * <p>
     * Allows you to change your rank appearance in chat to a lower rank
     * @return true if the player should have access to the changetag command
     */
    default boolean changetagCommandAccess() {
        return false;
    }

    /**
     * Allows you to see the territory region of your guild on the Wynncraft map
     * @return true if the player should have access to displaying their guild territory on the map
     */
    default boolean guildTerritoryMapIcon() {
        return false;
    }

    /**
     * Wether the player should have access to the '/relore' command
     * <p>
     * Adds or changes the item lore of a Crafted Item
     * @return true if the player should have access to the relore command
     */
    default boolean reloreCommandAccess() {
        return false;
    }

    /**
     * Wether the player should have access to customize their character nickname
     * @return true if the player should have access to customize their character nickname
     */
    default boolean customizableCharacterNickname() {
        return false;
    }

    /**
     * The number of additional class slots the player should have. The default number of
     * class slots is 6 without a rank
     * @return the number of additional class slots
     */
    default Integer additionalClassSlots() {
        return 3;
    }

    /**
     * The number of additional market slots the player should have. The default number of
     * market slots is 5 without a rank
     * @return the number of additional market slots
     */
    default Integer additionalMarketSlots() {
        return 5;
    }

    /**
     * Allows you to listen to the OST of Wynncraft wherever you want
     * @return true if the player should have access to the jukebox
     */
    default boolean jukeboxAccess() {
        return false;
    }

    /**
     * Allows you to access the beta version of the server
     * @return true if the player should have access to the beta features
     */
    default boolean heroBetaAccess() {
        return false;
    }

    /**
     * Allows you to use the '/switch' command to switch between worlds without
     * having to go to the lobby
     * @return true if the player should have access to the switch command
     */
    default boolean switchCommandAccess() {
        return false;
    }

    /**
     * Allows you to instantly delete a class without having to wait
     * @return true if the player should have access to instant class deletion
     */
    default boolean instantClassDeletion() {
        return false;
    }

    /**
     * Allows you to backup classes in case you accidentally delete them
     * @return true if the player should have access to class backups
     */
    default boolean classBackups() {
        return false;
    }

    /**
     * Wether you get notified when a bomb is thrown in a Wynncraft world
     * @return true if the player should have access to bomb notifications
     */
    default boolean bombBell() {
        return false;
    }

    /**
     * A temporary booth will be set up in an area which will
     * sell item you are selling on the Trade Market.
     * @return true if the player should have access to the merchant booth placement
     */
    default boolean merchantBoothPlacement() {
        return false;
    }

    /**
     * Allows you to join a world that is already full
     * @return true if the player should have access to join full worlds
     */
    default boolean reservedSlotAccess() {
        return false;
    }

    /**
     * Allows you to rename pets
     * @return true if the player should have access to rename pets
     */
    default boolean renamePetsAccess() {
        return true;
    }

    /**
     * Wether or not the player should be able to summon up to 3 pets at
     * one point in time
     * @return true if the player should have access to summoning 3 pets
     */
    default boolean summonThreePetsAccess() {
        return false;
    }

    /**
     * The maximum level a pet can be
     * @return the maximum level a pet can be as an integer
     */
    default Integer petMaxLevel() {
        return 20;
    }

    /**
     * The additional amount of housing islands allowed above the default
     * value of 1
     * @return the additional amount of housing islands the player is allowed.
     * The max value allowed for this is 4
     */
    default Integer additionalHousingIslands() {
        return 1;
    }

    /**
     * The additional amount of miscellaneous bucket pages the player is allowed
     * @return the additional amount of bucket pages the player is allowed as an integer
     * The max value allowed for this is 8
     */
    default Integer additionalBucketPages() {
        return 2;
    }

    /**
     * The additional amount of region limits the player is allowed
     * @return the additional amount of region limits the player is allowed as an integer
     * The max value allowed for this is 55
     */
    default Integer additionalRegionLimit() {
        return 5;
    }

    /**
     * The additional amount of NPC limits the player is allowed for housing
     * @return the additional amount of NPC limits the player is allowed as an integer
     * The max value allowed for this is 50
     */
    default Integer additionalNPCLimit() {
        return 0;
    }

    /**
     * Wether or not the player has access to play additional housing music
     * @return true if the player should have access to extra housing music
     */
    default boolean extraHousingMusic() {
        return false;
    }

    /**
     * Wether or not players can visit their house when they are offline
     * @return true if the player should have access to offline house visiting
     */
    default boolean offlineHouseGuestVisiting() {
        return false;
    }

    /**
     * Wether or not the player should have access to the blueprint tool
     * @return true if the player should have access to the blueprint tool
     */
    default boolean blueprintToolAccess() {
        return false;
    }

    /**
     * Wether or not the player should have access to the replication tool
     * @return true if the player should have access to the replication tool
     */
    default boolean replicationToolAccess() {
        return false;
    }

    /**
     * Wether or not the player should have access to the Build Stand NPC
     * @return true if the player should have access to the Build Stand NPC
     */
    default boolean buildStandNPCAccess() {
        return false;
    }

    /**
     * Wether or not there is a permanent merchant booth on the players housing
     * island
     * @return true if the player should have access to a permanent merchant booth
     */
    default boolean housingMerchantBoothAccess() {
        return false;
    }
}
