package com.medicoom.cogny.fragment;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicoom.cogny.R;
import com.medicoom.cogny.adapter.WelcomeAdapter;
import com.medicoom.cogny.model.WelcomeItem;
import com.medicoom.cogny.view.SmoothScrollingLinearLayoutManager;

import java.util.ArrayList;

import static com.medicoom.cogny.fragment.WelcomeFragment.WelcomeItemType.CARBOHYDRATES;
import static com.medicoom.cogny.fragment.WelcomeFragment.WelcomeItemType.DEFAULT;
import static com.medicoom.cogny.fragment.WelcomeFragment.WelcomeItemType.DIET;
import static com.medicoom.cogny.fragment.WelcomeFragment.WelcomeItemType.EXERCISE;
import static com.medicoom.cogny.fragment.WelcomeFragment.WelcomeItemType.MEDICINE;
import static com.medicoom.cogny.fragment.WelcomeFragment.WelcomeItemType.MOOD;


/**
 * A placeholder fragment containing a simple view.
 */
public class WelcomeFragment extends Fragment implements WelcomeAdapter.WelcomeAdapterListener {
    private RecyclerView rvItems;
    private SmoothScrollingLinearLayoutManager smoothScrollingLinearLayoutManager;
    private WelcomeAdapter mWelcomeAdapter;
    private Runnable mRunnable;
    private Handler mHandler;

    interface WelcomeItemType {
        int DEFAULT = 0;
        int MEDICINE = 1;
        int DIET = 2;
        int CARBOHYDRATES = 3;
        int EXERCISE = 4;
        int MOOD = 5;
    }

    public WelcomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
        rvItems = (RecyclerView) rootView.findViewById(R.id.rv_welcome);
        smoothScrollingLinearLayoutManager = new SmoothScrollingLinearLayoutManager(getActivity());
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mWelcomeAdapter = new WelcomeAdapter(this, getActivity());
        mHandler = new Handler();
        rvItems.setHasFixedSize(true);
        rvItems.setLayoutManager(smoothScrollingLinearLayoutManager);
        rvItems.setAdapter(mWelcomeAdapter);
        mWelcomeAdapter.addItem(createWelcomeItem(DEFAULT));

    }

    private WelcomeItem createWelcomeItem(int type) {
        WelcomeItem itemWelcome = new WelcomeItem();
        switch (type) {
            case MEDICINE:
                itemWelcome.setTitle(getString(R.string.medication_title));
                itemWelcome.setBody(getString(R.string.medication_body));
                itemWelcome.setIcon(R.drawable.ic_medicine);
                break;
            case DIET:
                itemWelcome.setTitle(getString(R.string.diet_title));
                itemWelcome.setBody(getString(R.string.diet_body));
                itemWelcome.setIcon(R.drawable.ic_food);
                break;
            case CARBOHYDRATES:
                itemWelcome.setTitle(getString(R.string.carbohydrates_title));
                itemWelcome.setBody(getString(R.string.carbohydrates_body));
                itemWelcome.setIcon(R.drawable.ic_carbohydrates);
                break;
            case EXERCISE:
                itemWelcome.setTitle(getString(R.string.exercise_title));
                itemWelcome.setBody(getString(R.string.exercise_body));
                itemWelcome.setIcon(R.drawable.ic_exercise);
                break;
            case MOOD:
                itemWelcome.setTitle(getString(R.string.mood_title));
                itemWelcome.setBody(getString(R.string.mood_body));
                itemWelcome.setIcon(R.drawable.ic_mood);
                break;
            default:
                itemWelcome.setTitle(getString(R.string.welcome_title));
                itemWelcome.setBody(getString(R.string.welcome_body));
                itemWelcome.setIcon(R.drawable.ic_mood);
        }

        return itemWelcome;
    }

    private ArrayList<WelcomeItem> createWelcomeItems() {
        ArrayList<WelcomeItem> welcomeItemArrayList = new ArrayList<>();
        welcomeItemArrayList.add(createWelcomeItem(DEFAULT));
        welcomeItemArrayList.add(createWelcomeItem(MEDICINE));
        welcomeItemArrayList.add(createWelcomeItem(DIET));
        welcomeItemArrayList.add(createWelcomeItem(CARBOHYDRATES));
        welcomeItemArrayList.add(createWelcomeItem(EXERCISE));
        welcomeItemArrayList.add(createWelcomeItem(MOOD));
        return welcomeItemArrayList;
    }

    @Override
    public void onItemAdded() {
        rvItems.smoothScrollToPosition(mWelcomeAdapter.getItemCount() - 1);
        if (mWelcomeAdapter.getItemCount() < 6) {
            mRunnable = new Runnable() {
                @Override
                public void run() {
                    mWelcomeAdapter.addItem(createWelcomeItem(mWelcomeAdapter.getItemCount()));
                }
            };
            mHandler.postDelayed(mRunnable, 2000);
        } else {
            mHandler.removeCallbacks(mRunnable);
        }
    }
}