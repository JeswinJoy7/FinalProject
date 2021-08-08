
package algonquin.cst2335.finalproject.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Connection {

    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("ConnectionTypeID")
    @Expose
    private Integer connectionTypeID;
    @SerializedName("ConnectionType")
    @Expose
    private ConnectionType connectionType;
    @SerializedName("Reference")
    @Expose
    private Object reference;
    @SerializedName("StatusTypeID")
    @Expose
    private Integer statusTypeID;
    @SerializedName("StatusType")
    @Expose
    private StatusType__1 statusType;
    @SerializedName("LevelID")
    @Expose
    private Integer levelID;
    @SerializedName("Level")
    @Expose
    private Level level;
    @SerializedName("Amps")
    @Expose
    private Object amps;
    @SerializedName("Voltage")
    @Expose
    private Object voltage;
    @SerializedName("PowerKW")
    @Expose
    private float powerKW;
    @SerializedName("CurrentTypeID")
    @Expose
    private Integer currentTypeID;
    @SerializedName("CurrentType")
    @Expose
    private CurrentType currentType;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;
    @SerializedName("Comments")
    @Expose
    private Object comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConnectionTypeID() {
        return connectionTypeID;
    }

    public void setConnectionTypeID(Integer connectionTypeID) {
        this.connectionTypeID = connectionTypeID;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public Object getReference() {
        return reference;
    }

    public void setReference(Object reference) {
        this.reference = reference;
    }

    public Integer getStatusTypeID() {
        return statusTypeID;
    }

    public void setStatusTypeID(Integer statusTypeID) {
        this.statusTypeID = statusTypeID;
    }

    public StatusType__1 getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType__1 statusType) {
        this.statusType = statusType;
    }

    public Integer getLevelID() {
        return levelID;
    }

    public void setLevelID(Integer levelID) {
        this.levelID = levelID;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Object getAmps() {
        return amps;
    }

    public void setAmps(Object amps) {
        this.amps = amps;
    }

    public Object getVoltage() {
        return voltage;
    }

    public void setVoltage(Object voltage) {
        this.voltage = voltage;
    }

    public float getPowerKW() {
        return powerKW;
    }

    public void setPowerKW(Integer powerKW) {
        this.powerKW = powerKW;
    }

    public Integer getCurrentTypeID() {
        return currentTypeID;
    }

    public void setCurrentTypeID(Integer currentTypeID) {
        this.currentTypeID = currentTypeID;
    }

    public CurrentType getCurrentType() {
        return currentType;
    }

    public void setCurrentType(CurrentType currentType) {
        this.currentType = currentType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

}
