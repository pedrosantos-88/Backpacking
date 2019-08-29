package com.android.firebaseapp.backpacking.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.firebaseapp.backpacking.GalleryActivity;
import com.android.firebaseapp.backpacking.Model.ImageUpload;
import com.android.firebaseapp.backpacking.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewPhotosAdapter extends RecyclerView.Adapter<RecyclerViewPhotosAdapter.ViewHolder> {


    private Context context;
    private List<ImageUpload> imagesList;
    private RecyclerViewPhotosAdapter.onItemClickListener listener;

    public interface onItemClickListener {
        void onItemClick(int position);

        void onItemDelete(int position);
    }

    public void setOnitemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public RecyclerViewPhotosAdapter(Context context, List<ImageUpload> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_photos, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        ImageUpload UploadInfo = imagesList.get(position);



        Glide.with(context).load(UploadInfo.getImageUrl()).into(viewHolder.photo);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = viewHolder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) ;
                    listener.onItemClick(position);
                }
                Intent intent = new Intent(context , GalleryActivity.class);
                intent.putExtra("image_url" , imagesList.get(position).getImageUrl());
                context.startActivity(intent);
            }

        });

        viewHolder.deleteImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = viewHolder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) ;
                    listener.onItemDelete(position);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    @Override
    public long getItemId(int position) {
        return imagesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return imagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView photo;
        public ImageView deleteImageView;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.imageViewMemories);
            deleteImageView = itemView.findViewById(R.id.deleteImageView);



        }
    }
}
