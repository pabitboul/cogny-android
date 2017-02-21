package com.medicoom.cogny.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medicoom.cogny.R;
import com.medicoom.cogny.adapter.WelcomeAdapter;
import com.medicoom.cogny.model.WelcomeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class WelcomeFragment extends Fragment {
    private RecyclerView rvItems;
    private LinearLayoutManager rvItemsLayoutManager;

    public WelcomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
        rvItems = (RecyclerView) rootView.findViewById(R.id.rv_welcome);
        rvItemsLayoutManager = new LinearLayoutManager(getActivity());
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WelcomeAdapter welcomeAdapter = new WelcomeAdapter(createItems());
        rvItems.setHasFixedSize(true);
        rvItems.setLayoutManager(rvItemsLayoutManager);
        rvItems.setAdapter(welcomeAdapter);
        welcomeAdapter.notifyDataSetChanged();
    }

    private ArrayList<WelcomeItem> createItems(){
        ArrayList<WelcomeItem> welcomeItemArrayList = new ArrayList<>();
        WelcomeItem itemWelcome = new WelcomeItem();
        WelcomeItem itemMedicine = new WelcomeItem();
        WelcomeItem itemDiet = new WelcomeItem();
        WelcomeItem itemCarbohydrates = new WelcomeItem();
        WelcomeItem itemExercise = new WelcomeItem();
        WelcomeItem itemMood = new WelcomeItem();

        itemWelcome.setTitle(getString(R.string.welcome_title));
        itemWelcome.setBody(getString(R.string.welcome_body));
        itemWelcome.setIcon(R.drawable.ic_mood);

        itemMedicine.setTitle(getString(R.string.medication_title));
        itemMedicine.setBody(getString(R.string.medication_body));
        itemMedicine.setIcon(R.drawable.ic_medicine);

        itemDiet.setTitle(getString(R.string.diet_title));
        itemDiet.setBody(getString(R.string.diet_body));
        itemDiet.setIcon(R.drawable.ic_food);

        itemCarbohydrates.setTitle(getString(R.string.carbohydrates_title));
        itemCarbohydrates.setBody(getString(R.string.carbohydrates_body));
        itemCarbohydrates.setIcon(R.drawable.ic_carbohydrates);

        itemExercise.setTitle(getString(R.string.exercise_title));
        itemExercise.setBody(getString(R.string.exercise_body));
        itemExercise.setIcon(R.drawable.ic_exercise);

        itemMood.setTitle(getString(R.string.mood_title));
        itemMood.setBody(getString(R.string.mood_body));
        itemMood.setIcon(R.drawable.ic_mood);

        welcomeItemArrayList.add(itemWelcome);
        welcomeItemArrayList.add(itemMedicine);
        welcomeItemArrayList.add(itemDiet);
        welcomeItemArrayList.add(itemCarbohydrates);
        welcomeItemArrayList.add(itemExercise);
        welcomeItemArrayList.add(itemMood);

        return welcomeItemArrayList;
    }
}
