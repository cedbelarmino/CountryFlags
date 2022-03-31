package com.unknown.developer.countryflags.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.unknown.developer.countryflags.R;
import com.unknown.developer.countryflags.model.Country;
import com.unknown.developer.countryflags.view.CountryInfoActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.MyViewHolder> implements Filterable {

    Context context;
    List<Country> countryList;
    List<Country> countryListFiltered;




    public CountryListAdapter(Context context, List<Country> countries) {
        this.context = context;
        this.countryList = countries;
        this.countryListFiltered = countries;
    }
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_list_adapter_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Country country = countryListFiltered.get(position);

        holder.tvCountryName.setText(country.getName());

        Glide.with(context).load(country.getFlags().getPng()).dontTransform().into(holder.imgCountryFlag);


        holder.imgCountryFlag.setOnClickListener(view -> {
            Intent intent = new Intent(context,CountryInfoActivity.class);
            intent.putExtra("CountryName", country.getName());


            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return countryListFiltered.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    countryListFiltered = countryList;
                } else {
                    List<Country> filteredList = new ArrayList<>();
                    for (Country row : countryList) {

                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getAlpha2Code().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getAlpha3Code().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    countryListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = countryListFiltered;
                return filterResults;
            }
            @SuppressWarnings("unchecked")
            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countryListFiltered = (ArrayList<Country>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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