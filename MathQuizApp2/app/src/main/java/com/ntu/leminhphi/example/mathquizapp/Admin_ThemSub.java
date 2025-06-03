package com.ntu.leminhphi.example.mathquizapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.ntu.leminhphi.example.mathquizapp.Models.SubDoiTuongModels;
import com.ntu.leminhphi.example.mathquizapp.databinding.ActivityAdminThemSubBinding;

public class Admin_ThemSub extends AppCompatActivity {

    ActivityAdminThemSubBinding binding;
    FirebaseDatabase datebase;
    FirebaseStorage storage;
    Dialog loadingdialog;

    private String doituongID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminThemSubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        datebase = FirebaseDatabase.getInstance();
        doituongID = getIntent().getStringExtra("tenlopID");

        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.loading_dialog);
        loadingdialog.setCancelable(false);

        binding.btnSubThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenbai = binding.edtThembai.getText().toString();
                if (tenbai.isEmpty()) {
                    binding.edtThembai.setError("Hãy nhập tên bài");
                }
                else {
                    storeData(tenbai);
                }
            }
        });
    }

    private void storeData(String tenbai) {
        loadingdialog.show();
        SubDoiTuongModels models = new SubDoiTuongModels(tenbai);
        datebase.getReference().child("tenlop").child(doituongID).child("baihoc")
                .push()
                .setValue(models).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        loadingdialog.dismiss();
                        Toast.makeText(Admin_ThemSub.this, "Thêm thành công ", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admin_ThemSub.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}