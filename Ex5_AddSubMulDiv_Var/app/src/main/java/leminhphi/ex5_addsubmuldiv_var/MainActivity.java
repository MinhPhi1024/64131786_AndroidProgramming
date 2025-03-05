package leminhphi.ex5_addsubmuldiv_var;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edt1, edt2;
    Button btnCong, btnTru, btnNhan, btnChia;
    TextView txtKQ;
    void TimDieuKhien(){
        edt1 = (EditText) findViewById(R.id.edit1);
        edt2 = (EditText) findViewById(R.id.edt2);
        btnCong = (Button) findViewById(R.id.btnCong);
        btnTru = (Button) findViewById(R.id.btnTru);
        btnNhan = (Button) findViewById(R.id.btnNhan);
        btnChia = (Button) findViewById(R.id.btnChia);
        txtKQ = (TextView) findViewById(R.id.txtKQ);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
        btnCong.setOnClickListener(boLangNghe_XuLyCong);
        btnTru.setOnClickListener(boLangNghe_XuLyTru);
        btnNhan.setOnClickListener(boLangNghe_XuLyNhan);
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lay du lieu tu edittext
                String s1 = edt1.getText().toString();
                String s2 = edt2.getText().toString();
                float a = Float.parseFloat(s1);
                float b = Float.parseFloat(s2);
                //tinh toan
                if(b == 0){
                    txtKQ.setText("Không thể chia cho 0");
                    return;
                }
                float kq = a / b ;
                //hien thi ket qua
                //chuyển đổi từ float sang string
                String ketqua = String.valueOf(kq);
                txtKQ.setText(ketqua);
            }
        });
    }
    View.OnClickListener boLangNghe_XuLyCong = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //lay du lieu tu edittext
            String s1 = edt1.getText().toString();
            String s2 = edt2.getText().toString();
            float a = Float.parseFloat(s1);
            float b = Float.parseFloat(s2);
            //tinh toan
            float kq = a + b ;
            //hien thi ket qua
            //chuyển đổi từ float sang string
            String ketqua = String.valueOf(kq);
            txtKQ.setText(ketqua);
        }
    };
    View.OnClickListener boLangNghe_XuLyTru = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //lay du lieu tu edittext
            String s1 = edt1.getText().toString();
            String s2 = edt2.getText().toString();
            float a = Float.parseFloat(s1);
            float b = Float.parseFloat(s2);
            //tinh toan
            float kq = a - b ;
            //hien thi ket qua
            //chuyển đổi từ float sang string
            String ketqua = String.valueOf(kq);
            txtKQ.setText(ketqua);
        }
    };
    View.OnClickListener boLangNghe_XuLyNhan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //lay du lieu tu edittext
            String s1 = edt1.getText().toString();
            String s2 = edt2.getText().toString();
            float a = Float.parseFloat(s1);
            float b = Float.parseFloat(s2);
            //tinh toan
            float kq = a * b;
            //hien thi ket qua
            //chuyển đổi từ float sang string
            String ketqua = String.valueOf(kq);
            txtKQ.setText(ketqua);
        }
    };

}