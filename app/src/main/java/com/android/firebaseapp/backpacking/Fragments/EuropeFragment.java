package com.android.firebaseapp.backpacking.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class EuropeFragment extends Fragment {
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
        citiesArrayList.add(new Cities(R.drawable.split, "Split", "Croatia", "Split, a town on Croatia’s Dalmatian Coast, is known for its beaches and the fortresslike complex at its center, Diocletian's Palace, erected by the Roman emperor in the 4th century. Once home to thousands, its sprawling remains include more than 200 buildings. Within its white stone walls and under its courtyards are a cathedral and numerous shops, bars, cafes, hotels and houses."));
        citiesArrayList.add(new Cities(R.drawable.bergamo, "Bergamo", "Italy", "Bergamo is an Italian city northeast of Milan, in the Lombardy region. Its older upper district, called Città Alta, is characterized by cobblestone streets, encircled by Venetian walls and accessible by funicular. It's home to the Duomo di Bergamo, the city cathedral. Also here are the Romanesque Basilica di Santa Maria Maggiore and the grand Cappella Colleoni, a chapel with 18th-century frescoes by Tiepolo."));
        citiesArrayList.add(new Cities(R.drawable.azores, "Azores", "Portugal", "The Azores, an autonomous region of Portugal, are an archipelago in the mid-Atlantic. The islands are characterized by dramatic landscapes, fishing villages, green pastures and hedgerows of blue hydrangeas. São Miguel, the largest, has lake-filled calderas and the Gorreana Tea Plantation. Pico is home to the 2,351m Mt. Pico and vineyards sheltered by boulders."));
        citiesArrayList.add(new Cities(R.drawable.granada, "Granada", "Spain", "Granada is a city in southern Spain’s Andalusia region, in the foothills of the Sierra Nevada mountains. It's known for grand examples of medieval architecture dating to the Moorish occupation, especially the Alhambra. This sprawling hilltop fortress complex encompasses royal palaces, serene patios, and reflecting pools from the Nasrid dynasty, as well as the fountains and orchards of the Generalife gardens."));
        citiesArrayList.add(new Cities(R.drawable.gothenburg, "Gothenburg", "Sweden", "Gothenburg, a major city in Sweden, is situated off the Göta älv river on the country's west coast. An important seaport, it's known for its Dutch-style canals and leafy boulevards like the Avenyn, the city's main thoroughfare, lined with many cafes and shops. Liseberg is a popular amusement park with themed rides, performance venues and a landscaped sculpture garden."));
        citiesArrayList.add(new Cities(R.drawable.nessebar, "Nessebar ", "Bulgaria", "Nessebar is a town in Burgas Province, on Bulgaria’s Black Sea coast. The cobbled streets of the old town, which sits on a promontory, are lined with ruins such as Byzantine-era fortifications and baths. The ruins of the 5th-century Church of St. Sofia include stone columns and large arched windows. The 11th-century Church of St. Stephen houses hundreds of mural paintings and a huge, richly decorated altarpiece."));
        citiesArrayList.add(new Cities(R.drawable.yekaterinburg, "Yekaterinburg", "Russia", "Yekaterinburg is a city in Russia, east of the Ural Mountains. It’s known for the golden-domed Church on the Blood, built in the early 21st century on the site of the 1918 Romanov executions. The Monument to the Founders stands by the banks of the Iset River. Exhibits at the nearby Sverdlovsk Regional Local Lore Museum include the Hall of the Romanovs, with personal items that belonged to the last royal family."));
        citiesArrayList.add(new Cities(R.drawable.leipzig, "Leipzig", "Germany", "Leipzig is the most populous city in the German federal state of Saxony. With a population of 587,857 inhabitants as of 2018, it is Germany's eighth most populous city as well as the second most populous city in the area of former East Germany after Berlin."));
        citiesArrayList.add(new Cities(R.drawable.coimbra, "Coimbra", "Portugal", "Coimbra, a riverfront city in central Portugal and the country’s former capital, is home to a preserved medieval old town and the historic University of Coimbra. Built on the grounds of a former palace, the university is famed for its baroque library, the Biblioteca Joanina, and its 18th-century bell tower. In the city’s old town lies the 12th-century Romanesque cathedral Sé Velha."));
        citiesArrayList.add(new Cities(R.drawable.nantes, "Nantes", "France", "Nantes, a city on the Loire River in the Upper Brittany region of western France, has a long history as a port and industrial center. It's home to the restored, medieval Château des Ducs de Bretagne, where the Dukes of Brittany once lived. The castle is now a local history museum with multimedia exhibits, as well as a walkway atop its fortified ramparts"));
        citiesArrayList.add(new Cities(R.drawable.saaremaa, "Saaremaa Island", "Estonia", "Saaremaa is an Estonian island in the Baltic Sea. It's known for its beaches, spas and traditional villages. Its southern capital, Kuressaare, is home to medieval Kuressaare Castle. It houses the Saaremaa Museum, with exhibits on Estonian culture and handicrafts, plus weapons in the castle fortress. Nearby, the Kaali meteorite crater field includes a cluster of ancient craters and a museum of meteor remains."));
        citiesArrayList.add(new Cities(R.drawable.perast, "Perast", "Montenegro", "Perast is an old town on the Bay of Kotor in Montenegro. It is situated a few kilometres northwest of Kotor and is noted for its proximity to the islets of St. George and Our Lady of the Rocks."
        ));
    }

}
