package com.ntu.leminhphi.example.mathquizapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.ntu.leminhphi.example.mathquizapp.Adapter_User.DoiTuongAdapters;
import com.ntu.leminhphi.example.mathquizapp.Models_User.DoiTuongModels;
import com.ntu.leminhphi.example.mathquizapp.databinding.ActivityAdminBinding;
import com.ntu.leminhphi.example.mathquizapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseDatabase datebase;
    FirebaseStorage storage;
    DoiTuongAdapters adapters;
    ArrayList<DoiTuongModels> list;
    Dialog loadingdialog;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView imgMenu, imgPerson;
    View header;

    void Timkiem()
    {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navigationView);
        imgMenu = (ImageView)findViewById(R.id.imgMenu);
        imgPerson = (ImageView)findViewById(R.id.imgPerson);
        header = navigationView.getHeaderView(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Timkiem();

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.action_share) {
                    String shareBody = "App hay" + "http://play.google.com/store/apps/details?id" + MainActivity.this.getPackageName();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.action_settings) {
                    Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.action_about) {
                    Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.action_exit) {
                    finishAffinity();

                }
                return false;
            }
        });
    }
}