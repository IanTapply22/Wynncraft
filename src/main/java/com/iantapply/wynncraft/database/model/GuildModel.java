package com.iantapply.wynncraft.database.model;

import com.iantapply.wynncraft.database.model.object.guild.BannerLayer;
import com.iantapply.wynncraft.database.model.object.guild.GuildMember;
import com.iantapply.wynncraft.database.model.object.guild.SeasonRank;
import com.iantapply.wynncraft.database.pgsql.PGSQLDatabaseHelpers;
import com.iantapply.wynncraft.database.pgsql.PGSQLDatabase;
import com.iantapply.wynncraft.database.pgsql.table.DataType;
import com.iantapply.wynncraft.database.database.WynncraftDatabase;
import com.iantapply.wynncraft.database.pgsql.table.Column;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.format.NamedTextColor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

@Getter @Setter
public class GuildModel implements Model {
    private UUID uuid;
    private String name;
    private String prefix;
    private int level;
    private int rawXp;
    private int wars;
    private Timestamp created;
    private GuildMember owner;
    private ArrayList<GuildMember> chiefs;
    private ArrayList<GuildMember> strategists;
    private ArrayList<GuildMember> captains;
    private ArrayList<GuildMember> recruiters;
    private ArrayList<GuildMember> recruits;
    private NamedTextColor bannerBase;
    private int bannerTier;
    private ArrayList<BannerLayer> bannerLayers;
    private ArrayList<SeasonRank> seasonRanks;

    public GuildModel(UUID uuid, String name, String prefix, int level, int rawXp, int wars, Timestamp created,
                      GuildMember owner, ArrayList<GuildMember> chiefs, ArrayList<GuildMember> strategists, ArrayList<GuildMember> captains,
                      ArrayList<GuildMember> recruiters, ArrayList<GuildMember> recruits, NamedTextColor bannerBase, int bannerTier,
                      ArrayList<BannerLayer> bannerLayers, ArrayList<SeasonRank> seasonRanks) {
        this.uuid = uuid;
        this.name = name;
        this.prefix = prefix;
        this.level = level;
        this.rawXp = rawXp;
        this.wars = wars;
        this.created = created;
        this.owner = owner;
        this.chiefs = chiefs;
        this.strategists = strategists;
        this.captains = captains;
        this.recruiters = recruiters;
        this.recruits = recruits;
        this.bannerBase = bannerBase;
        this.bannerTier = bannerTier;
        this.bannerLayers = bannerLayers;
        this.seasonRanks = seasonRanks;
    }

    /**
     * Used only for running the migrate and revert methods
     */
    public GuildModel() {}

    /**
     * The UUID of the model. This does not change
     * @return The UUID of the model
     */
    @Override
    public String uuid() {
        return "79251aba-dd91-4298-a8c3-f5b46b2e978e";
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
        return "guilds";
    }

    /**
     * The columns that will appear in order and in the specified parent
     * @return An arraylist of column objects the contain the information of the column
     */
    @Override
    public ArrayList<Column> columns() {
        ArrayList<Column> columns = new ArrayList<>();
        columns.add(new Column("uuid", DataType.UUID));
        columns.add(new Column("name", DataType.TEXT));
        columns.add(new Column("prefix", DataType.TEXT));
        columns.add(new Column("level", DataType.INTEGER));
        columns.add(new Column("rawXp", DataType.INTEGER));
        columns.add(new Column("wars", DataType.INTEGER));
        columns.add(new Column("created", DataType.TIMESTAMP));

        columns.add(new Column("owner", DataType.JSONB));
        columns.add(new Column("chiefs", DataType.JSONB));
        columns.add(new Column("strategists", DataType.JSONB));
        columns.add(new Column("captains", DataType.JSONB));
        columns.add(new Column("recruiters", DataType.JSONB));
        columns.add(new Column("recruits", DataType.JSONB));

        columns.add(new Column("bannerBase", DataType.TEXT));
        columns.add(new Column("bannerTier", DataType.INTEGER));
        columns.add(new Column("bannerLayers", DataType.JSONB));

        columns.add(new Column("seasonRanks", DataType.JSONB));

        return columns;
    }

    /**
     * The name of the model, used in the initialization of the database and debugging
     *
     * @return The name of the model as a string
     */
    @Override
    public String name() {
        return "Guild";
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
                PGSQLDatabaseHelpers.safeGet(this.getName()),
                PGSQLDatabaseHelpers.safeGet(this.getPrefix()),
                this.getLevel(),
                this.getRawXp(),
                this.getWars(),
                PGSQLDatabaseHelpers.safeGet(this.getCreated()),
                PGSQLDatabaseHelpers.safeGet(this.getOwner()),
                PGSQLDatabaseHelpers.safeGet(this.getChiefs()),
                PGSQLDatabaseHelpers.safeGet(this.getStrategists()),
                PGSQLDatabaseHelpers.safeGet(this.getCaptains()),
                PGSQLDatabaseHelpers.safeGet(this.getRecruiters()),
                PGSQLDatabaseHelpers.safeGet(this.getRecruits()),
                PGSQLDatabaseHelpers.safeGet(this.getBannerBase().examinableName()),
                this.getBannerTier(),
                PGSQLDatabaseHelpers.safeGet(this.getBannerLayers()),
                PGSQLDatabaseHelpers.safeGet(this.getSeasonRanks())
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
