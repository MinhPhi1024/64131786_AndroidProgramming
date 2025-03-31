package ntu.leminhphi.example.giuakymauluyentap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnCau1, btnCau2, btnCau3, btnCau4;
    void TimDieuKhien(){
        btnCau1 = (Button) findViewById(R.id.btncau1);
        btnCau2 = (Button) findViewById(R.id.btncau2);
        btnCau3 = (Button) findViewById(R.id.btncau3);
        btnCau4 = (Button) findViewById(R.id.btncau4);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDieuKhien();

        btnCau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cau1 = new Intent(MainActivity.this, Activity_cau1.class);
                startActivity(cau1);
            }
        });
        btnCau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cau2 = new Intent(MainActivity.this, Activity_cau2.class);
                startActivity(cau2);
            }
        });
        btnCau3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cau3 = new Intent(MainActivity.this, Activity_cau3.class);
                startActivity(cau3);
            }
        });
        btnCau4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cau4 = new Intent(MainActivity.this, Activity_cau4.class);
                startActivity(cau4);
            }
        });
    }
}