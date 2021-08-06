
package algonquin.cst2335.finalproject.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Country {

    @SerializedName("ISOCode")
    @Expose
    private String iSOCode;
    @SerializedName("ContinentCode")
    @Expose
    private String continentCode;
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("Title")
    @Expose
    private String title;

    public String getISOCode() {
        return iSOCode;
    }

    public void setISOCode(String iSOCode) {
        this.iSOCode = iSOCode;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
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
