package ntu.leminhphi.example.mathquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    EditText edtemail, edtpassword;
    Button btnDangnhap;
    TextView tvDangky;
    ProgressBar progressLogin;
    FirebaseAuth mAuth;
    FirebaseFirestore mstore;

    public void TimKiem()
    {
        edtemail = (EditText) findViewById(R.id.edtemail);
        edtpassword = (EditText) findViewById(R.id.edtpassword);
        btnDangnhap = (Button) findViewById(R.id.btnDangnhap);
        tvDangky = (TextView) findViewById(R.id.tvDangky);
        progressLogin = (ProgressBar) findViewById(R.id.progressLogin);
        mAuth = FirebaseAuth.getInstance();
        mstore = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        TimKiem();

        tvDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = edtemail.getText().toString();
                String mPassword = edtpassword.getText().toString();

                if(mEmail.equals("admin") && mPassword.equals("admin")){
                    startActivity(new Intent(Login.this, MainActivity.class));
                    finish();
                }
                if(TextUtils.isEmpty(mEmail)){
                    edtemail.setError("Vui lòng nhập email");
                    return;
                }
                if(TextUtils.isEmpty(mPassword)){
                    edtpassword.setError("Vui lòng nhập mật khẩu");
                    return;
                }

                progressLogin.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, MainActivity.class));
                            finishAffinity();
                        }else{
                            Toast.makeText(Login.this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                            progressLogin.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
    }
}