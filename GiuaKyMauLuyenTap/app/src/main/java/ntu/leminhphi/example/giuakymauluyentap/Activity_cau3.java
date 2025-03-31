package ntu.leminhphi.example.giuakymauluyentap;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Activity_cau3 extends AppCompatActivity {

    LandscapeAdater adater;
    ArrayList<Landscape> dsLandscape;
    RecyclerView reVLand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau3);
        dsLandscape = getLandscape();
        reVLand = findViewById(R.id.reVLand);
        dsLandscape = new ArrayList<>();

    }
    ArrayList<Landscape> getLandscape(){
        ArrayList<Landscape> listData = new ArrayList<Landscape>();
        Landscape landScape1 = new Landscape("Chùa cầu","@mipmap/chua_cau");
        listData.add(landScape1);
        listData.add(new Landscape("Hồ Gươm","@mipmap/ho_guom"));
        listData.add(new Landscape("Cầu Vàng","@mipmap/cau_vang"));
        listData.add(new Landscape("Cột cờ","@mipmap/cot_co"));
        listData.add(new Landscape("Nhà thờ Đức Bà","@mipmap/nha_tho_duc_ba"));
        listData.add(new Landscape("Vịnh Hạ Long","@mipmap/vinh_ha_long"));
        return listData;
    }
}