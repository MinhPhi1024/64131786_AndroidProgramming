package com.ntu.leminhphi.example.mathquizapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.ntu.leminhphi.example.mathquizapp.Adapters.QuestionAdapters;
import com.ntu.leminhphi.example.mathquizapp.Models.QuestionModels;
import com.ntu.leminhphi.example.mathquizapp.databinding.ActivityAdminQuestionsBinding;

import java.util.ArrayList;

public class Admin_Questions extends AppCompatActivity {

    ActivityAdminQuestionsBinding binding;
    FirebaseDatabase datebase;
    FirebaseStorage storage;
    QuestionAdapters adapters;
    ArrayList<QuestionModels> list;
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
        list = new ArrayList<QuestionModels>();

        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.loading_dialog);
        loadingdialog.setCancelable(true);
        loadingdialog.show();

        LinearLayoutManager LayoutManager = new LinearLayoutManager (this);
        binding.rvQuestion.setLayoutManager(LayoutManager);
        adapters = new QuestionAdapters(this,list,tenlopID,themdoituongID);
        binding.rvQuestion.setAdapter(adapters);

        datebase.getReference().child("tenlop").child(tenlopID).child("baihoc").child(themdoituongID)
                .child("cauhoi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    list.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        QuestionModels models = dataSnapshot.getValue(QuestionModels.class);
                        models.setKey(dataSnapshot.getKey());
                        list.add(models);
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

        binding.fbtnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Questions.this, Admin_ThemQuestions.class);
                intent.putExtra("tenlopID",tenlopID);
                intent.putExtra("themdoituongID",themdoituongID);
                startActivity(intent);
            }
        });
    }
}