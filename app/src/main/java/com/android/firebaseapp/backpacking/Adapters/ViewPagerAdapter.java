package com.android.firebaseapp.backpacking.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.firebaseapp.backpacking.Fragments.ExpensesFragment;
import com.android.firebaseapp.backpacking.Fragments.PhotosFragment;
import com.android.firebaseapp.backpacking.Fragments.PlacesFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new ExpensesFragment();
            case 1:
                return new PlacesFragment();
            case 2:
                return new PhotosFragment();
        }
        return null;

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Expenses";
            case 1:
                return "Hidden Gems";
            case 2:
                return "Moments";
            default:
                return null;
        }

    }
}
