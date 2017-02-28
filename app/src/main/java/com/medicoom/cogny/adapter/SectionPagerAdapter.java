package com.medicoom.cogny.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.medicoom.cogny.fragment.BaseFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_ITEMS = 3;

    public interface PageType {
        int DIET = 0;
        int EXERCISE = 1;
        int MEDICINES = 2;
    }


    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return BaseFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

}