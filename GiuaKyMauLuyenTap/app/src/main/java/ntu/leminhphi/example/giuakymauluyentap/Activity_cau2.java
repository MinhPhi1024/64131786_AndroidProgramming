package ntu.leminhphi.example.giuakymauluyentap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Activity_cau2 extends AppCompatActivity {

    ListView lvBank;
    ArrayList<String> dsBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau2);
        lvBank = (ListView) findViewById(R.id.lvBank);
        dsBank = new ArrayList<String>();
        dsBank.add("Vietcombank");
        dsBank.add("Techcombank");
        dsBank.add("Vietcombank");
        dsBank.add("BIDV");
        dsBank.add("VietinBank");
        dsBank.add("Agribank");
        dsBank.add("ACB");
        dsBank.add("MB Bank");
        dsBank.add("Sacombank");
        dsBank.add("VPBank");
        dsBank.add("SHB");
        dsBank.add("HDBank");

        ArrayAdapter<String> adapterBank;
        adapterBank = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dsBank);

        lvBank.setAdapter(adapterBank);
        lvBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bankName = dsBank.get(position);
                //Intent intent = new Intent(Activity_cau2.this, Activity_item.class);
                //Tạo thêm 1 cái trang mới để hiển thị thông tin chi tiết của ngân hàng
            }
        });
    }
}