package com.unknown.developer.countryflags.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new CountryPresenter(this);
        mPresenter.getCountry();
        countryList = new ArrayList<>();
        recyclerView = findViewById(R.id.activity_main_flag_list);

    }


    @Override
    public void onSuccess(List<Country> categoryModels) {
        countryList = categoryModels;
        countryListAdapter = new CountryListAdapter(this, countryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(countryListAdapter);

    }

    @Override
    public void onFailed(Throwable throwable) {

    }

    @Override
    public void ProcessStart() {

    }

    @Override
    public void ProcessEnd() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.item_search);

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