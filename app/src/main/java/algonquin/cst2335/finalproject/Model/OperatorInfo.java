
package algonquin.cst2335.finalproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OperatorInfo {

    @SerializedName("WebsiteURL")
    @Expose
    private String websiteURL;
    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("PhonePrimaryContact")
    @Expose
    private Object phonePrimaryContact;
    @SerializedName("PhoneSecondaryContact")
    @Expose
    private Object phoneSecondaryContact;
    @SerializedName("IsPrivateIndividual")
    @Expose
    private Boolean isPrivateIndividual;
    @SerializedName("AddressInfo")
    @Expose
    private Object addressInfo;
    @SerializedName("BookingURL")
    @Expose
    private Object bookingURL;
    @SerializedName("ContactEmail")
    @Expose
    private Object contactEmail;
    @SerializedName("FaultReportEmail")
    @Expose
    private Object faultReportEmail;
    @SerializedName("IsRestrictedEdit")
    @Expose
    private Boolean isRestrictedEdit;
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

    public Object getPhonePrimaryContact() {
        return phonePrimaryContact;
    }

    public void setPhonePrimaryContact(Object phonePrimaryContact) {
        this.phonePrimaryContact = phonePrimaryContact;
    }

    public Object getPhoneSecondaryContact() {
        return phoneSecondaryContact;
    }

    public void setPhoneSecondaryContact(Object phoneSecondaryContact) {
        this.phoneSecondaryContact = phoneSecondaryContact;
    }

    public Boolean getIsPrivateIndividual() {
        return isPrivateIndividual;
    }

    public void setIsPrivateIndividual(Boolean isPrivateIndividual) {
        this.isPrivateIndividual = isPrivateIndividual;
    }

    public Object getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(Object addressInfo) {
        this.addressInfo = addressInfo;
    }

    public Object getBookingURL() {
        return bookingURL;
    }

    public void setBookingURL(Object bookingURL) {
        this.bookingURL = bookingURL;
    }

    public Object getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(Object contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Object getFaultReportEmail() {
        return faultReportEmail;
    }

    public void setFaultReportEmail(Object faultReportEmail) {
        this.faultReportEmail = faultReportEmail;
    }

    public Boolean getIsRestrictedEdit() {
        return isRestrictedEdit;
    }

    public void setIsRestrictedEdit(Boolean isRestrictedEdit) {
        this.isRestrictedEdit = isRestrictedEdit;
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
