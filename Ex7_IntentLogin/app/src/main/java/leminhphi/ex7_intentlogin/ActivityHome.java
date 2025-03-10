package leminhphi.ex7_intentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //lấy Intent về
        Intent intentToLogin = getIntent();
        //Lọc ra lấy dữ liệu
        String TenDangNhap = intentToLogin.getStringExtra("TenDangNhap");
        //Gắn vào điều khiển
        TextView tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvUserName.setText(TenDangNhap);

    }
}