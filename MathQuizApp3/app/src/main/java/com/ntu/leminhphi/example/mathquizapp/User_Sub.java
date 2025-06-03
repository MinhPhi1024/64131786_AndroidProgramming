package com.ntu.leminhphi.example.mathquizapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import com.ntu.leminhphi.example.mathquizapp.Adapter_User.SubDoiTuongAdapters;
import com.ntu.leminhphi.example.mathquizapp.Models_User.SubDoiTuongModels;
import com.ntu.leminhphi.example.mathquizapp.databinding.ActivityAdminSubBinding;

import java.util.ArrayList;

public class User_Sub extends AppCompatActivity {

    ActivityAdminSubBinding binding;
    FirebaseDatabase datebase;
    FirebaseStorage storage;
    SubDoiTuongAdapters adapters;
    ArrayList<SubDoiTuongModels> list;
    Dialog loadingdialog;
    ImageView imgback;

    private String doituongID, tendoituong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminSubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        datebase = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        doituongID = getIntent().getStringExtra("tenlopID");
        tendoituong = getIntent().getStringExtra("baihoc");

        binding.tvAdmin.setText(tendoituong);

        imgback = (ImageView)findViewById(R.id.imgback);


        list = new ArrayList<>();

        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.loading_dialog);
        loadingdialog.setCancelable(true);
        loadingdialog.show();

        LinearLayoutManager LayoutManager = new LinearLayoutManager (this);
        binding.rvdoituong.setLayoutManager(LayoutManager);
        adapters = new SubDoiTuongAdapters(doituongID,list, this);
        binding.rvdoituong.setAdapter(adapters);

        datebase.getReference().child("tenlop").child(doituongID).child("baihoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    list.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        SubDoiTuongModels subdoiTuongModels = dataSnapshot.getValue(SubDoiTuongModels.class);
                        subdoiTuongModels.setKey(dataSnapshot.getKey());
                        list.add(subdoiTuongModels);
                    }
                    adapters.notifyDataSetChanged();
                    loadingdialog.dismiss();
                }
                else{
                    loadingdialog.dismiss();
                    Toast.makeText(User_Sub.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadingdialog.dismiss();
                Toast.makeText(User_Sub.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

    }
}
