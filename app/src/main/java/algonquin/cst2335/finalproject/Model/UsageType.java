
package algonquin.cst2335.finalproject.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UsageType {

    @SerializedName("IsPayAtLocation")
    @Expose
    private Boolean isPayAtLocation;
    @SerializedName("IsMembershipRequired")
    @Expose
    private Boolean isMembershipRequired;
    @SerializedName("IsAccessKeyRequired")
    @Expose
    private Boolean isAccessKeyRequired;
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("Title")
    @Expose
    private String title;

    public Boolean getIsPayAtLocation() {
        return isPayAtLocation;
    }

    public void setIsPayAtLocation(Boolean isPayAtLocation) {
        this.isPayAtLocation = isPayAtLocation;
    }

    public Boolean getIsMembershipRequired() {
        return isMembershipRequired;
    }

    public void setIsMembershipRequired(Boolean isMembershipRequired) {
        this.isMembershipRequired = isMembershipRequired;
    }

    public Boolean getIsAccessKeyRequired() {
        return isAccessKeyRequired;
    }

    public void setIsAccessKeyRequired(Boolean isAccessKeyRequired) {
        this.isAccessKeyRequired = isAccessKeyRequired;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
