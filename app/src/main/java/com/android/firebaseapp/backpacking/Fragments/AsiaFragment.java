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
import android.widget.Toast;

import com.android.firebaseapp.backpacking.AboutCityActivity;
import com.android.firebaseapp.backpacking.Adapters.RecyclerViewCitiesAdapter;
import com.android.firebaseapp.backpacking.Model.Cities;
import com.android.firebaseapp.backpacking.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AsiaFragment extends Fragment {

    private ArrayList<Cities> citiesArrayList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewCitiesAdapter adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_europe, container, false);


        createArray();
        recyclerView = rootView.findViewById(R.id.recyclerViewCities);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new RecyclerViewCitiesAdapter(getActivity(), citiesArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


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
        int photo = cities.getImageCity();
        Intent intent = new Intent(getContext(), AboutCityActivity.class);
        intent.putExtra("about", aboutCity);
        intent.putExtra("photo", photo);
        startActivity(intent);
    }


    public void createArray() {

        citiesArrayList = new ArrayList<>();
        citiesArrayList.add(new Cities(R.drawable.port, "Port Barton", "Philippines", "Port Barton is a village on the north-west coast of the island of Palawan in the Philippines. It's about 23km north-west of Roxas.\n" +
                "\n" +
                "Port Barton is a quieter, smaller, and more laid-back version of El Nido. The activities you can do here are very similar to those in El Nido (but for about half the price), including for example Tours lettered A through D, kayak rentals, etc.\n" +
                "\n" +
                "Port Barton doesn't usually attract luxury seeking, status vacationers. Instead, it has become a popular choice for backpackers and other adventurous international travellers, who appreciate and enjoy the rural, relaxed atmosphere and natural beauty that this quiet and peaceful beachside village, on the edge of the rainforest, has to offer. "));
        citiesArrayList.add(new Cities(R.drawable.shirakawa, "Shirakawa", "Japan", "Shirakawa is a village located in Ōno District, Gifu Prefecture, Japan. It is best known for being the site of Shirakawa-gō, a small, traditional village showcasing a building style known as gasshō-zukuri. Together with Gokayama in Nanto, Toyama, it is one of UNESCO's World Heritage Sites."));
        citiesArrayList.add(new Cities(R.drawable.jiuzhaigou, "Jiuzhaigou", "China", "Jiuzhaigou National Park is a network of valleys in China’s Sichuan province. In the northern Shuzheng Valley, Nuorilang Waterfall cascades from the edge of a large tree-fringed lake. The Zharu Monastery is a place of worship for the park’s Tibetan villages. In the south, Rize Valley’s mountains are covered with ancient forests. Fallen trees are scattered on the bottom of the striking, multicolored Five Flower Lake."));
        citiesArrayList.add(new Cities(R.drawable.lampang, "Lampang", "Thailand", "Lampang, also called Nakhon Lampang to differentiate from Lampang Province, is the third largest city in northern Thailand and capital of Lampang Province and the Lampang district. Traditional names for Lampang include Wiang Lakon and Khelang Nakhon. The city is a trading and transportation center."));
        citiesArrayList.add(new Cities(R.drawable.ella, "Ella", "Sri Lanka", "Ella is a small town in the Badulla District of Uva Province, Sri Lanka governed by an Urban Council. It is approximately 200 kilometres east of Colombo and is situated at an elevation of 1,041 metres above sea level. The area has a rich bio-diversity, dense with numerous varieties of flora and fauna."));
        citiesArrayList.add(new Cities(R.drawable.jaisalmer, "Jaisalmer", "India", "Jaisalmer is a former medieval trading center and a princely state in the western Indian state of Rajasthan, in the heart of the Thar Desert. Known as the \"Golden City,\" it's distinguished by its yellow sandstone architecture. Dominating the skyline is Jaisalmer Fort, a sprawling hilltop citadel buttressed by 99 bastions. Behind its massive walls stand the ornate Maharaja's Palace and intricately carved Jain temples."));
        citiesArrayList.add(new Cities(R.drawable.phong, "Phong nha", "Vietnam", "Designated a Unesco World Heritage Site in 2003, the remarkable Phong Nha-Ke Bang National Park contains the oldest karst mountains in Asia, formed approximately 400 million years ago. Riddled with hundreds of cave systems – many of extraordinary scale and length – and spectacular underground rivers, Phong Nha is a speleologists’ heaven on earth."));
        citiesArrayList.add(new Cities(R.drawable.galle, "Galle", "Sri Lanka", "Galle is a jewel. A Unesco World Heritage Site, this historic city is a delight to explore on foot, an endlessly exotic old trading port blessed with imposing Dutch-colonial buildings, ancient mosques and churches, grand mansions and museums. Wandering its rambling lanes you'll pass stylish cafes, quirky boutiques and impeccably restored hotels owned by local and foreign artists, writers, photographers and designers."));
        citiesArrayList.add(new Cities(R.drawable.nan, "Nan", "Thailand", "Nan, located in the most eastern part of Northern Thailand in the valley of its namesake river, is a charming little town with a unique historical centre."));
    }


}
