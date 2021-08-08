
package algonquin.cst2335.finalproject.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Level {

    @SerializedName("Comments")
    @Expose
    private String comments;
    @SerializedName("IsFastChargeCapable")
    @Expose
    private Boolean isFastChargeCapable;
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("Title")
    @Expose
    private String title;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getIsFastChargeCapable() {
        return isFastChargeCapable;
    }

    public void setIsFastChargeCapable(Boolean isFastChargeCapable) {
        this.isFastChargeCapable = isFastChargeCapable;
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
