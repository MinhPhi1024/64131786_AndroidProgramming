package ntu.leminhphi.example.lvbank;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewBank;
    ArrayList<String> dsNganHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listViewBank = findViewById(R.id.ListBank);
        //B1.chuẩn bị dữ liệu
        dsNganHang = new ArrayList<String>();
        dsNganHang.add("Vietcombank");
        dsNganHang.add("Techcombank");
        dsNganHang.add("Vietcombank");
        dsNganHang.add("BIDV");
        dsNganHang.add("VietinBank");
        dsNganHang.add("Agribank");
        dsNganHang.add("ACB");
        dsNganHang.add("MB Bank");
        dsNganHang.add("Sacombank");
        dsNganHang.add("VPBank");
        dsNganHang.add("SHB");
        dsNganHang.add("HDBank");
        //B2.tạo adapter
        ArrayAdapter<String> adapterBank;
        adapterBank = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dsNganHang);
        //B3.gán adapter cho listview
        listViewBank.setAdapter(adapterBank);
        //B4. Gắn bộ lắng nghe
        listViewBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //code xử lý khi click vào item
                //chú ý: biến position lưu vị trí của item trong listview
                String bankName = dsNganHang.get(position);
                //Ví dụ đơn gian
                Toast.makeText(MainActivity.this,bankName, Toast.LENGTH_LONG).show();
            }
        });
    }
}