package ru.bda.utair.presenter.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import ru.bda.utair.model.dto.TownDTO;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class Town implements Serializable {

    private String zip = "";
    private String country = "";
    private String localizedCountryName = "";
    private String city = "";
    private Calendar date = null;
    private double lon = 0;
    private int ranking = 0;
    private int id = 0;
    private int memberCount = 0;
    private String nameString = "";
    private double lat = 0;

    public Town() {}

    public Town(TownDTO townDTO) {
        this.zip = townDTO.getZip();
        this.country = townDTO.getCountry();
        this.localizedCountryName = townDTO.getLocalizedCountryName();
        this.city = townDTO.getCity();
        this.lon = townDTO.getLon();
        this.ranking = townDTO.getRanking();
        this.id = townDTO.getId();
        this.memberCount = townDTO.getMemberCount();
        this.nameString = townDTO.getNameString();
        this.lat = townDTO.getLat();
    }

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

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public String getNameString() {
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString = nameString;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Town{" +
                "zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", localizedCountryName='" + localizedCountryName + '\'' +
                ", city='" + city + '\'' +
                ", date=" + date +
                ", lon=" + lon +
                ", ranking=" + ranking +
                ", id=" + id +
                ", memberCount=" + memberCount +
                ", nameString='" + nameString + '\'' +
                ", lat=" + lat +
                '}';
    }
}
