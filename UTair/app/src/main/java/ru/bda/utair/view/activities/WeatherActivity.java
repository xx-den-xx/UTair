package ru.bda.utair.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.bda.utair.R;
import ru.bda.utair.constant.Const;
import ru.bda.utair.model.event.DataTownsEvent;
import ru.bda.utair.presenter.vo.Town;
import ru.bda.utair.view.adapters.ViewPagerAdapter;
import ru.bda.utair.view.fragments.WeatherFragment;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class WeatherActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_town_from)
    TextView tvTownFrom;
    @Bind(R.id.tv_town_to)
    TextView tvTownTo;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private Town townFrom;
    private Town townTo;
    private Calendar dateForward;
    private Calendar dateReturn;
    private ViewPagerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            townFrom = (Town) getIntent().getSerializableExtra(Const.INTENT_TOWN_FROM);
            townTo = (Town) getIntent().getSerializableExtra(Const.INTENT_TOWN_TO);
            dateForward = (Calendar) getIntent().getSerializableExtra(Const.INTENT_DATE_FORWARD);
            dateReturn = (Calendar) getIntent().getSerializableExtra(Const.INTENT_DATE_RETURN);
            tvTownFrom.setText(townFrom.getCity());
            tvTownTo.setText(townTo.getCity());
            setupViewPager(new DataTownsEvent(townFrom, townTo, dateForward, dateReturn));
            setupTabIcon();
        }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_blue_24dp);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupViewPager(DataTownsEvent data) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), data, this);
        WeatherFragment fragmentForward = new WeatherFragment();
        fragmentForward.setData(data.dateForward, data.townFrom, data.townTo);
        adapter.addFragment(fragmentForward);
        WeatherFragment fragmentReturn = new WeatherFragment();
        fragmentReturn.setData(data.dateReturn, data.townFrom, data.townTo);
        adapter.addFragment(fragmentReturn);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupTabIcon() {
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        View tabForward = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        ((TextView) tabForward.findViewById(R.id.tab_text)).setText(R.string.text_tab_forward);
        ((ImageView) tabForward.findViewById(R.id.tab_icon)).setImageDrawable(getResources().getDrawable(R.drawable.tab_forward));
        tabLayout.getTabAt(0).setCustomView(tabForward);

        View tabReturn = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        ((TextView) tabReturn.findViewById(R.id.tab_text)).setText(R.string.text_tab_return);
        ((ImageView) tabReturn.findViewById(R.id.tab_icon)).setImageDrawable(getResources().getDrawable(R.drawable.tab_return));
        tabLayout.getTabAt(1).setCustomView(tabReturn);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(DataTownsEvent event) {
        this.townFrom = event.townFrom;
        this.townTo = event.townTo;
        this.dateForward = event.dateForward;
        this.dateReturn = event.dateReturn;
        Log.d("weather_log", "Город отбытия" + townFrom.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
