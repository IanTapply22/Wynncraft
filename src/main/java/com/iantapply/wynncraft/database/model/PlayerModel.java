package com.iantapply.wynncraft.database.model;

import com.iantapply.wynncraft.database.Database;
import com.iantapply.wynncraft.database.DatabaseHelpers;
import com.iantapply.wynncraft.player.LegacyRankColour;
import com.iantapply.wynncraft.database.database.WynncraftDatabase;
import com.iantapply.wynncraft.database.table.Column;
import com.iantapply.wynncraft.database.table.DataType;
import com.iantapply.wynncraft.rank.NonPurchasableRank;
import com.iantapply.wynncraft.rank.PurchasableRank;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

/**
 * A model that is used to handle and store the base profile data of a
 * player who is registered on the server.
 * <p>
 * All profile related data is stored in the profiles table on a per-profile basis
 */
@Getter @Setter
public class PlayerModel implements Model {
    private String username;
    private Boolean online;
    private String server;
    private UUID activeCharacter; // Stored as UUID internally, but stored as String in DB
    private String nickname;
    private UUID uuid; // Shouldn't change and should be the main source of ID
    private NonPurchasableRank rank; // Stored as the rank ID in the DB, but stored internally as a NonPurchasableRank
    private String rankBadge; // The URL path to the badge SVG in the Wynncraft CDN
    private LegacyRankColour legacyRankColour; // Stored in a LegacyRankColour object internally, but parsed in DB as JSON
    private PurchasableRank supportRank; // The rank that determines the support level, stored in DB as string
    private Boolean veteran;
    private Timestamp firstJoin; // Also the created_at of the DB row. Type in DB is of timestamp
    private Timestamp lastJoin; // Stored in DB as timestamp
    private Integer forumLink; // The code used to link your forums account
    private Boolean publicProfile;

    public PlayerModel(String username, Boolean online, String server, UUID activeCharacter, String nickname, UUID uuid, NonPurchasableRank rank,
                       String rankBadge, LegacyRankColour legacyRankColour, PurchasableRank supportRank, Boolean veteran,
                       Timestamp firstJoin, Timestamp lastJoin, Integer forumLink, Boolean publicProfile) {
        this.username = username;
        this.online = online;
        this.server = server;
        this.activeCharacter = activeCharacter;
        this.nickname = nickname;
        this.uuid = uuid;
        this.rank = rank;
        this.rankBadge = rankBadge;
        this.legacyRankColour = legacyRankColour;
        this.supportRank = supportRank;
        this.veteran = veteran;
        this.firstJoin = firstJoin;
        this.lastJoin = lastJoin;
        this.forumLink = forumLink;
        this.publicProfile = publicProfile;
    }

    public PlayerModel(UUID uuid) {
        this.uuid = uuid;
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
        return new WynncraftDatabase();
    }

    /**
     * The table the migration will be run on inside the parent
     * @return The name of the table in the parent database
     */
    @Override
    public String table() {
        return "players";
    }

    /**
     * The columns that will appear in order and in the specified parent
     * @return An arraylist of column objects the contain the information of the column
     */
    @Override
    public ArrayList<Column> columns() {
        ArrayList<Column> columns = new ArrayList<>();
        columns.add(new Column("username", DataType.TEXT));
        columns.add(new Column("online", DataType.BOOLEAN));
        columns.add(new Column("server", DataType.TEXT));
        columns.add(new Column("activeCharacter", DataType.UUID));
        columns.add(new Column("nickname", DataType.TEXT));
        columns.add(new Column("uuid", DataType.UUID));
        columns.add(new Column("rank", DataType.INTEGER)); // ID rather than name, this is parsed
        columns.add(new Column("rankBadge", DataType.TEXT));
        columns.add(new Column("legacyRankColour", DataType.JSON));
        columns.add(new Column("supportRank", DataType.INTEGER)); // ID rather than name, this is parsed
        columns.add(new Column("veteran", DataType.BOOLEAN));
        columns.add(new Column("firstJoin", DataType.TIMESTAMP));
        columns.add(new Column("lastJoin", DataType.TIMESTAMP));
        columns.add(new Column("forumLink", DataType.INTEGER));
        columns.add(new Column("publicProfile", DataType.BOOLEAN, "true"));

        return columns;
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
        return "1.0.5";
    }

    @Override
    public boolean automaticallyMigrate() {
        return true;
    }

    // TODO: Redo populate method
    @Override
    public void populate() throws SQLException {
        // Extract column names and values
        ArrayList<String> columnNamesList = new ArrayList<>();
        for (Column column : columns()) {
            columnNamesList.add(column.getName());
        }
        String[] columnNames = columnNamesList.toArray(new String[0]);

        // Object array with a utility method for null checks
        Object[] values = new Object[]{
                DatabaseHelpers.safeGet(this.getUsername()),
                DatabaseHelpers.safeGet(this.getOnline()),
                DatabaseHelpers.safeGet(this.getServer()),
                DatabaseHelpers.safeGet(this.getActiveCharacter()),
                DatabaseHelpers.safeGet(this.getNickname()),
                DatabaseHelpers.safeGet(this.getUuid()),
                DatabaseHelpers.safeGet(this.getRank() != null ? this.getRank().getId() : null),
                DatabaseHelpers.safeGet(this.getRankBadge()),
                DatabaseHelpers.safeGet(this.getLegacyRankColour() != null ? this.getLegacyRankColour().getContent() : null),
                DatabaseHelpers.safeGet(this.getSupportRank() != null ? this.getSupportRank().getId() : null),
                DatabaseHelpers.safeGet(this.getVeteran()),
                DatabaseHelpers.safeGet(this.getFirstJoin()),
                DatabaseHelpers.safeGet(this.getLastJoin()),
                DatabaseHelpers.safeGet(this.getForumLink()),
                DatabaseHelpers.safeGet(this.getPublicProfile())
        };

        Connection connection = this.database().connect(true);

        try {
            // Check if a row with the same UUID exists
            String condition = "uuid = CAST(? AS UUID)";
            String[] existingRow = DatabaseHelpers.selectRow(connection, this.table(), condition, this.getUuid());

            if (existingRow != null) {
                // Update only the columns that have changed, and avoid overwriting with null values
                for (int i = 0; i < columnNames.length; i++) {
                    String columnName = columnNames[i];
                    Object currentValue = values[i];
                    String databaseValue = existingRow[i];

                    // Only update if the current value is not null and is different from the database value
                    if (currentValue != null && !currentValue.toString().equals(databaseValue)) {
                        DatabaseHelpers.updateColumnValue(connection, this.table(), columnName, currentValue, condition, this.getUuid());
                    }
                }
            } else {
                // Insert a new row if none exists
                DatabaseHelpers.insertRow(connection, this.table(), columnNames, values);
            }
        } finally {
            this.database().disconnect();
        }
    }
}
