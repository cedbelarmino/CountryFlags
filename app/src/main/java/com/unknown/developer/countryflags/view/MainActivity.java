package com.unknown.developer.countryflags.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.unknown.developer.countryflags.R;
import com.unknown.developer.countryflags.adapter.CountryListAdapter;
import com.unknown.developer.countryflags.core.CountryContract;
import com.unknown.developer.countryflags.core.CountryPresenter;
import com.unknown.developer.countryflags.model.Country;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CountryContract.View {

    public CountryPresenter mPresenter;
    List<Country> countryList;
    CountryListAdapter countryListAdapter;
    RecyclerView recyclerView;
    ShimmerFrameLayout container;

    MenuItem menuItem;
    boolean isflag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(null);

        countryList = new ArrayList<>();
        recyclerView = findViewById(R.id.activity_main_flag_list);
        container = findViewById(R.id.shimmerLayout);


        mPresenter = new CountryPresenter(this);
        mPresenter.getCountry();

    }


    @Override
    public void onSuccess(List<Country> categoryModels) {
        menuItem.setEnabled(true);
        countryList = categoryModels;
        countryListAdapter = new CountryListAdapter(this, countryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(countryListAdapter);

    }

    @Override
    public void onFailed(Throwable throwable) {
        container.stopShimmer();
        container.setVisibility(View.GONE);
        isflag = false;
    }

    @Override
    public void ProcessStart() {
        container.startShimmer();
        container.setVisibility(View.VISIBLE);
        isflag = false;

    }

    @Override
    public void ProcessEnd() {
        container.stopShimmer();
        container.setVisibility(View.GONE);
        isflag = true;

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuItem.setEnabled(isflag);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.search_menu, menu);

        menuItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                countryListAdapter.getFilter().filter(newText);


                return true;
            }
        });


        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.item_search) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}