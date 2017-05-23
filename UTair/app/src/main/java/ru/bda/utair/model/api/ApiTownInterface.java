package ru.bda.utair.model.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.bda.utair.model.dto.TownResultDTO;
import rx.Observable;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public interface ApiTownInterface {
    @GET("cities?")
    Observable<TownResultDTO> getTowns(@Query("query") String city, @Query("key")String apiKey);
}
