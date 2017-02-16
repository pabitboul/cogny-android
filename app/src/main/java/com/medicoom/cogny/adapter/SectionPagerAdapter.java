package com.medicoom.cogny.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.medicoom.cogny.fragment.BaseFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Medicine", "Food", "Gym" };
    private Context mContext;

    public interface PageType{
        int MEDICINES = 0;
        int FOODS = 1;
        int GYMS = 2;
    }


    public SectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return BaseFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}