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
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AmericaFragment extends Fragment {

    private ArrayList<Cities> citiesArrayList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewCitiesAdapter adapter;

    @Nullable
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
        citiesArrayList.add(new Cities(R.drawable.salvador, "Salvador", "Brazil", "Salvador, the capital of Brazil’s northeastern state of Bahia, is known for its Portuguese colonial architecture, Afro-Brazilian culture and a tropical coastline. The Pelourinho neighborhood is its historic heart, with cobblestone alleys opening onto large squares, colorful buildings and baroque churches such as São Francisco, featuring gilt woodwork.\n" +
                "From Pelourinho, the Elevador Lacerda art deco elevator descends a cliff to the waterfront and the Mercado Modelo, a large crafts market. Beachside Rio Vermelho is the heart of the city’s restaurant and nightlife scene. At the tip of the peninsula, the Santo Antônio da Barra fort and its accompanying lighthouse and nautical museum overlook All Saints’ Bay. At street stalls all over Salvador, Baianas (women in traditional all-white outfits) sell acarajés and abarás, Afro-Brazilian dishes based on black-eyed peas. Carnaval time brings samba and axé music, massive street parties and parades."));
        citiesArrayList.add(new Cities(R.drawable.paysandu, "Nuevo Paysandú", "Uruguay", "Paysandú is a city in western Uruguay on the Uruguay River, which forms the border with Argentina. Its Historical Museum has exhibitions of weaponry and photos. The nearby Basilica of Our Lady of the Rosary is known for its huge organ. Gravestones and mausoleums adorned with sculptures of angels and saints fill the Paysandú Cemetery. Next to the Municipal Beach, the Río Uruguay Amphitheater stages open-air concerts."));
        citiesArrayList.add(new Cities(R.drawable.barra, "Barranquilla", "Colombia", "Barranquilla, the capital of Colombia’s Atlántico Department, is a bustling seaport flanked by the Magdalena River. The city is known for its enormous Carnival, which brings together flamboyantly costumed performers, elaborate floats and cumbia music. In the chic neighborhood of El Prado, the Museo Romántico showcases artifacts from past festivals and exhibits on famous Colombians, like writer Gabriel García Márquez."));
        citiesArrayList.add(new Cities(R.drawable.trindade, "Trindade and Martim Vaz", "Brazil", "Trindade and Martin Vaz is an archipelago located in the Southern Atlantic Ocean about 1,100 kilometres east of the coast of Espírito Santo, Brazil, which it constitutes a part of. The archipelago has a total area of 10.4 square kilometres and a population of 32."));
        citiesArrayList.add(new Cities(R.drawable.coyoacan, "Coyoacán", "Mexico", "Coyoacán Municipality is a cultural hub with the bright-blue Frida Kahlo Museum, showcasing her life and work, and the National Museum of Popular Culture, displaying folk art like jewelry and ceramics. Diego Rivera’s collection of pre-Hispanic artifacts fills the Museo Anahuacalli. University City is a peaceful district with science museums and theaters, while Estadio Azteca hosts international soccer games."));
        citiesArrayList.add(new Cities(R.drawable.salta, "Salta", "Argentina", "Salta is a provincial capital in mountainous northwestern Argentina. Founded in 1582, it’s known for its Spanish colonial architecture and Andean heritage. The city centers on Plaza 9 de Julio, an elegant, cafe-lined square bordered by the neoclassical Salta Cathedral and El Cabildo, an 18th-century town hall turned historical museum. Museo de Arqueología de Alta Montaña (MAAM) nearby houses Incan artifacts."));
        citiesArrayList.add(new Cities(R.drawable.park, "Dinosaur Provincial Park", "Canada States", "Dinosaur Provincial Park is a UNESCO World Heritage Site located about two-and-a-half hours drive east of Calgary, Alberta, Canada; or 48 kilometres, about a half-hour drive northeast of Brooks. The park is situated in the valley of the Red Deer River, which is noted for its striking badland topography. "));
        citiesArrayList.add(new Cities(R.drawable.cape, "Cape Horn", "Chile", "Cape Horn is a rocky headland on Hornos Island, in southern Chile's Tierra del Fuego archipelago. It's surrounded by wild seas off the southern tip of South America where the Pacific and Atlantic oceans meet. The albatross-shaped Cape Horn Monument commemorates the lives of thousands of seafarers who perished attempting to sail around the cape. A secluded lighthouse and the tiny Stella-Maris Chapel are nearby."));
        citiesArrayList.add(new Cities(R.drawable.supai, "Supai", "United States", "Supai is a census-designated place in Coconino County, Arizona, United States, within the Grand Canyon. As of the 2010 census, the CDP had a population of 208. The capital of the Havasupai Indian Reservation, Supai is the only place in the United States where mail is still carried out by mules"));
    }


}
