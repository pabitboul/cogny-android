package com.medicoom.cogny.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicoom.cogny.R;

public class MedsFragment extends BaseFragment {


    public MedsFragment() {
        // Required empty public constructor
    }

    public static MedsFragment newInstance() {
        MedsFragment fragment = new MedsFragment();
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
        return inflater.inflate(R.layout.fragment_meds, container, false);
    }
}