package com.ntu.leminhphi.example.mathquizapp;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ntu.leminhphi.example.mathquizapp.Models.DoiTuongModels;
import com.ntu.leminhphi.example.mathquizapp.databinding.ActivityAdminThemBinding;

import java.util.Date;

public class Admin_Them extends AppCompatActivity {

    ActivityAdminThemBinding binding;
    FirebaseDatabase datebase;
    FirebaseStorage storage;
    Dialog loadingdialog;
    Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminThemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        datebase = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.loading_dialog);
        loadingdialog.setCancelable(false);

        binding.fetchimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        binding.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tenlop = binding.edtTenLop.getText().toString();
                if(tenlop.isEmpty()) {
                    binding.edtTenLop.setError("Hãy nhập tên lớp");

                }else if(imgUri == null) {
                    Toast.makeText(Admin_Them.this, "Hãy chọn ảnh", Toast.LENGTH_SHORT).show();

                }else {
                    uploadData( tenlop,imgUri);
                }
            }
        });
    }

    private void uploadData(String tenlop, Uri imgUri) {
        loadingdialog.show();
        final StorageReference reference = storage.getReference().child("tenlop")
                .child(new Date().getTime()+"");
        reference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        DoiTuongModels doiTuongModels = new DoiTuongModels(tenlop,uri.toString());
                        datebase.getReference().child("tenlop").push()
                                .setValue(doiTuongModels).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Admin_Them.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                        loadingdialog.dismiss();
                                        onBackPressed();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        loadingdialog.dismiss();
                                        Toast.makeText(Admin_Them.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {

            if(data != null) {
                imgUri = data.getData();
                binding.imageView2.setImageURI(imgUri);
            }
        }
    }


}