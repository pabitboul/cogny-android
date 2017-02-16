package com.medicoom.cogny.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicoom.cogny.R;

public class GymsFragment extends BaseFragment {


    public GymsFragment() {
        // Required empty public constructor
    }

    public static GymsFragment newInstance() {
        GymsFragment fragment = new GymsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gyms, container, false);
    }

}