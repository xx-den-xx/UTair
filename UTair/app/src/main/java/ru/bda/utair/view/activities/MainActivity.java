package ru.bda.utair.view.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.bda.utair.R;
import ru.bda.utair.constant.Const;
import ru.bda.utair.model.event.DataTownsEvent;
import ru.bda.utair.model.event.TownEvent;
import ru.bda.utair.presenter.vo.Town;

public class MainActivity extends AppCompatActivity implements OnClickListener, DatePickerDialog.OnDateSetListener{

    @Bind(R.id.layout_forward)
    LinearLayout layoutForward;//layout для выбора города отбытия рейса
    @Bind(R.id.tv_town_forward)
    TextView tvTownForward;//город отбытия рейса
    @Bind(R.id.layout_return)
    LinearLayout layoutReturn;//layout для выбора города назначения рейса
    @Bind(R.id.tv_town_return)
    TextView tvTownReturn;//город прибытия рейса
    @Bind(R.id.btn_change_position)
    ImageButton btnChangePosition;//кнопка изменения местами городов
    @Bind(R.id.layout_date_forward)
    LinearLayout layoutDateForward;//layout даты отбытия
    @Bind(R.id.tv_date_forward)
    TextView tvDateForward;//дата отбытия
    @Bind(R.id.layout_date_return)
    LinearLayout layoutDateReturn;//layout даты обратного рейса
    @Bind(R.id.tv_date_return)
    TextView tvDateReturn;//дата обратного рейса
    @Bind(R.id.btn_cancel_return)
    ImageButton btnCancelReturn;//кнопка отмены рейса "Обратно"
    @Bind(R.id.layout_add_return)
    LinearLayout layoutAddReturn;//лэйаут для добавления рейса "Обратно"
    @Bind(R.id.btn_plus_adult)
    LinearLayout btnPlusAdult;//кнопка добавления взрослых пассажиров
    @Bind(R.id.btn_plus_child)
    LinearLayout btnPlusChild;//кнопка добавления детей от 2 - 12 лет
    @Bind(R.id.btn_plus_infant)
    LinearLayout btnPlusInfant;//кнопка добавления детей до 2 лет
    @Bind(R.id.btn_minus_adult)
    LinearLayout btnMinusAdult;//кнопка убавления взрослых
    @Bind(R.id.btn_minus_child)
    LinearLayout btnMinusChild;//кнопка убавления детей от 2 - 12 лет
    @Bind(R.id.btn_minus_infant)
    LinearLayout btnMinusInfant;//кнопка убавления детей до 2 лет
    @Bind(R.id.image_adult)
    ImageView imageAdult;
    @Bind(R.id.image_child)
    ImageView imageChild;
    @Bind(R.id.image_infant)
    ImageView imageInfant;
    @Bind(R.id.placeholder_adult)
    TextView placeholderAdult;
    @Bind(R.id.placeholder_child)
    TextView placeholderChild;
    @Bind(R.id.placeholder_infant)
    TextView placeholderInfant;
    @Bind(R.id.text_adult)
    TextView textAdult;//поле количества взрослых
    @Bind(R.id.text_child)
    TextView textChild;//поле количества детей 2 - 12 лет
    @Bind(R.id.text_infant)
    TextView textInfant;//поле количества детей до 2-х лет
    @Bind(R.id.btn_find)
    LinearLayout btnFind;//кнопка поиска рейса

    private int adultInt = 1;//количество взрослых
    private int childInt = 0;//количество детей
    private int infantInt = 0;//количество младенцев
    private int passengersInt = 1;//общее количество пассажиров

    private Town townFrom;//город отправления
    private Town townTo;//город прибытия

    private Calendar dateForward;
    private Calendar dateReturn;

    private int idDatepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        dateForward = Calendar.getInstance();
        tvDateForward.setText(getDate(dateForward, "dd MMM, EE"));

        textAdult.setText(String.valueOf(adultInt));
        textChild.setText(String.valueOf(childInt));
        textInfant.setText(String.valueOf(infantInt));

        btnChangePosition.setBackgroundResource(R.drawable.bg_round_rect_rotated36_disabled);

        layoutForward.setOnClickListener(this);
        layoutReturn.setOnClickListener(this);
        layoutDateForward.setOnClickListener(this);
        layoutDateReturn.setOnClickListener(this);
        layoutAddReturn.setOnClickListener(this);

        btnFind.setOnClickListener(this);
        btnChangePosition.setOnClickListener(this);
        btnCancelReturn.setOnClickListener(this);
        btnPlusAdult.setOnClickListener(this);
        btnMinusAdult.setOnClickListener(this);
        btnPlusChild.setOnClickListener(this);
        btnMinusChild.setOnClickListener(this);
        btnPlusInfant.setOnClickListener(this);
        btnMinusInfant.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String mode;
        switch(v.getId()) {
            case R.id.layout_forward:
                startActivity(Const.FLIGHT_TOWN_UP, TakeTownActivity.class);
                break;
            case R.id.layout_return:
                startActivity(Const.FLIGHT_TOWN_DOWN, TakeTownActivity.class);
                break;
            case R.id.layout_date_forward:
                idDatepicker = R.id.layout_date_forward;
                showDatePicker(Calendar.getInstance());
                break;
            case R.id.layout_date_return:
                idDatepicker = R.id.layout_date_return;
                showDatePicker(dateForward);
                break;
            case R.id.layout_add_return:
                idDatepicker = R.id.layout_date_return;
                showDatePicker(dateForward);
                break;
            case R.id.btn_change_position:
                if (townFrom != null || townTo != null) {
                    Town townTemp = townTo;
                    townTo = townFrom;
                    townFrom = townTemp;

                    tvTownForward.setText(townFrom != null ? townFrom.getNameString()
                            : getString(R.string.text_from));
                    tvTownReturn.setText(townTo != null ? townTo.getNameString()
                            : getString(R.string.text_to));
                }
                break;
            case R.id.btn_cancel_return:
                layoutAddReturn.setVisibility(View.VISIBLE);
                layoutDateReturn.setVisibility(View.GONE);
                btnCancelReturn.setVisibility(View.GONE);
                dateReturn = null;
                break;
            case R.id.btn_find:
                if (townFrom == null) {
                    showSnackbar(getString(R.string.error_empty_town_from));
                } else if (townTo == null) {
                    showSnackbar(getString(R.string.error_empty_town_to));
                } else if (dateReturn == null) {
                    showSnackbar(getString(R.string.error_empty_date_return));
                } else {
                    EventBus.getDefault().post(new DataTownsEvent(townFrom, townTo, dateForward, dateReturn));
                    startActivity("", WeatherActivity.class);
                }
                break;
            case R.id.btn_plus_adult:
                mode = Const.PASS_MODE_ADULT;
                if (isNumberPassengers()) {
                    adultInt = adultInt + 1;
                    showPassengers(mode, adultInt);
                }
                break;
            case R.id.btn_plus_child:
                mode = Const.PASS_MODE_CHILD;
                if (isNumberPassengers()) {
                    childInt = childInt + 1;
                    showPassengers(mode, childInt);
                }
                break;
            case R.id.btn_plus_infant:
                mode = Const.PASS_MODE_INFANT;
                if (infantInt < adultInt) {
                    if (isNumberPassengers()) {
                        infantInt = infantInt + 1;
                        showPassengers(mode, infantInt);
                    }
                } else if (infantInt == adultInt){
                    showToast(R.string.error_infant_count);
                }
                break;
            case R.id.btn_minus_adult:
                mode = Const.PASS_MODE_ADULT;
                if (adultInt > 1) {
                    adultInt = adultInt - 1;
                    showPassengers(mode, adultInt);
                }
                break;
            case R.id.btn_minus_child:
                mode = Const.PASS_MODE_CHILD;
                if (childInt > 0) {
                    childInt = childInt - 1;
                    showPassengers(mode, childInt);
                }
                break;
            case R.id.btn_minus_infant:
                mode = Const.PASS_MODE_INFANT;
                if (infantInt > 0 ) {
                    infantInt = infantInt - 1;
                    showPassengers(mode, infantInt);
                }
                break;
        }
        passengersInt = adultInt + childInt + infantInt;
    }

    private DatePickerDialog showDatePicker(Calendar date) {
        DatePickerDialog dpd = DatePickerDialog.newInstance(this);
        dpd.setAccentColor(getResources().getColor(R.color.colorPrimary));
        dpd.setMinDate(date);
        dpd.show(getFragmentManager(), "datepicker");
        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
        return dpd;
    }

    private String getDate(Calendar date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date.getTime());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTown(TownEvent event) {
        Town town = event.town;
        btnChangePosition.setBackgroundResource(R.drawable.button_change_position);
        if (event.route.equals(Const.FLIGHT_TOWN_UP)) {
            townFrom = town;
            tvTownForward.setText(townFrom.getNameString());
        } else if (event.route.equals(Const.FLIGHT_TOWN_DOWN)) {
            townTo = town;
            tvTownReturn.setText(townTo.getNameString());
        }

    }

    private void startActivity(String route, Class activity) {
        Intent intent = new Intent(this, activity);
        if (!route.equals("")) intent.putExtra(Const.INTENT_TAKE_TOWN, route);
        else {
            intent.putExtra(Const.INTENT_TOWN_FROM, townFrom);
            intent.putExtra(Const.INTENT_TOWN_TO, townTo);
            intent.putExtra(Const.INTENT_DATE_FORWARD, dateForward);
            intent.putExtra(Const.INTENT_DATE_RETURN, dateReturn);
        }
        startActivity(intent);
    }

    private void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coord_layout), message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        View sbView = snackbar.getView();
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) sbView;
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);
        sbView.setBackgroundResource(R.color.colorSnackBar);

        View snackView = getLayoutInflater().inflate(R.layout.snackbar_main, null);
        TextView textViewTop = (TextView) snackView.findViewById(R.id.message);
        textViewTop.setText(message);
        Button action = (Button) snackView.findViewById(R.id.action);
        action.setText("Закрыть");
        action.setOnClickListener(v -> snackbar.dismiss());
        layout.addView(snackView);
        snackbar.setDuration(2000);
        snackbar.show();
    }

    private void showToast(int messageId) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show();
    }

    private boolean isNumberPassengers() {
        if (passengersInt < 9) return true;
        else {
            showToast(R.string.error_passengers_count);
            return false;
        }
    }

    private void showPassengers(String mode, int count) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.text_animation);
        if (mode.equals(Const.PASS_MODE_ADULT)) {
            textAdult.setText(String.valueOf(count));
            textAdult.startAnimation(animation);
            if (count < infantInt) {
                infantInt = count;
                textInfant.setText(String.valueOf(infantInt));
                textInfant.startAnimation(animation);
                passengersInt = adultInt + childInt + infantInt;
            }
            placeholderAdult.setText(count > 1 ? R.string.text_adult_many : R.string.text_adult);
        } else if (mode.equals(Const.PASS_MODE_CHILD)) {
            if (count >= 1) {
                imageChild.setImageDrawable(getResources().getDrawable(R.drawable.ic_booking_counter_child_white));
            } else if (count == 0) {
                imageChild.setImageDrawable(getResources().getDrawable(R.drawable.ic_booking_counter_child_white30));
            }
            setThemeColor(count, textChild, placeholderChild);
            textChild.setText(String.valueOf(count));
            textChild.startAnimation(animation);
        } else if (mode.equals(Const.PASS_MODE_INFANT)) {
            if (count >= 1) {
                imageInfant.setImageDrawable(getResources().getDrawable(R.drawable.ic_booking_counter_infant_white));
            } else if (count == 0) {
                imageInfant.setImageDrawable(getResources().getDrawable(R.drawable.ic_booking_counter_infant_white30));
            }
            setThemeColor(count, textInfant, placeholderInfant);
            textInfant.setText(String.valueOf(count));
            textInfant.startAnimation(animation);
        }
    }

    private void setThemeColor(int count, TextView text, TextView placeholder) {
        if (count >= 1) {
            text.setTextColor(getResources().getColor(R.color.colorAccent));
            placeholder.setTextColor(getResources().getColor(R.color.colorAccent));
        }  else if (count == 0) {
            text.setTextColor(getResources().getColor(R.color.colorDivider));
            placeholder.setTextColor(getResources().getColor(R.color.colorDivider));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("datepicker");
        if (dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar dateResult = Calendar.getInstance();
        dateResult.set(Calendar.YEAR, year);
        dateResult.set(Calendar.MONTH, monthOfYear);
        dateResult.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (R.id.layout_date_forward == idDatepicker) {
            dateForward = dateResult;
            if (dateReturn != null) {
                if (dateReturn.getTimeInMillis() < dateForward.getTimeInMillis()) {
                    layoutAddReturn.setVisibility(View.VISIBLE);
                    layoutDateReturn.setVisibility(View.GONE);
                    btnCancelReturn.setVisibility(View.GONE);
                    dateReturn = null;
                }
            }
            tvDateForward.setText(getDate(dateForward, "dd MMM, EE"));
        } else if (R.id.layout_date_return == idDatepicker) {
            layoutAddReturn.setVisibility(View.GONE);
            layoutDateReturn.setVisibility(View.VISIBLE);
            btnCancelReturn.setVisibility(View.VISIBLE);
            dateReturn = dateResult;
            tvDateReturn.setText(getDate(dateReturn, "dd MMM, EE"));
        }
    }
}
