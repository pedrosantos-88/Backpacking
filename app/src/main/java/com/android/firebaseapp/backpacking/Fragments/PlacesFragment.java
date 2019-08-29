package com.android.firebaseapp.backpacking.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.firebaseapp.backpacking.AboutCityActivity;
import com.android.firebaseapp.backpacking.Adapters.RecyclerViewCitiesAdapter;
import com.android.firebaseapp.backpacking.Adapters.ViewPagerAdapter;
import com.android.firebaseapp.backpacking.Model.Cities;
import com.android.firebaseapp.backpacking.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesFragment extends Fragment {



    public PlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_places, container, false);

       loadFragment(new EuropeFragment());



        BottomNavigationView bottomNavigationView = rootView.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        return rootView;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_europe:
                            selectedFragment = new EuropeFragment();
                            loadFragment(selectedFragment);
                            break;
                        case R.id.nav_asia:
                            selectedFragment = new AsiaFragment();
                            loadFragment(selectedFragment);
                            break;
                        case R.id.nav_africa:
                            selectedFragment = new AfricaFragment();
                            loadFragment(selectedFragment);
                            break;
                        case R.id.nav_america:
                            selectedFragment = new AmericaFragment();
                            loadFragment(selectedFragment);
                            break;
                        case R.id.nav_oceania:
                            selectedFragment = new OceaniaFragment();
                            loadFragment(selectedFragment);
                            break;

                    }


                    return true;
                }
            };

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}