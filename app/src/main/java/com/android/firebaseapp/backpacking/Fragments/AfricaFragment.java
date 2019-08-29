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

public class AfricaFragment extends Fragment {

    private static ArrayList<Cities> citiesArrayList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewCitiesAdapter adapter;
    private DatabaseReference database;

    @Nullable
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
        intent.putExtra("name", nameCity);
        intent.putExtra("photo", photo);
        intent.putExtra("country", nameCity);
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
        citiesArrayList.add(new Cities(R.drawable.mafia, "Mafia Island", "Tanzania", "Located just off the coast of Zanzibar and Dar es Salaam, Mafia Island is one of East Africa’s best diving locations. Scuba divers visit this small archipelago from all over the world in order to see an assortment of healthy coral reefs and massive schools of fish. The real reason, however, to visit Mafia Island is to encounter a whale shark. These gentle sea giants are found just a few kilometers from the Mafia Island harbor, and snorkelers can spot up to 20 of these graceful creatures at a time." +
                "Visit Mafia Island in Tanzania and snorkel with whale sharks\n" +
                "\n" +
                "Located just off the coast of Zanzibar and Dar es Salaam, Mafia Island is one of East Africa’s best diving locations. Scuba divers visit this small archipelago from all over the world in order to see an assortment of healthy coral reefs and massive schools of fish. The real reason, however, to visit Mafia Island is to encounter a whale shark. These gentle sea giants are found just a few kilometers from the Mafia Island harbor, and snorkelers can spot up to 20 of these graceful creatures at a time."));
        citiesArrayList.add(new Cities(R.drawable.elgin, "Elgin Valley", "South Africa", "Elgin Valley is one of the most under-marketed wine areas of the Western Cape. It hosts some of South Africa’s award winning wine estates, adrenaline-inducing mountain bike tracks and remote getaway cabins which are all tucked away in a natural paradise. With the valley being slightly less polished than nearby Stellenbosch and Franschhoek, it retains a rustic farm community charm."));
        citiesArrayList.add(new Cities(R.drawable.siwa, "Siwa", "Egypt", "The Siwa Oasis is an urban oasis in Egypt between the Qattara Depression and the Great Sand Sea in the Western Desert, nearly 50 km east of the Libyan border, and 560 km from Cairo. "));
        citiesArrayList.add(new Cities(R.drawable.chef, "Chefchaouen", "Marroco", "Situated in the Rif Mountains, inland from Tangier & Tetouan, and is known for its blue-rinsed eye-catching houses and buldings. The village has been used as a fortress against the Portuguese invasion, which soon formed a part of the Spanish Morocco, hence the Moroccan-Spanish-Portuguese influence."));
        citiesArrayList.add(new Cities(R.drawable.dougga, "Dougga", "Tunisia", "Dougga was a Punic Berber and Roman settlement near present-day Téboursouk in northern Tunisia. The current archaeological site covers 65 hectares. UNESCO qualified Dougga as a World Heritage Site in 1997, believing that it represents \"the best-preserved Roman small town in North Africa\"."));
        citiesArrayList.add(new Cities(R.drawable.wadi, "Wadi Mujib", "Jordan", "Wadi Mujib is a spellbinding canyon that towers above the Mujib River and stretches along 70 kilometers. The Mujib River in Jordan flows into the Dead Sea which is the lowest place on earth. There are many excursions that allow visitors the chance to trek through the water, beneath the towering canyons, and into the Dead Sea itself. Sections of Wadi Mujib also cover the Mujib Biosphere Reserve which is home to a number of rare wildlife species."));
        citiesArrayList.add(new Cities(R.drawable.akagera, "Akagera National Park", "Rwanda", "Tourists in East Africa generally check out the famous safari parks of Tanzania and Kenya. Although the Serengeti and Maasai Mara are impressive beyond belief, the hordes of tourists sitting engine to engine in these serene spaces are much less enjoyable. Plus, it is wildly expensive. For a more quiet, cheaper, and off the beaten path safari experience, check out Akagera National Park in Rwanda. Akagera has recently been able to reclaim its title of a Big Five safari park, with both lions and rhinos reintroduced to Rwanda in the past few years. For just $40 USD in safari fees per day, roam the stunning wetlands and savannah of East Africa’s most underrated safari destination."));

    }

}
