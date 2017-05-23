package ru.bda.utair.model;

import ru.bda.utair.constant.Const;
import ru.bda.utair.model.api.ApiModule;
import ru.bda.utair.model.api.ApiTownInterface;
import ru.bda.utair.model.api.ApiWeatherInterface;
import ru.bda.utair.model.dto.TownResultDTO;
import ru.bda.utair.model.dto.WeatherDataDTO;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class ModelImpl implements Model {

    ApiTownInterface townInterface = ApiModule.getApiTownInterface();
    ApiWeatherInterface weatherInterface = ApiModule.getApiWeatherInterface();

    @Override
    public Observable<TownResultDTO> getTown(String city) {
        return townInterface.getTowns(city, Const.API_KEY_TOWN)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<WeatherDataDTO> getWeather(double lat, double lon) {
        return weatherInterface.getWeather(lat, lon, "like", "metric", "ru", Const.API_KEY_WEATHER)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
