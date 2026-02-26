package com.example.lab5_starter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {
    private ArrayList<City> cities;
    private Context context;
    private OnCityDeleteListener deleteListener;

    public interface OnCityDeleteListener {
        void onCityDelete(City city, int position);
    }

    public CityArrayAdapter(Context context, ArrayList<City> cities){
        super(context, 0, cities);
        this.cities = cities;
        this.context = context;
    }

    public void setOnCityDeleteListener(OnCityDeleteListener listener) {
        this.deleteListener = listener;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_city, parent, false);
        }

        City city = cities.get(position);
        TextView movieName = view.findViewById(R.id.textCityName);
        TextView movieYear = view.findViewById(R.id.textCityProvince);
        Button deleteButton = view.findViewById(R.id.buttonDeleteCity);

        movieName.setText(city.getName());
        movieYear.setText(city.getProvince());

        deleteButton.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onCityDelete(city, position);
            }
        });

        return view;
    }
}
