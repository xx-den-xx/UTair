package ru.bda.utair.view;

import java.util.List;

import ru.bda.utair.presenter.vo.Weather;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public interface WeatherFragmentView extends View{
    void startProgress();
    void stopProgress();
    void showWeatherAll();
    void saveWeather(List<Weather> weatherList, String place);
}
