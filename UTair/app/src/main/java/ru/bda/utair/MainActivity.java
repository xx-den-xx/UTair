package ru.bda.utair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.layout_forward)
    LinearLayout layoutForward;//layout для выбора города отбытия рейса
    @Bind(R.id.tv_town_forward)
    TextView tvTownForward;//город отбытия рейса
    @Bind(R.id.layout_return)
    LinearLayout layoutReturn;
    @Bind(R.id.tv_town_return)
    TextView tvTownReturn;
    @Bind(R.id.btn_change_position)
    ImageButton btnChangePosition;
    @Bind(R.id.layout_date_forward)
    LinearLayout layoutDateForward;
    @Bind(R.id.tv_date_forward)
    TextView tvDateForward;
    @Bind(R.id.layout_date_return)
    LinearLayout layoutDateReturn;
    @Bind(R.id.tv_date_return)
    TextView tvDateReturn;
    @Bind(R.id.btn_cancel_return)
    ImageButton btnCancelReturn;//кнопка отмены рейса "Обратно"
    @Bind(R.id.layout_add_return)
    LinearLayout layoutAddReturn;//лэйаут для добавления рейса "Обратно"
    @Bind(R.id.btn_plus_adult)
    ImageButton btnPlusAdult;
    @Bind(R.id.btn_plus_child)
    ImageButton btnPlusChild;
    @Bind(R.id.btn_plus_infant)
    ImageButton btnPlusInfant;
    @Bind(R.id.btn_minus_adult)
    ImageButton btnMinusAdult;
    @Bind(R.id.btn_minus_child)
    ImageButton btnMinusChild;
    @Bind(R.id.btn_minus_infant)
    ImageButton btnMinusInfant;
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
    TextView textAdult;
    @Bind(R.id.text_child)
    TextView textChild;
    @Bind(R.id.text_infant)
    TextView textInfant;
    @Bind(R.id.btn_find)
    LinearLayout btnFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
