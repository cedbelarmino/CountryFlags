package com.unknown.developer.countryflags.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.unknown.developer.countryflags.R;
import com.unknown.developer.countryflags.model.Country;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.MyViewHolder> {

    Context context;
    List<Country> countryList;




    public CountryListAdapter(Context context, List<Country> countries) {
        this.context = context;
        this.countryList = countries;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_list_adapter_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Country country = countryList.get(position);

        holder.tvCountryName.setText(country.getName());

        Glide.with(context)
                .load(country.getFlags().getPng())
                .override(300, 200)
                .into(holder.imgCountryFlag);

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCountryFlag;
        TextView tvCountryName;

        public MyViewHolder(final View itemView) {
            super(itemView);

            tvCountryName = (TextView) itemView.findViewById(R.id.countryName);
            imgCountryFlag = (ImageView) itemView.findViewById(R.id.countryFlag);

        }
    }

}