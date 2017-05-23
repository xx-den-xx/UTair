package ru.bda.utair.model;

import ru.bda.utair.model.dto.TownResultDTO;
import ru.bda.utair.model.dto.WeatherDataDTO;
import rx.Observable;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public interface Model {
    Observable<TownResultDTO> getTown(String city);
    Observable<WeatherDataDTO> getWeather(double lat, double lon);
}
