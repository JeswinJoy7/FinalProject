
package algonquin.cst2335.finalproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AddressInfo {
    @Override
    public String toString() {
        return "AddressInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", town='" + town + '\'' +
                ", stateOrProvince=" + stateOrProvince +
                ", postcode='" + postcode + '\'' +
                ", countryID=" + countryID +
                ", country=" + country +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", contactTelephone1=" + contactTelephone1 +
                ", contactTelephone2=" + contactTelephone2 +
                ", contactEmail=" + contactEmail +
                ", accessComments=" + accessComments +
                ", relatedURL='" + relatedURL + '\'' +
                ", distance=" + distance +
                ", distanceUnit=" + distanceUnit +
                '}';
    }

    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("AddressLine1")
    @Expose
    private String addressLine1;
    @SerializedName("AddressLine2")
    @Expose
    private String addressLine2;
    @SerializedName("Town")
    @Expose
    private String town;
    @SerializedName("StateOrProvince")
    @Expose
    private Object stateOrProvince;
    @SerializedName("Postcode")
    @Expose
    private String postcode;
    @SerializedName("CountryID")
    @Expose
    private Integer countryID;
    @SerializedName("Country")
    @Expose
    private Country country;
    @SerializedName("Latitude")
    @Expose
    private Float latitude;
    @SerializedName("Longitude")
    @Expose
    private Float longitude;
    @SerializedName("ContactTelephone1")
    @Expose
    private String contactTelephone1;
    @SerializedName("ContactTelephone2")
    @Expose
    private String contactTelephone2;
    @SerializedName("ContactEmail")
    @Expose
    private String contactEmail;
    @SerializedName("AccessComments")
    @Expose
    private Object accessComments;
    @SerializedName("RelatedURL")
    @Expose
    private String relatedURL;
    @SerializedName("Distance")
    @Expose
    private Object distance;
    @SerializedName("DistanceUnit")
    @Expose
    private Integer distanceUnit;

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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Object getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(Object stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getContactTelephone1() {
        return contactTelephone1;
    }

    public void setContactTelephone1(String contactTelephone1) {
        this.contactTelephone1 = contactTelephone1;
    }

    public String getContactTelephone2() {
        return contactTelephone2;
    }

    public void setContactTelephone2(String contactTelephone2) {
        this.contactTelephone2 = contactTelephone2;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Object getAccessComments() {
        return accessComments;
    }

    public void setAccessComments(Object accessComments) {
        this.accessComments = accessComments;
    }

    public String getRelatedURL() {
        return relatedURL;
    }

    public void setRelatedURL(String relatedURL) {
        this.relatedURL = relatedURL;
    }

    public Object getDistance() {
        return distance;
    }

    public void setDistance(Object distance) {
        this.distance = distance;
    }

    public Integer getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(Integer distanceUnit) {
        this.distanceUnit = distanceUnit;
    }


}
