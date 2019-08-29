package com.android.firebaseapp.backpacking.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.firebaseapp.backpacking.AboutCityActivity;
import com.android.firebaseapp.backpacking.Adapters.RecyclerViewCitiesAdapter;
import com.android.firebaseapp.backpacking.Model.Cities;
import com.android.firebaseapp.backpacking.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OceaniaFragment extends Fragment {
    private static ArrayList<Cities> citiesArrayList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewCitiesAdapter adapter;
    private DatabaseReference database;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_europe, container, false);


        createArray();
        database = FirebaseDatabase.getInstance().getReference().child("favorite");
        recyclerView = rootView.findViewById(R.id.recyclerViewCities);
        createRecyclerView();


        adapter.setOnitemClickListener(new RecyclerViewCitiesAdapter.onItemClickListener() {


            @Override
            public void onItemClick(int position) {

                aboutCity(position);
            }

        });


        return rootView;


    }


    public void aboutCity(int position) {
        Cities cities = citiesArrayList.get(position);
        String aboutCity = cities.getAboutCity();
        String nameCity = cities.getNameCity();
        int photo = cities.getImageCity();
        Intent intent = new Intent(getContext(), AboutCityActivity.class);
        intent.putExtra("about", aboutCity);
        intent.putExtra("name",nameCity);
        intent.putExtra("photo", photo);
        intent.putExtra("country" , nameCity);
        startActivity(intent);
    }




    public void createRecyclerView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new RecyclerViewCitiesAdapter(getActivity(), citiesArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }



    public void createArray() {


        citiesArrayList = new ArrayList<>();
        citiesArrayList.add(new Cities(R.drawable.coromandel, "Coromandel", "New Zealand", "Coromandel is a coastal town on the Coromandel Peninsula of New Zealand's North Island. It's known for the Driving Creek Railway, a narrow-gauge train ride through mountain forests, past pottery sculptures. Powered by a huge waterwheel, the 1900s Coromandel Gold Stamper Battery still processes gold from rock. The town's gold mining history is chronicled at the Coromandel School of Mines and Historical Museum."));
        citiesArrayList.add(new Cities(R.drawable.moorea,"Moorea-Maiao", "French Polynesia", "Moorea-Maiao is a commune of French Polynesia, an overseas territory of France in the Pacific Ocean. The commune is in the administrative subdivision of the Windward Islands. At the 2017 census it had a population of 17,816"));
        citiesArrayList.add(new Cities(R.drawable.bay, "Bay of Fire", "Tasmania", "The Bay of Fires is a bay on the northeastern coast of Tasmania in Australia, extending from Binalong Bay to Eddystone Point. The bay was given its name in 1773 by Captain Tobias Furneaux in Adventure, who saw the fires of Aboriginal people on the beaches."));
        citiesArrayList.add(new Cities(R.drawable.adelaide, "Adelaide", "Australia", "Just 40 minutes ferry ride from Adelaide is the wilderness of Kangaroo Island -\"a zoo without fences.\" It's a haven for kangaroos, wallabies, possums, bandicoots and platypus, among animals. Visitors can walk amongst a colony of Australian sea lions at the island's Seal Bay. A boardwalk and lookout offers views of the stunning coastal landscape and wildlife. The impressive Remarkable Rocks form an unusual cluster of precariously balanced boulders, becoming the most photographed backdrop of the island, which is definitely one of the hidden tourist gems in Oceania. "));
        citiesArrayList.add(new Cities(R.drawable.perth, "Perth", "Australia", "Perth, capital of Western Australia, sits where the Swan River meets the southwest coast. Sandy beaches line its suburbs, and the huge, riverside Kings Park and Botanic Garden on Mount Eliza offer sweeping views of the city. The Perth Cultural Centre houses the state ballet and opera companies, and occupies its own central precinct, including a theatre, library and the Art Gallery of Western Australia."));

    }

}
