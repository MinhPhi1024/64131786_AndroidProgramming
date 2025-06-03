package com.ntu.leminhphi.example.mathquizapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.ntu.leminhphi.example.mathquizapp.Models_Admin.QuestionModels;
import com.ntu.leminhphi.example.mathquizapp.databinding.ActivityAdminThemQuestionsBinding;

public class Admin_ThemQuestions extends AppCompatActivity {

    ActivityAdminThemQuestionsBinding binding;
    FirebaseDatabase datebase;
    FirebaseStorage storage;
    RadioGroup rgOption;
    LinearLayout linAns;
    Dialog loadingdialog;

    private String tenlopID,themdoituongID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminThemQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        datebase = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        tenlopID = getIntent().getStringExtra("tenlopID");
        themdoituongID = getIntent().getStringExtra("themdoituongID");

        rgOption = (RadioGroup)findViewById(R.id.rgOption);
        linAns = (LinearLayout)findViewById(R.id.linAns);

        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.loading_dialog);
        loadingdialog.setCancelable(false);

        binding.btnthemQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int correct = -1;

                for( int i = 0; i < rgOption.getChildCount(); i++){
                    EditText answ = (EditText)linAns.getChildAt(i);
                    if(answ.getText().toString().isEmpty()){
                        answ.setError("Không được để trống");
                        return;
                    }
                    RadioButton radioButton = (RadioButton)rgOption.getChildAt(i);
                    if(radioButton.isChecked()){
                        correct = i;
                        break;
                    }
                }

                if(correct == -1){
                    Toast.makeText(Admin_ThemQuestions.this, "Vui lòng chọn đáp án đúng", Toast.LENGTH_SHORT).show();
                    return;
                }

                loadingdialog.show();
                QuestionModels models = new QuestionModels();
                models.setQuestion(binding.edtQues.getText().toString());
                models.setEdtA(((EditText)linAns.getChildAt(0)).getText().toString());
                models.setEdtB(((EditText)linAns.getChildAt(1)).getText().toString());
                models.setEdtC(((EditText)linAns.getChildAt(2)).getText().toString());
                models.setEdtD(((EditText)linAns.getChildAt(3)).getText().toString());
                models.setAnswer(((EditText)linAns.getChildAt(correct)).getText().toString());

                datebase.getReference().child("tenlop").child(tenlopID).child("baihoc").child(themdoituongID)
                        .child("cauhoi")
                        .push()
                        .setValue(models).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                loadingdialog.dismiss();
                                Toast.makeText(Admin_ThemQuestions.this, "Thêm thành công ", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                loadingdialog.dismiss();
                                Toast.makeText(Admin_ThemQuestions.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });


    }
}