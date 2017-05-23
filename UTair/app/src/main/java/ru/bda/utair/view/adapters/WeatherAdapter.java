package ru.bda.utair.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ru.bda.utair.R;
import ru.bda.utair.constant.Const;
import ru.bda.utair.presenter.vo.Weather;
import ru.bda.utair.presenter.vo.WeatherList;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{

    private WeatherList weathersFrom;
    private WeatherList weathersTo;
    private Context context;

    public WeatherAdapter(Context context, WeatherList weathersFrom, WeatherList weathersTo) {
        this.weathersFrom = weathersFrom;
        this.weathersTo = weathersTo;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Weather weatherFrom = weathersFrom.getWeatherList().get(position);
        Weather weatherTo = weathersTo.getWeatherList().get(position);
        Calendar date = Calendar.getInstance();

        date.setTimeInMillis(weatherFrom.getDate() * 1000);
        String time = getDate(date, "hh:mm");

        holder.tvTime.setText(time);

        String weatherUrlFrom = Const.WEATHER_ICON_URL + weatherFrom.getIcon() + ".png";
        String weatherUrlTo = Const.WEATHER_ICON_URL + weatherTo.getIcon() + ".png";

        Picasso.with(context)
                .load(weatherUrlFrom)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_gallery)
                .into(holder.iconFrom);
        Picasso.with(context)
                .load(weatherUrlTo)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_gallery)
                .into(holder.iconTo);

        String tempFrom = weatherFrom.getTemp() > 0 ? "+" + weatherFrom.getTemp() : "" + weatherFrom.getTemp();
        String tempTo = weatherTo.getTemp() > 0 ? "+" + weatherTo.getTemp() : "" + weatherTo.getTemp();

        String weatherTxtFrom = "Температура " + tempFrom + " " + Const.CELSIUS + ", " + weatherFrom.getDescription();
        String weatherTxtTo = "Температура " + tempTo + " " + Const.CELSIUS + ", " + weatherTo.getDescription();

        holder.tvWeatherFrom.setText(weatherTxtFrom);
        holder.tvWeatherTo.setText(weatherTxtTo);
    }

    @Override
    public int getItemCount() {
        return weathersFrom != null ? weathersFrom.getWeatherList().size() : 0;
    }

    private String getDate(Calendar date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date.getTime());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTime;
        ImageView iconFrom;
        ImageView iconTo;
        TextView tvWeatherFrom;
        TextView tvWeatherTo;
        LinearLayout layoutFrom;
        LinearLayout layoutTo;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            iconFrom = (ImageView) itemView.findViewById(R.id.icon_from);
            iconTo = (ImageView) itemView.findViewById(R.id.icon_to);
            tvWeatherFrom = (TextView) itemView.findViewById(R.id.tv_weather_from);
            tvWeatherTo = (TextView) itemView.findViewById(R.id.tv_weather_to);
            layoutFrom = (LinearLayout) itemView.findViewById(R.id.layout_from);
            layoutTo = (LinearLayout) itemView.findViewById(R.id.layout_to);
        }
    }
}
