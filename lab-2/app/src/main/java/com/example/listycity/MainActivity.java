package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView cityList;
    private Button addCity;
    private Button deleteCity;
    private Button confirmCity;
    private EditText cityInput;

    private ArrayList<String> dataList;
    private ArrayAdapter<String> cityAdapter;

    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addCity = findViewById(R.id.add_city);
        deleteCity = findViewById(R.id.delete_city);
        confirmCity = findViewById(R.id.confirm_city);
        cityInput = findViewById(R.id.city_input);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
            cityList.setItemChecked(position, true);
        });

        addCity.setOnClickListener(v -> {
            cityInput.setText("");
            cityInput.setVisibility(View.VISIBLE);
            confirmCity.setVisibility(View.VISIBLE);
        });

        confirmCity.setOnClickListener(v -> {
            String name = cityInput.getText().toString().trim();
            if (!name.isEmpty()) {
                dataList.add(name);
                cityAdapter.notifyDataSetChanged();
            }
            cityInput.setVisibility(View.GONE);
            confirmCity.setVisibility(View.GONE);
        });

        deleteCity.setOnClickListener(v -> {
            if (selectedIndex >= 0 && selectedIndex < dataList.size()) {
                dataList.remove(selectedIndex);
                selectedIndex = -1;
                cityList.clearChoices();
                cityAdapter.notifyDataSetChanged();
            }
        });
    }
}
