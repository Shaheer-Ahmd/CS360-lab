package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    private ListView cityList;
    private ArrayAdapter<City> cityAdapter;
    private ArrayList<City> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        dataList = new ArrayList<>();
        dataList.add(new City("Edmonton", "AB"));
        dataList.add(new City("Vancouver", "BC"));
        dataList.add(new City("Toronto", "ON"));
        dataList.add(new City("Hamilton", "ON"));
        dataList.add(new City("Calgary", "AB"));
        dataList.add(new City("Waterloo", "ON"));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        final Button addCityButton = findViewById(R.id.add_city);
        addCityButton.setOnClickListener(v -> new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY"));

        cityList.setOnItemClickListener((adapterView, view, i, l) -> {
            City selectedCity = dataList.get(i);
            AddCityFragment.newInstance(selectedCity, i).show(getSupportFragmentManager(), "EDIT_CITY");
        });
    }

    @Override
    public void onOkPressed(City newCity) {
        cityAdapter.add(newCity);
    }

    @Override
    public void onEditPressed(City city, int position) {
        City currentCity = dataList.get(position);
        currentCity.setCityName(city.getCityName());
        currentCity.setProvinceName(city.getProvinceName());
        cityAdapter.notifyDataSetChanged();
    }
}