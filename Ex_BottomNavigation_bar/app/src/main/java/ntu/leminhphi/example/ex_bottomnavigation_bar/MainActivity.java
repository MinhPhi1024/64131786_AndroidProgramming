package ntu.leminhphi.example.ex_bottomnavigation_bar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tìm điểu khiển bottom_nav
        bottomNavView = findViewById(R.id.bottom_nav);
        //lắng nghe
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int mnuItemID = item.getItemId();
                if (mnuItemID == R.id.mnu_home) {
                    //bằng thì thay fragment

                }
                else if (mnuItemID == R.id.mnu_search) {
                    Toast.makeText(MainActivity.this, "Thay SEARCH", Toast.LENGTH_SHORT).show();
                }
                else if (mnuItemID == R.id.mnu_profile) {
                    Toast.makeText(MainActivity.this, "Thay PROFILE", Toast.LENGTH_SHORT).show();
                }
                else return false;

                return true;
            }
        });
    }
}