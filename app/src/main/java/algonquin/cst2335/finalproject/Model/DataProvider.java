
package algonquin.cst2335.finalproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataProvider {

    @SerializedName("WebsiteURL")
    @Expose
    private String websiteURL;
    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("DataProviderStatusType")
    @Expose
    private DataProviderStatusType dataProviderStatusType;
    @SerializedName("IsRestrictedEdit")
    @Expose
    private Boolean isRestrictedEdit;
    @SerializedName("IsOpenDataLicensed")
    @Expose
    private Boolean isOpenDataLicensed;
    @SerializedName("IsApprovedImport")
    @Expose
    private Boolean isApprovedImport;
    @SerializedName("License")
    @Expose
    private String license;
    @SerializedName("DateLastImported")
    @Expose
    private Object dateLastImported;
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("Title")
    @Expose
    private String title;

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public DataProviderStatusType getDataProviderStatusType() {
        return dataProviderStatusType;
    }

    public void setDataProviderStatusType(DataProviderStatusType dataProviderStatusType) {
        this.dataProviderStatusType = dataProviderStatusType;
    }

    public Boolean getIsRestrictedEdit() {
        return isRestrictedEdit;
    }

    public void setIsRestrictedEdit(Boolean isRestrictedEdit) {
        this.isRestrictedEdit = isRestrictedEdit;
    }

    public Boolean getIsOpenDataLicensed() {
        return isOpenDataLicensed;
    }

    public void setIsOpenDataLicensed(Boolean isOpenDataLicensed) {
        this.isOpenDataLicensed = isOpenDataLicensed;
    }

    public Boolean getIsApprovedImport() {
        return isApprovedImport;
    }

    public void setIsApprovedImport(Boolean isApprovedImport) {
        this.isApprovedImport = isApprovedImport;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Object getDateLastImported() {
        return dateLastImported;
    }

    public void setDateLastImported(Object dateLastImported) {
        this.dateLastImported = dateLastImported;
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
