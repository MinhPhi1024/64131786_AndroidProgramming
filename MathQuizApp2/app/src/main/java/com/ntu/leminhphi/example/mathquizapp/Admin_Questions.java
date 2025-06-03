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
import com.ntu.leminhphi.example.mathquizapp.Adapters.QuestionAdapters;
import com.ntu.leminhphi.example.mathquizapp.Models.DoiTuongModels;
import com.ntu.leminhphi.example.mathquizapp.Models.QuestionModels;
import com.ntu.leminhphi.example.mathquizapp.databinding.ActivityAdminBinding;
import com.ntu.leminhphi.example.mathquizapp.databinding.ActivityAdminQuestionsBinding;

import java.util.ArrayList;

public class Admin_Questions extends AppCompatActivity {

    ActivityAdminQuestionsBinding binding;
    FloatingActionButton fbtnThem;
    FirebaseDatabase datebase;
    FirebaseStorage storage;
    QuestionAdapters adapters;
    ArrayList<DoiTuongModels> list;
    Dialog loadingdialog;

    private String tenlopID,themdoituongID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tenlopID = getIntent().getStringExtra("tenlopID");
        themdoituongID = getIntent().getStringExtra("themdoituongID");

        datebase = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        list = new ArrayList<>();

        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.loading_dialog);
        loadingdialog.setCancelable(true);
        loadingdialog.show();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.rvQuestion.setLayoutManager(gridLayoutManager);
        adapters = new QuestionAdapters(this,list,tenlopID,themdoituongID);
        binding.rvQuestion.setAdapter(adapters);

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
                    Toast.makeText(Admin_Questions.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadingdialog.dismiss();
                Toast.makeText(Admin_Questions.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        fbtnThem = (FloatingActionButton)findViewById(R.id.fbtnThem);
        fbtnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Questions.this, Admin_ThemQuestions.class);
                startActivity(intent);
            }
        });
    }
}