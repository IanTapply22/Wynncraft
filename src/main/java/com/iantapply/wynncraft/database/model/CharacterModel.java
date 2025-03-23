package com.iantapply.wynncraft.database.model;

import com.iantapply.wynncraft.character.CharacterReskin;
import com.iantapply.wynncraft.character.CharacterType;
import com.iantapply.wynncraft.character.Gamemode;
import com.iantapply.wynncraft.database.model.object.Profession;
import com.iantapply.wynncraft.database.model.object.Pvp;
import com.iantapply.wynncraft.database.model.object.SkillPoints;
import com.iantapply.wynncraft.database.pgsql.PGSQLDatabaseHelpers;
import com.iantapply.wynncraft.database.pgsql.PGSQLDatabase;
import com.iantapply.wynncraft.database.pgsql.table.DataType;
import com.iantapply.wynncraft.database.database.WynncraftDatabase;
import com.iantapply.wynncraft.database.pgsql.table.Column;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

// TODO: Store raids, dungeons, and quests on separate tables
@Getter @Setter
public class CharacterModel implements Model {
    private UUID uuid;
    private UUID ownerUUID;
    private CharacterType type;
    private CharacterReskin reskin;
    private String nickname;
    private int level;
    private int xp;
    private int totalLevel;
    private Gamemode gamemode;
    private int wars; // TODO: Calculate from wars database
    private double playtime;
    private int mobsKilled;
    private int chestsFound;
    private int itemsIdentified;
    private int blocksWalked;
    private int logins;
    private int deaths;
    private int discoveries;
    private String preEconomy;
    private Pvp pvp;
    private SkillPoints skillPoints;
    private Profession fishingProfession;
    private Profession woodcuttingProfession;
    private Profession miningProfession;
    private Profession farmingProfession;
    private Profession scribingProfession;
    private Profession jewlingProfession;
    private Profession alchemismProfession;
    private Profession cookingProfession;
    private Profession weaponsmithingProfession;
    private Profession tailoringProfession;
    private Profession woodworkingProfession;
    private Profession armouringProfession;

    public CharacterModel(UUID uuid, UUID ownerUUID, CharacterType type, CharacterReskin reskin, String nickname, int level,
                          int xp, int totalLevel, Gamemode gamemode, int wars, double playtime, int mobsKilled, int chestsFound,
                          int itemsIdentified, int blocksWalked, int logins, int deaths, int discoveries, String preEconomy, Pvp pvp,
                          SkillPoints skillPoints, Profession fishingProfession, Profession woodcuttingProfession, Profession miningProfession,
                          Profession farmingProfession, Profession scribingProfession, Profession jewlingProfession, Profession alchemismProfession,
                          Profession cookingProfession, Profession weaponsmithingProfession, Profession tailoringProfession, Profession woodworkingProfession,
                          Profession armouringProfession) {
        this.uuid = uuid;
        this.ownerUUID = ownerUUID;
        this.type = type;
        this.reskin = reskin;
        this.nickname = nickname;
        this.level = level;
        this.xp = xp;
        this.totalLevel = totalLevel;
        this.gamemode = gamemode;
        this.wars = wars;
        this.playtime = playtime;
        this.mobsKilled = mobsKilled;
        this.chestsFound = chestsFound;
        this.itemsIdentified = itemsIdentified;
        this.blocksWalked = blocksWalked;
        this.logins = logins;
        this.deaths = deaths;
        this.discoveries = discoveries;
        this.preEconomy = preEconomy;
        this.pvp = pvp;
        this.skillPoints = skillPoints;
        this.fishingProfession = fishingProfession;
        this.woodcuttingProfession = woodcuttingProfession;
        this.miningProfession = miningProfession;
        this.farmingProfession = farmingProfession;
        this.scribingProfession = scribingProfession;
        this.jewlingProfession = jewlingProfession;
        this.alchemismProfession = alchemismProfession;
        this.cookingProfession = cookingProfession;
        this.weaponsmithingProfession = weaponsmithingProfession;
        this.tailoringProfession = tailoringProfession;
        this.woodworkingProfession = woodworkingProfession;
        this.armouringProfession = armouringProfession;
    }

    /**
     * Used only for running the migrate and revert methods
     */
    public CharacterModel() {}

    /**
     * The UUID of the model. This does not change
     * @return The UUID of the model
     */
    @Override
    public String uuid() {
        return "47eeb3cc-f23f-4653-86e4-2d0ff39b65ce";
    }

    /**
     * The database, or table that the model is the child of. This is also referred to as
     * the parent of the model.
     * @return The Database object of the table
     */
    @Override
    public PGSQLDatabase database() {
        return new WynncraftDatabase();
    }

    /**
     * The table the migration will be run on inside the parent
     * @return The name of the table in the parent database
     */
    @Override
    public String table() {
        return "characters";
    }

    /**
     * The columns that will appear in order and in the specified parent
     * @return An arraylist of column objects the contain the information of the column
     */
    @Override
    public ArrayList<Column> columns() {
        ArrayList<Column> columns = new ArrayList<>();
        columns.add(new Column("uuid", DataType.UUID));
        columns.add(new Column("ownerUuid", DataType.UUID));
        columns.add(new Column("type", DataType.INTEGER));
        columns.add(new Column("reskin", DataType.INTEGER));
        columns.add(new Column("nickname", DataType.TEXT));
        columns.add(new Column("level", DataType.INTEGER));
        columns.add(new Column("xp", DataType.INTEGER));
        columns.add(new Column("totalLevel", DataType.INTEGER));
        columns.add(new Column("gamemode", DataType.INTEGER));
        columns.add(new Column("wars", DataType.INTEGER));
        columns.add(new Column("playtime", DataType.DOUBLE));
        columns.add(new Column("mobsKilled", DataType.INTEGER));
        columns.add(new Column("chestsFound", DataType.INTEGER));
        columns.add(new Column("itemsIdentified", DataType.INTEGER));
        columns.add(new Column("blocksWalked", DataType.INTEGER));
        columns.add(new Column("logins", DataType.INTEGER));
        columns.add(new Column("deaths", DataType.INTEGER));
        columns.add(new Column("discoveries", DataType.INTEGER));
        columns.add(new Column("preEconomy", DataType.TEXT));
        columns.add(new Column("pvp", DataType.JSONB));
        columns.add(new Column("skillPoints", DataType.JSONB));
        columns.add(new Column("fishingProfession", DataType.JSONB));
        columns.add(new Column("woodcuttingProfession", DataType.JSONB));
        columns.add(new Column("miningProfession", DataType.JSONB));
        columns.add(new Column("farmingProfession", DataType.JSONB));
        columns.add(new Column("scribingProfession", DataType.JSONB));
        columns.add(new Column("jewlingProfession", DataType.JSONB));
        columns.add(new Column("alchemismProfession", DataType.JSONB));
        columns.add(new Column("cookingProfession", DataType.JSONB));
        columns.add(new Column("weaponsmithingProfession", DataType.JSONB));
        columns.add(new Column("tailoringProfession", DataType.JSONB));
        columns.add(new Column("woodworkingProfession", DataType.JSONB));
        columns.add(new Column("armouringProfession", DataType.JSONB));

        return columns;
    }

    /**
     * The name of the model, used in the initialization of the database and debugging
     *
     * @return The name of the model as a string
     */
    @Override
    public String name() {
        return "Character";
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

    @Override
    public boolean automaticallyMigrate() {
        return true;
    }

    @Override
    public Object getModelValue(String key) throws SQLException {
        // Get the value from the database
        Connection connection = this.database().connect(true);
        String condition = "uuid = CAST(? AS UUID)";
        String[] row = PGSQLDatabaseHelpers.selectRow(connection, this.table(), condition, this.getUuid());
        this.database().disconnect();

        // Find column index
        int index = -1;
        for (int i = 0; i < this.columns().size(); i++) {
            if (this.columns().get(i).getName().equalsIgnoreCase(key)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new SQLException("Column not found: " + key);
        }

        if (row == null) {
            return null;
        }

        // Convert the retrieved value to the correct type
        Object value = row[index];

        // Cast properly based on expected return type
        return PGSQLDatabaseHelpers.parseValue(value, this.columns().get(index).getType());
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
                PGSQLDatabaseHelpers.safeGet(this.getUuid()),
                PGSQLDatabaseHelpers.safeGet(this.getOwnerUUID()),
                this.getType().getId(),
                this.getReskin().getId(),
                PGSQLDatabaseHelpers.safeGet(this.getNickname()),
                this.getLevel(),
                this.getXp(),
                this.getTotalLevel(),
                this.getGamemode().getId(),
                this.getWars(),
                this.getPlaytime(),
                this.getMobsKilled(),
                this.getChestsFound(),
                this.getItemsIdentified(),
                this.getBlocksWalked(),
                this.getLogins(),
                this.getDeaths(),
                this.getDiscoveries(),
                PGSQLDatabaseHelpers.safeGet(this.getPreEconomy()),
                this.getPvp().toJson(),
                this.getSkillPoints().toJson(),
                this.getFishingProfession().toJson(),
                this.getWoodcuttingProfession().toJson(),
                this.getMiningProfession().toJson(),
                this.getFarmingProfession().toJson(),
                this.getScribingProfession().toJson(),
                this.getJewlingProfession().toJson(),
                this.getAlchemismProfession().toJson(),
                this.getCookingProfession().toJson(),
                this.getWeaponsmithingProfession().toJson(),
                this.getTailoringProfession().toJson(),
                this.getWoodworkingProfession().toJson(),
                this.getArmouringProfession().toJson()
        };

        Connection connection = this.database().connect(true);

        if (PGSQLDatabaseHelpers.getPrimaryKey(connection, this.table()) == null) {
            PGSQLDatabaseHelpers.setPrimaryKey(connection, this.table(), "uuid");
        }

        try {
            // Check if a row with the same UUID exists
            String condition = "uuid = CAST(? AS UUID)";
            String[] existingRow = PGSQLDatabaseHelpers.selectRow(connection, this.table(), condition, this.getUuid());

            if (existingRow != null) {
                // Update only the columns that have changed, and avoid overwriting with null values
                for (int i = 0; i < columnNames.length; i++) {
                    String columnName = columnNames[i];
                    Object currentValue = values[i];
                    String databaseValue = existingRow[i];

                    // Only update if the current value is different from the database value
                    if (currentValue == null || !currentValue.toString().equals(databaseValue)) {
                        PGSQLDatabaseHelpers.updateColumnValue(connection, this.table(), columnName, currentValue, condition, this.getUuid());
                    }
                }
            } else {
                // Insert a new row if none exists
                PGSQLDatabaseHelpers.insertRow(connection, this.table(), columnNames, values);
            }
        } finally {
            this.database().disconnect();
        }
    }
}
