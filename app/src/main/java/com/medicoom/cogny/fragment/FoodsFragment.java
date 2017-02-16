package com.medicoom.cogny.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicoom.cogny.R;

public class FoodsFragment extends BaseFragment {

    public FoodsFragment() {
        // Required empty public constructor
    }

    public static FoodsFragment newInstance() {
        FoodsFragment fragment = new FoodsFragment();

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
        return inflater.inflate(R.layout.fragment_foods, container, false);
    }
}