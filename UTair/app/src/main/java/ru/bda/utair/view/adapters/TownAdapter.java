package ru.bda.utair.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.bda.utair.R;
import ru.bda.utair.presenter.TakeTownPresenter;
import ru.bda.utair.presenter.vo.Town;
import ru.bda.utair.view.TakeTownView;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class TownAdapter extends RecyclerView.Adapter<TownAdapter.ViewHolder> {

    private Context context;
    private List<Town> towns;
    private TakeTownPresenter presenter;

    public TownAdapter(Context context, List<Town> towns, TakeTownPresenter presenter) {
        this.context = context;
        this.towns = towns;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_town, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Town town = towns.get(position);
        holder.town.setText(town.getNameString());
        holder.itemView.setOnClickListener(v -> presenter.onClickTown(town));
    }

    @Override
    public int getItemCount() {
        return towns.size();
    }

    public void setTownList(List<Town> towns) {
        this.towns = towns;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView town;

        public ViewHolder(View itemView) {
            super(itemView);
            town = (TextView) itemView.findViewById(R.id.town);
        }
    }
}
