
package algonquin.cst2335.finalproject.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CurrentType {

    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("Title")
    @Expose
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
