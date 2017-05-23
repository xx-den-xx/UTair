package ru.bda.utair.presenter.vo;

import java.io.Serializable;
import java.util.List;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class WeatherList implements Serializable {
    private List<Weather> weatherList;
    private Town town;

    public WeatherList() {}

    public WeatherList(List<Weather> weatherList, Town town) {
        this.weatherList = weatherList;
        this.town = town;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "WeatherList{" +
                "weatherList=" + weatherList +
                ", town=" + town +
                '}';
    }
}
