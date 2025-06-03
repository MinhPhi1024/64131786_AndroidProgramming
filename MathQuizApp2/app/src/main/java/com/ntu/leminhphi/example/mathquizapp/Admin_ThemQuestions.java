package com.ntu.leminhphi.example.mathquizapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.ntu.leminhphi.example.mathquizapp.databinding.ActivityAdminThemQuestionsBinding;

public class Admin_ThemQuestions extends AppCompatActivity {

    ActivityAdminThemQuestionsBinding binding;
    FirebaseDatabase datebase;
    FirebaseStorage storage;
    RadioGroup rgOption;
    LinearLayout linAns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminThemQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}