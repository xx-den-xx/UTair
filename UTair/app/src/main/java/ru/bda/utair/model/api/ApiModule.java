package ru.bda.utair.model.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.bda.utair.BuildConfig;
import ru.bda.utair.constant.Const;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class ApiModule {

    public ApiModule() {

    }

    public static ApiTownInterface getApiTownInterface() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY
                                : HttpLoggingInterceptor.Level.NONE))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_TOWN)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(ApiTownInterface.class);
    }

    public static ApiWeatherInterface getApiWeatherInterface() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY
                                : HttpLoggingInterceptor.Level.NONE))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_WEATHER)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(ApiWeatherInterface.class);
    }
}
