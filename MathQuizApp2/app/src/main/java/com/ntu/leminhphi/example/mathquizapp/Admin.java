package com.ntu.leminhphi.example.mathquizapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.ntu.leminhphi.example.mathquizapp.Adapters.DoiTuongAdapters;
import com.ntu.leminhphi.example.mathquizapp.Models.DoiTuongModels;
import com.ntu.leminhphi.example.mathquizapp.databinding.ActivityAdminBinding;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {

    ActivityAdminBinding binding;
    FloatingActionButton fbtnThem;
    FirebaseDatabase datebase;
    FirebaseStorage storage;
    DoiTuongAdapters adapters;
    ArrayList<DoiTuongModels>list;
    Dialog loadingdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        datebase = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        list = new ArrayList<>();

        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.loading_dialog);
        loadingdialog.setCancelable(true);
        loadingdialog.show();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.rvdoituong.setLayoutManager(gridLayoutManager);
        adapters = new DoiTuongAdapters(this,list);
        binding.rvdoituong.setAdapter(adapters);

        datebase.getReference().child("tenlop").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    list.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        DoiTuongModels doiTuongModels = dataSnapshot.getValue(DoiTuongModels.class);
                        doiTuongModels.setKey(dataSnapshot.getKey());
                        list.add(doiTuongModels);
                    }
                    adapters.notifyDataSetChanged();
                    loadingdialog.dismiss();
                }
                else{
                    loadingdialog.dismiss();
                    Toast.makeText(Admin.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadingdialog.dismiss();
                Toast.makeText(Admin.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        fbtnThem = (FloatingActionButton)findViewById(R.id.fbtnThem);
        fbtnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, Admin_Them.class);
                startActivity(intent);
            }
        });
    }
}