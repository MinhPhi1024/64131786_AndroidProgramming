package leminhphi.ex7_intentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityLogin extends AppCompatActivity {

    EditText edtDN, edtMK, edtEmail;
    Button btnXacNhan;
    void TimDieuKhien(){
        EditText edtDN = (EditText) findViewById(R.id.edtUsername);
        EditText edtMK = (EditText) findViewById(R.id.edtPassword);
        EditText edtEmail = (EditText) findViewById(R.id.edtUserEmail);
        Button btnXacNhan = (Button) findViewById(R.id.btnLogin);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TimDieuKhien();

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Xử lý đăng nhập
                //Lấy dữ liệu
                //B1: Tham chiếu với giá trị có sẵn
                //B2: Lấy giá trị
                String TenDangNhap = edtDN.getText().toString();
                String MatKhau = edtMK.getText().toString();
                String Email = edtEmail.getText().toString();
                //Kiểm tra mật khẩu
                if (TenDangNhap.equals("LeMinhPhi") && MatKhau.equals("123456")){//mk đúng/
                    //Chuyển qua màn hình home
                    Intent iQuiz = new Intent(ActivityLogin.this, ActivityHome.class);
                    //Gửi dữ liệu vào IQuiz ở dạng key - value. key được dùng để bên kia lọc dữ liệu
                    iQuiz.putExtra("TenDangNhap", TenDangNhap);
                    iQuiz.putExtra("Email", Email);
                    //Gửi đi
                    startActivity(iQuiz);
                }
                else{
                    //Thông báo lỗi
                    Toast.makeText(ActivityLogin.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}