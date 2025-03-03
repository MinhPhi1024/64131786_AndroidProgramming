package leminhphi.ex4_addsubmuldiv_onclick;

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
    //khai bao các đối tương
    EditText edt1, edt2, edtKQ;
    Button btnCong, btnTru, btnNhan, btnChia;

    public void TimDieuKhien() {
        edt1 = (EditText)findViewById(R.id.edt1);
        edt2 = (EditText)findViewById(R.id.edt2);
        edtKQ = (EditText)findViewById(R.id.edtKQ);
        btnCong = (Button)findViewById(R.id.btnCong);
        btnTru = (Button)findViewById(R.id.btnTru);
        btnNhan = (Button)findViewById(R.id.btnNhan);
        btnChia = (Button)findViewById(R.id.btnChia);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
    }
    public void XuLyCong(View v) {
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
    public void XuLyTru(View v) {
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
    public void XuLyNhan(View v) {
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
    public void XuLyChia(View v) {
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
}