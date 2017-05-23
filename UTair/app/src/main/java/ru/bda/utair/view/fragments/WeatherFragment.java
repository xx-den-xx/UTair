package ru.bda.utair.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.bda.utair.R;
import ru.bda.utair.presenter.WeatherFragmentPresenter;
import ru.bda.utair.presenter.vo.Town;
import ru.bda.utair.presenter.vo.Weather;
import ru.bda.utair.presenter.vo.WeatherList;
import ru.bda.utair.view.WeatherFragmentView;
import ru.bda.utair.view.adapters.WeatherAdapter;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class WeatherFragment extends Fragment implements WeatherFragmentView{

    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_town_from)
    TextView tvTownFrom;
    @Bind(R.id.tv_town_to)
    TextView tvTownTo;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    private Calendar date;
    private Town townFrom, townTo;
    private WeatherFragmentPresenter presenter;
    private WeatherList weathersFrom;
    private WeatherList weathersTo;

    private LinearLayoutManager layoutManager;
    private WeatherAdapter adapter;

    private final static String PLACE_FROM = "place_from";
    private final static String PLACE_TO = "place_to";

    public WeatherFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        presenter = new WeatherFragmentPresenter(this);
        presenter.getWeather(townFrom, PLACE_FROM);
        presenter.getWeather(townTo, PLACE_TO);
        if (date != null) tvDate.setText(getDate(date, "dd MMMM, EEEE"));
        tvTownFrom.setText(townFrom.getCity());
        tvTownTo.setText(townTo.getCity());
        return view;
    }

    private void setupRecyclerView() {
        adapter = new WeatherAdapter(getContext(), weathersFrom, weathersTo);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    public void setData(Calendar date, Town townFrom, Town townTo) {
        this.date = date;
        this.townFrom = townFrom;
        this.townTo = townTo;
    }

    private String getDate(Calendar date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date.getTime());
    }

    @Override
    public void showError(String error) {
        Snackbar.make(tvDate, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void startProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showWeatherAll() {
        setupRecyclerView();
    }

    @Override
    public void saveWeather(List<Weather> weatherList, String place) {
        if (place.equals(PLACE_FROM)) {
            weathersFrom = new WeatherList(weatherList, townFrom);
        } else if (place.equals(PLACE_TO)) {
            weathersTo = new WeatherList(weatherList, townTo);
        }
        if (isLoadWeathers()) {
            stopProgress();
            showWeatherAll();
            Snackbar.make(progressBar, "load complete", Snackbar.LENGTH_LONG).show();
        }
    }

    public boolean isLoadWeathers() {
        boolean load = false;
        if (weathersFrom != null && weathersTo != null)
            load = true;
        return load;
    }
}
