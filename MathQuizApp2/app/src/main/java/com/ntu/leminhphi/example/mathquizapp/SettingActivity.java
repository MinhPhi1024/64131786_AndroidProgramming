package com.ntu.leminhphi.example.mathquizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingActivity extends AppCompatActivity {

    Switch swThongbao;
    Spinner spinnerNgonngu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        swThongbao = findViewById(R.id.swThongbao);
        spinnerNgonngu = findViewById(R.id.spinnerNgonngu);

        // Ngôn ngữ
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.language_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNgonngu.setAdapter(adapter);

        // Xử lý sự kiện (có thể lưu SharedPreferences sau)
        swThongbao.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(this, isChecked ? "Bật thông báo" : "Tắt thông báo", Toast.LENGTH_SHORT).show();
        });

        spinnerNgonngu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String language = parent.getItemAtPosition(pos).toString();
                Toast.makeText(SettingActivity.this, "Đã chọn: " + language, Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}