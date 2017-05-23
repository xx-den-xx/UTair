package ru.bda.utair.model.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.bda.utair.model.dto.WeatherDataDTO;
import rx.Observable;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public interface ApiWeatherInterface {
    @GET("forecast?")
    Observable<WeatherDataDTO> getWeather(@Query("lat") double lat,
                                          @Query("lon") double lon,
                                          @Query("type") String type,
                                          @Query("units") String units,
                                          @Query("lang") String lang,
                                          @Query("apikey") String apiKey);
}
