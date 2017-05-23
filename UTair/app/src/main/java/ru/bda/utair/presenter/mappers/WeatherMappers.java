package ru.bda.utair.presenter.mappers;

import java.util.List;

import ru.bda.utair.model.dto.WeatherDataDTO;
import ru.bda.utair.presenter.vo.Weather;
import rx.Observable;
import rx.functions.Func1;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class WeatherMappers implements Func1<WeatherDataDTO, List<Weather>> {
    @Override
    public List<Weather> call(WeatherDataDTO weatherDataDTO) {
        List<Weather> weathers = Observable.from(weatherDataDTO.getList())
                .map(weatherList -> new Weather(weatherList))
                .toList()
                .toBlocking()
                .first();
        return weathers;
    }
}
