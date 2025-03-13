package com.iantapply.wynncraft.database.influx;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InfluxDatabase {
    private final Wynncraft plugin = Wynncraft.getInstance();
    private String address = plugin.getConfigurationCore().getString("WYNNCRAFT_INFLUX_ADDRESS");
    private int port = plugin.getConfigurationCore().getInteger("WYNNCRAFT_INFLUX_PORT");
    private String token = plugin.getConfigurationCore().getString("WYNNCRAFT_INFLUX_TOKEN");
    private String org = plugin.getConfigurationCore().getString("WYNNCRAFT_INFLUX_DEFAULT_ORG");
    private String bucket = plugin.getConfigurationCore().getString("WYNNCRAFT_INFLUX_DEFAULT_BUCKET");

    private InfluxDBClient influxClient;
    private WriteApiBlocking influxWriteApi;

    /**
     * The constructor for the InfluxDatabase object
     * @param address The address of the InfluxDB system
     * @param port The port of the InfluxDB system
     * @param token The token to authenticate with the InfluxDB system
     * @param org The organization to connect to
     * @param bucket The bucket to connect to
     */
    public InfluxDatabase(String address, int port, String token, String org, String bucket) {
        this.address = address;
        this.port = port;
        this.token = token;
        this.org = org;
        this.bucket = bucket;
    }

    /**
     * The constructor for the InfluxDatabase object. This defaults connection details to the Wynncraft configuration
     * @param org The organization to connect to
     * @param bucket The bucket to connect to
     */
    public InfluxDatabase(String org, String bucket) {
        this.org = org;
        this.bucket = bucket;
    }

    /**
     * The constructor for the InfluxDatabase object. This defaults all connection details to the Wynncraft configuration.
     * This constructor is NOT preferred as it is recommended to provide an org and bucket to connect to.
     */
    public InfluxDatabase() {}

    /**
     * Initializes the InfluxDB client and establishes a connection to the InfluxDB system. This creates a client and write API connection.
     */
    public void initialize() {
        Logger.log(LoggingLevel.INFO, "Initializing and establishing connection to the InfluxDB system...");
        String formattedConnectionURL = String.format("http://%s:%d", this.getAddress(), this.getPort());
        this.setInfluxClient(InfluxDBClientFactory.create(formattedConnectionURL, this.getToken().toCharArray(), this.getOrg(), this.getBucket()));
        this.setInfluxWriteApi(this.getInfluxClient().getWriteApiBlocking());
        Logger.log(LoggingLevel.SUCCESS, "Successfully connected to the InfluxDB system and established a write API and client connection");
    }

    /**
     * Gets the InfluxDB client connection. If the client is null, it will attempt to reinitialize the client connection.
     * @return The InfluxDB client connection
     */
    public InfluxDBClient getInfluxClient() {
        if (this.influxClient == null) {
            Logger.log(LoggingLevel.WARNING, "InfluxDB client is null. Attempting to reinitialize the client...");
            this.initialize();
        }

        return this.influxClient;
    }

    /**
     * Gets the InfluxDB write API. If the write API is null, it will attempt to reinitialize the write API.
     * @return The InfluxDB write API
     */
    public WriteApiBlocking getInfluxWriteApi() {
        if (this.influxWriteApi == null) {
            Logger.log(LoggingLevel.WARNING, "InfluxDB write API is null. Attempting to reinitialize the write API...");
            this.initialize();
        }

        return this.influxWriteApi;
    }

    /**
     * Gets the InfluxDB query API that is used for executing queries on the InfluxDB system
     * @return The InfluxDB query API
     */
    public QueryApi getQueryApi() {
        return this.getInfluxClient().getQueryApi();
    }

    /**
     * Writes a point to the current bucket, org, and table and assigns a timestamp down to the MS
     * @param point The point to write to the InfluxDB system
     */
    public void writePoint(Point point) {
        point.time(System.currentTimeMillis(), WritePrecision.MS);
        this.getInfluxWriteApi().writePoint(this.getBucket(), this.getOrg(), point);
    }

    /**
     * Closes the InfluxDB client connection and write API connection
     */
    public void close() {
        Logger.log(LoggingLevel.INFO, "Closing the InfluxDB client connection...");
        this.getInfluxClient().close();

        this.setInfluxClient(null);
        this.setInfluxWriteApi(null);

        Logger.log(LoggingLevel.SUCCESS, "Successfully closed the InfluxDB client connection");
    }
}
