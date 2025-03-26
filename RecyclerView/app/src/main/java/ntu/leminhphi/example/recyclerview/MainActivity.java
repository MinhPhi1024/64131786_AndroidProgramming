package ntu.leminhphi.example.recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LandScapeAdapter adapter;
    ArrayList<LandScape> listDataLand;
    RecyclerView recyclerViewLandscape;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    ArrayList<LandScape> getLandScapeData() {
        ArrayList<LandScape> listData = new ArrayList<LandScape>();
        LandScape landScape1 = new LandScape("Chùa cầu","@mipmap/cau_chua");
        listData.add(landScape1);
        listData.add(new LandScape("Hồ Gươm","@mipmap/ho_guom"));
        listData.add(new LandScape("Cầu Vàng","@mipmap/cau_vang"));
        listData.add(new LandScape("Cột cờ","@mipmap/cot_co"));
        listData.add(new LandScape("Nhà thờ Đức Bà","@mipmap/nha_tho_duc_ba"));
        listData.add(new LandScape("Vịnh Hạ Long","@mipmap/vinh_ha_long"));
    }
}