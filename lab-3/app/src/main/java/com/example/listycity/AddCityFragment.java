package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    interface OnFragmentInteractionListener {
        void onOkPressed(City newCity);
        void onEditPressed(City city, int position);
    }

    private OnFragmentInteractionListener listener;
    private int position = -1;

    static AddCityFragment newInstance(City city, int position) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        args.putInt("position", position);
        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnFragmentInteractionListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_city_fragment_layout, null);
        EditText cityName = view.findViewById(R.id.city_name_editText);
        EditText provinceName = view.findViewById(R.id.province_confirm_editText);

        Bundle bundle = getArguments();
        if (bundle != null) {
            City city = (City) bundle.getSerializable("city");
            position = bundle.getInt("position");
            cityName.setText(city.getCityName());
            provinceName.setText(city.getProvinceName());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add/Edit City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", (dialog, which) -> {
                    String cityStr = cityName.getText().toString();
                    String provinceStr = provinceName.getText().toString();
                    if (position == -1) {
                        listener.onOkPressed(new City(cityStr, provinceStr));
                    } else {
                        listener.onEditPressed(new City(cityStr, provinceStr), position);
                    }
                }).create();
    }
}