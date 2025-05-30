package leminhphi.ex5_addsubmuldiv_anynomous;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2, edtKQ;
    Button btnCong, btnTru, btnNhan, btnChia;
    void TimDieuKhien(){
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        edtKQ = (EditText) findViewById(R.id.edtKQ);
        btnCong = (Button) findViewById(R.id.btnCong);
        btnTru = (Button) findViewById(R.id.btnTru);
        btnNhan = (Button) findViewById(R.id.btnNhan);
        btnChia = (Button) findViewById(R.id.btnChia);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
        //Gắn bộ lắng nghe sự kiện và code xử lý cho từng btn
        //Cách viết tường mình
        View.OnClickListener boLangNgheCong = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xử lý code
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
                edtKQ.setText(ketqua);
            }
        };
        btnCong.setOnClickListener(boLangNgheCong);
        //Cách viết ẩn danh
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xử lý code
                //Cách viết hàm riêng để gọn code.
                XULU_TRU();
            }
        });
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xử lý code
                //lay du lieu tu edittext
                String s1 = edt1.getText().toString();
                String s2 = edt2.getText().toString();
                float a = Float.parseFloat(s1);
                float b = Float.parseFloat(s2);
                //tinh toan
                float kq = a * b ;
                //hien thi ket qua
                //chuyển đổi từ float sang string
                String ketqua = String.valueOf(kq);
                edtKQ.setText(ketqua);
            }
        });
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xử lý code
                //lay du lieu tu edittext
                String s1 = edt1.getText().toString();
                String s2 = edt2.getText().toString();
                float a = Float.parseFloat(s1);
                float b = Float.parseFloat(s2);
                //tinh toan
                if(b == 0){
                    edtKQ.setText("Không thể chia cho 0");
                    return;
                }
                float kq = a / b ;
                //hien thi ket qua
                //chuyển đổi từ float sang string
                String ketqua = String.valueOf(kq);
                edtKQ.setText(ketqua);
            }
        });

    };
    void XULU_TRU(){
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
        edtKQ.setText(ketqua);
    }
}