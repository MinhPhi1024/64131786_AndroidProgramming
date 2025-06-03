package com.ntu.leminhphi.example.mathquizapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    public static final String TAG = "TAG";

    EditText edtusername, edtemail, edtphone, edtpassword, edtpassAgain;
    TextView tvDangnhap;
    Button btnDangky;
    ProgressBar progressLogin;
    FirebaseAuth mAuth;
    FirebaseFirestore mstore;
    Dialog loadingdialog;

    public void TimKiem()
    {
        edtusername = (EditText) findViewById(R.id.edtusername);
        edtemail = (EditText) findViewById(R.id.edtemail);
        edtpassword = (EditText) findViewById(R.id.edtpassword);
        edtphone = (EditText) findViewById(R.id.edtphone);
        edtpassAgain = (EditText) findViewById(R.id.edtpassAgain);
        tvDangnhap = (TextView) findViewById(R.id.tvDangnhap);
        btnDangky = (Button) findViewById(R.id.btnDangky);
        progressLogin = (ProgressBar) findViewById(R.id.progressLogin);
        mAuth = FirebaseAuth.getInstance();
        mstore = FirebaseFirestore.getInstance();
        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.loading_dialog);
        loadingdialog.setCancelable(false);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        TimKiem();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(SignUp.this, MainActivity.class));
            finishAffinity();
        }

        tvDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Login.class));
                finish();
            }
        });

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = edtusername.getText().toString();
                String mEmail = edtemail.getText().toString().trim();
                String mPhone = edtphone.getText().toString().trim();
                String mPassword = edtpassword.getText().toString();
                String mPassAgain = edtpassAgain.getText().toString();

                if(TextUtils.isEmpty(mUsername)){
                    edtusername.setError("Vui lòng nhập tên đăng nhập");
                    return;
                }
                if(TextUtils.isEmpty(mEmail)){
                    edtemail.setError("Vui lòng nhập email");
                    return;
                }
                if(TextUtils.isEmpty(mPhone)){
                    edtphone.setError("Vui lòng nhập số điện thoại");
                    return;
                }
                if(mPhone.length() != 10){
                    edtphone.setError("Số điện thoại không hợp lệ");
                    return;
                }
                if(TextUtils.isEmpty(mPassword)){
                    edtpassword.setError("Vui lòng nhập mật khẩu");
                    return;
                }
                if(mPassword.length() < 6 || mPassword.length() > 20){
                    edtpassword.setError("Mật khẩu phải có ít nhất 6 ký tự và không quá 20 ký tự");
                    return;
                }
                if(TextUtils.isEmpty(mPassAgain)){
                    edtpassAgain.setError("Vui lòng nhập lại mật khẩu");
                    return;
                }
                if(!mPassword.equals(mPassAgain)){
                    edtpassAgain.setError("Mật khẩu không trùng khớp");
                    return;
                }
                loadingdialog.show();
                //progressLogin.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser mUser = mAuth.getCurrentUser();
                            mUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    loadingdialog.dismiss();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Email không thể gửi" + e.getMessage());
                                }
                            });

                            DocumentReference documentReference = mstore.collection("users").document(mUser.getUid());
                            Map<String, Object> user = new HashMap<>();
                            user.put("username", mUsername);
                            user.put("email", mEmail);
                            user.put("phone", mPhone);
                            user.put("password", mPassword);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "Hồ sơ người dùng đã được tạo");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Hồ sơ người dùng không được tạo" + e.getMessage());
                                }
                            });
                            Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this, MainActivity.class));
                            finish();

                        }else{
                            Toast.makeText(SignUp.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            loadingdialog.dismiss();
                        }
                    }
                });

            }
        });
    }
}