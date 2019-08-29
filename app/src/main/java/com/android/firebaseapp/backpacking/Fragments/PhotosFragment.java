package com.android.firebaseapp.backpacking.Fragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.DialogPreference;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.firebaseapp.backpacking.Adapters.RecyclerViewAdapter;
import com.android.firebaseapp.backpacking.Adapters.RecyclerViewPhotosAdapter;
import com.android.firebaseapp.backpacking.MainActivity;
import com.android.firebaseapp.backpacking.Model.ImageUpload;
import com.android.firebaseapp.backpacking.R;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */


public class PhotosFragment extends Fragment {

    private Button UploadButton;
    private ImageView mSelectImageView;
    private Uri mImageUri;
    private StorageReference storageReference;
    private ImageView mDeleteIimageView;
    private DatabaseReference databaseReference;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ProgressBar mProgressBar;
    private ValueEventListener valueEventListenerPhoto;
    private FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private RecyclerView recyclerView;
    private RecyclerViewPhotosAdapter adapter;
    private List<ImageUpload> list = new ArrayList<>();


    public PhotosFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(valueEventListenerPhoto);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_photos, container, false);


        storageReference = FirebaseStorage.getInstance().getReference("uploads").child(currentFirebaseUser.getUid());
        databaseReference = FirebaseDatabase.getInstance().getReference("photos").child(currentFirebaseUser.getUid());
        mSelectImageView = rootView.findViewById(R.id.ButtonChooseImage);
        mDeleteIimageView = rootView.findViewById(R.id.deleteImageView);
        UploadButton = rootView.findViewById(R.id.ButtonUploadImage);

        mProgressBar = rootView.findViewById(R.id.progressBar);
        recyclerView = rootView.findViewById(R.id.recyclerViewPhotos);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        mSelectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChoosedImage();
            }
        });

        UploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                upLoadPhoto();


            }
        });


        valueEventListenerPhoto = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ImageUpload imageUpload = data.getValue(ImageUpload.class);
                    list.add(imageUpload);


                }
                adapter = new RecyclerViewPhotosAdapter(getActivity(), list);
                mProgressBar.setVisibility(View.INVISIBLE);

                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                adapter.setOnitemClickListener(new RecyclerViewPhotosAdapter.onItemClickListener() {
                    String dataKeys;

                    @Override
                    public void onItemClick(int position) {


                    }

                    @Override
                    public void onItemDelete(final int position) {

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
                        alertDialog.setTitle("Delete Photo?");

                        alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                int counter = 0;

                                for (DataSnapshot child : dataSnapshot.getChildren()) {

                                    if (counter == position) {
                                        dataKeys = child.getKey();

                                        break;
                                    }
                                    counter++;
                                }
                                databaseReference.child(dataKeys).removeValue();
                            }
                        });

                        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertDialog.show();

                    }

                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mProgressBar.setVisibility(View.INVISIBLE);

            }
        };


        return rootView;

    }

    private void openChoosedImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Glide.with(this).load(mImageUri).into(mSelectImageView);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(780, 760);
            mSelectImageView.setLayoutParams(layoutParams);
        }
    }


    private void upLoadPhoto() {

        mSelectImageView.setImageResource(R.drawable.land);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 350);
        mSelectImageView.setLayoutParams(layoutParams);

        if (mImageUri == null) {
            mProgressBar.setVisibility(View.INVISIBLE);

            Toast.makeText(getActivity(), "Please insert Photo", Toast.LENGTH_SHORT).show();
        }
        if (mImageUri != null) {
            storageReference.putFile(mImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {

                        throw task.getException();
                    }
                    return storageReference.getDownloadUrl();


                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {

                @Override
                public void onComplete(@NonNull Task<Uri> task) {


                    if (task.isSuccessful()) {

                        Uri downloadUri = task.getResult();
                        ImageUpload upload = new ImageUpload(downloadUri.toString());
                        mProgressBar.setVisibility(View.INVISIBLE);

                        databaseReference.push().setValue(upload);

                    } else {
                        Toast.makeText(getContext(), "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

