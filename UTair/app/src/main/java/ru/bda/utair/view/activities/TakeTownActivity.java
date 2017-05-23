package ru.bda.utair.view.activities;

import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.bda.utair.R;
import ru.bda.utair.constant.Const;
import ru.bda.utair.model.dto.TownDTO;
import ru.bda.utair.model.event.TownEvent;
import ru.bda.utair.presenter.TakeTownPresenter;
import ru.bda.utair.presenter.vo.Town;
import ru.bda.utair.view.TakeTownView;
import ru.bda.utair.view.adapters.TownAdapter;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class TakeTownActivity extends AppCompatActivity implements TakeTownView{
    @Bind(R.id.image_place)
    ImageView imagePlace;
    @Bind(R.id.search_town)
    EditText searchTown;
    @Bind(R.id.placeholder_town)
    TextView placeholderTown;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    private TakeTownPresenter presenter;
    private TownAdapter adapter;
    private LinearLayoutManager manager;
    private List<Town> townList = new ArrayList<>();
    private String route;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_town);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            route = getIntent().getStringExtra(Const.INTENT_TAKE_TOWN);
            setContent(route);
        }
        presenter = new TakeTownPresenter(this);
        searchTown.addTextChangedListener(watcher);

        initRecyclerView();
    }

    private void setContent(String destination) {
        if (destination.equals(Const.FLIGHT_TOWN_UP)) {
            imagePlace.setImageDrawable(getResources().getDrawable(R.drawable.ic_route_big_dot_up_white_8x8));
            placeholderTown.setText(R.string.text_from);
        } else if (destination.equals(Const.FLIGHT_TOWN_DOWN)) {
            imagePlace.setImageDrawable(getResources().getDrawable(R.drawable.ic_route_big_dot_down_white_8x8));
            placeholderTown.setText(R.string.text_to);
        }
    }

    private void initRecyclerView() {
        adapter = new TownAdapter(this, townList, presenter);
        manager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.getTownList(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTowns(List<Town> towns) {
        adapter.setTownList(towns);
    }

    @Override
    public void hideTowns() {
        adapter.setTownList(new ArrayList<Town>());
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
    public void showClickTown(Town town) {
        EventBus.getDefault().post(new TownEvent(town, route));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onStop();
    }
}
