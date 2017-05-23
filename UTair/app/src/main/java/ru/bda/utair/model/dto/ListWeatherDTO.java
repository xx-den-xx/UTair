package ru.bda.utair.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class ListWeatherDTO {
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("main")
    @Expose
    private MainWeatherDTO main;
    @SerializedName("weather")
    @Expose
    private List<WeatherDTO> weather = null;
    @SerializedName("clouds")
    @Expose
    private CloudsDTO clouds;
    @SerializedName("wind")
    @Expose
    private WindDTO wind;
    @SerializedName("sys")
    @Expose
    private SysDTO sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;
    @SerializedName("rain")
    @Expose
    private RainDTO rain;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public MainWeatherDTO getMain() {
        return main;
    }

    public void setMain(MainWeatherDTO main) {
        this.main = main;
    }

    public List<WeatherDTO> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDTO> weather) {
        this.weather = weather;
    }

    public CloudsDTO getClouds() {
        return clouds;
    }

    public void setClouds(CloudsDTO clouds) {
        this.clouds = clouds;
    }

    public WindDTO getWind() {
        return wind;
    }

    public void setWind(WindDTO wind) {
        this.wind = wind;
    }

    public SysDTO getSys() {
        return sys;
    }

    public void setSys(SysDTO sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public RainDTO getRain() {
        return rain;
    }

    public void setRain(RainDTO rain) {
        this.rain = rain;
    }
}
