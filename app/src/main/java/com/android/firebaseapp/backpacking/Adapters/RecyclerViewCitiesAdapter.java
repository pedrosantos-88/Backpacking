package com.android.firebaseapp.backpacking.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.firebaseapp.backpacking.Model.Cities;
import com.android.firebaseapp.backpacking.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewCitiesAdapter extends RecyclerView.Adapter<RecyclerViewCitiesAdapter.CitiesViewHolder> {
    public Context mContext;
    private ArrayList<Cities> mCitiesArraylist;
    private onItemClickListener listener;


    public interface onItemClickListener {


        void onItemClick(int position);

    }

    public void setOnitemClickListener(RecyclerViewCitiesAdapter.onItemClickListener listener) {
        this.listener = listener;

    }


    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_cities, viewGroup, false);

        CitiesViewHolder viewHolder = new CitiesViewHolder(view);

        return viewHolder;
    }

    public RecyclerViewCitiesAdapter(Context context, ArrayList<Cities> citiesArrayList) {
        mContext = context;
        mCitiesArraylist = citiesArrayList;

    }

    @Override
    public void onBindViewHolder(@NonNull final CitiesViewHolder citiesViewHolder, final int position) {
        final Cities currentCity = mCitiesArraylist.get(position);

        citiesViewHolder.mTextViewCity.setText(currentCity.getNameCity());
        citiesViewHolder.mTextViewCountry.setText(currentCity.getNameCountry());
        Glide.with(mContext).load(currentCity.getImageCity()).into(citiesViewHolder.mImageViewCity);
        citiesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                if (listener != null) {
                    int position = citiesViewHolder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) ;
                    listener.onItemClick(position);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return mCitiesArraylist.size();
    }

    public static class CitiesViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageViewCity;
        public TextView mTextViewCity;
        public TextView mTextViewCountry;


        public CitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewCity = itemView.findViewById(R.id.ImageViewCity);
            mTextViewCity = itemView.findViewById(R.id.TextViewCity);
            mTextViewCountry = itemView.findViewById(R.id.textViewCountry);



        }


    }

}
