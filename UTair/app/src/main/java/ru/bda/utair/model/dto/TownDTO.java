package ru.bda.utair.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class TownDTO {
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("localized_country_name")
    @Expose
    private String localizedCountryName;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("lon")
    @Expose
    private double lon;
    @SerializedName("ranking")
    @Expose
    private int ranking;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("member_count")
    @Expose
    private int memberCount;
    @SerializedName("name_string")
    @Expose
    private String nameString;
    @SerializedName("lat")
    @Expose
    private double lat;

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocalizedCountryName() {
        return localizedCountryName;
    }

    public void setLocalizedCountryName(String localizedCountryName) {
        this.localizedCountryName = localizedCountryName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public String getNameString() {
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString = nameString;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "TownDTO{" +
                "zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", localizedCountryName='" + localizedCountryName + '\'' +
                ", city='" + city + '\'' +
                ", lon=" + lon +
                ", ranking=" + ranking +
                ", id=" + id +
                ", memberCount=" + memberCount +
                ", nameString='" + nameString + '\'' +
                ", lat=" + lat +
                '}';
    }
}
