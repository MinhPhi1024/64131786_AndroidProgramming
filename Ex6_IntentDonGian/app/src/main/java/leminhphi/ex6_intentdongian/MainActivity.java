package leminhphi.ex6_intentdongian;

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
    Button NutMH2, NutMH3;
    void TimDieuKhien(){

        Button NutMH2 = (Button) findViewById(R.id.btn1);
        Button NutMH3 = (Button) findViewById(R.id.btn2);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tìm điều khiển nút bấm
        TimDieuKhien();
        //Gắn bộ lắng nghe sự kiện
        NutMH2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Xử lý chuyển màn hình
                //Bước 1: Tạo 1 intent 2 tham số 1.Mh hiện tại, 2. Mh chuyển đến
                Intent intentMH2 = new Intent(MainActivity.this, MH2Activity.class);
                //Bước 2: Gửi
                startActivity(intentMH2);
            }
        });
        NutMH3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Xử lý chuyển màn hình
                //Bước 1: Tạo 1 intent 2 tham số 1.Mh hiện tại, 2. Mh chuyển đến
                Intent intentMH3 = new Intent(MainActivity.this, MH2Activity.class);
                //Bước 2: Gửi
                startActivity(intentMH3);
            }
        });
    }

}