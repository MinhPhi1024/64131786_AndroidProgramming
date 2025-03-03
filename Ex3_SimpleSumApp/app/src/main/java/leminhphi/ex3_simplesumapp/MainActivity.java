package leminhphi.ex3_simplesumapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Gắn layout tương ứng cho file này
        setContentView(R.layout.activity_main);
    }
    //Đây là bộ lắng nghe và bộ xử lý sự kiện onlick Tính tổng
    public void XuLyCong(View view) {
        //tìm và tham chiếu đến file XML mapping sang Java
        EditText edtA = findViewById(R.id.edtA);
        EditText edtB = findViewById(R.id.edtB);
        EditText edtKQ = findViewById(R.id.edtKQ);
        
        //lấy dữ liệu về
        String strA = edtA.getText().toString();
        String strB = edtB.getText().toString();

        //chuyển dữ liệu sang dữ liệu số
        int a = Integer.parseInt(strA);
        int b = Integer.parseInt(strB);

        //Tính tổng theo yêu cầu
        int tong = a + b;

        //chuyển tổng sang chuỗi
        String strKQ = String.valueOf(tong);

        //Hiển thị kết quả
        edtKQ.setText(String.valueOf(strKQ));








    }
}