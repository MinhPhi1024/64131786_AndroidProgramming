package ntu.leminhphi.example.mathquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ntu.leminhphi.example.mathquizapp.databinding.ActivityAdminBinding;

public class Admin extends AppCompatActivity {

    FloatingActionButton fbtnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        fbtnThem = (FloatingActionButton)findViewById(R.id.fbtnThem);
        fbtnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, Admin_ThemActivity.class);
                startActivity(intent);
            }
        });
    }
}