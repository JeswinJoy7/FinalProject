
package algonquin.cst2335.finalproject.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataProviderStatusType {

    @SerializedName("IsProviderEnabled")
    @Expose
    private Boolean isProviderEnabled;
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("Title")
    @Expose
    private String title;

    public Boolean getIsProviderEnabled() {
        return isProviderEnabled;
    }

    public void setIsProviderEnabled(Boolean isProviderEnabled) {
        this.isProviderEnabled = isProviderEnabled;
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
