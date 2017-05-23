package ru.bda.utair.presenter;

import android.util.Log;

import java.util.List;

import ru.bda.utair.model.Model;
import ru.bda.utair.model.ModelImpl;
import ru.bda.utair.presenter.mappers.WeatherMappers;
import ru.bda.utair.presenter.vo.Town;
import ru.bda.utair.presenter.vo.Weather;
import ru.bda.utair.view.WeatherFragmentView;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class WeatherFragmentPresenter implements Presenter {

    private Model model = new ModelImpl();

    private WeatherFragmentView view;
    private Subscription subscription = Subscriptions.empty();
    private WeatherMappers mapper = new WeatherMappers();

    public WeatherFragmentPresenter(WeatherFragmentView view) {
        this.view = view;
    }

    public void getWeather(final Town town, final String place) {
        view.startProgress();

        subscription = model.getWeather(town.getLat(), town.getLon())
                .map(mapper)
                .subscribe(new Observer<List<Weather>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.toString());
                        view.stopProgress();
                    }

                    @Override
                    public void onNext(List<Weather> weatherList) {
                        view.saveWeather(weatherList, place);
                        Log.d("weather_log", "take town " + town.getNameString() + " place = " + place);
                    }
                });
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            view.stopProgress();
        }
    }
}
