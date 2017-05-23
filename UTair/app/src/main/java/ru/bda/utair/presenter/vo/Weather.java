package ru.bda.utair.presenter.vo;

import java.io.Serializable;

import ru.bda.utair.model.dto.ListWeatherDTO;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class Weather implements Serializable {

    private String description = "";
    private String icon = "";
    private double temp = 0;
    private double tempMin = 0;
    private double tempMax = 0;
    private int date = 0;
    private String dateTxt = "";

    public Weather() {}

    public Weather(ListWeatherDTO list) {
        this.description = list.getWeather().get(0).getDescription();
        this.icon = list.getWeather().get(0).getIcon();
        this.temp = list.getMain().getTemp();
        this.tempMin = list.getMain().getTempMin();
        this.tempMax = list.getMain().getTempMax();
        this.date = list.getDt();
        this.dateTxt = list.getDtTxt();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDateTxt() {
        return dateTxt;
    }

    public void setDateTxt(String dateTxt) {
        this.dateTxt = dateTxt;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", temp=" + temp +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", date=" + date +
                ", dateTxt='" + dateTxt + '\'' +
                '}';
    }
}
