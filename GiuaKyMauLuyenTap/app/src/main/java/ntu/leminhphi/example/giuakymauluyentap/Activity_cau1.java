package ntu.leminhphi.example.giuakymauluyentap;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_cau1 extends AppCompatActivity {
    EditText edtSoa, edtSob, edtkq;
    Button btnTinhTong;
    void Timkiem(){
        edtSoa = findViewById(R.id.edtSoa);
        edtSob = findViewById(R.id.edtSob);
        edtkq = findViewById(R.id.edtkq);
        btnTinhTong = findViewById(R.id.btnTinhTong);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau1);
        Timkiem();

    }
}