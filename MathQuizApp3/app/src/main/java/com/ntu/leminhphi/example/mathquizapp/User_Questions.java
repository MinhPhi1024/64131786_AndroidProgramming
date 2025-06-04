package com.ntu.leminhphi.example.mathquizapp;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ntu.leminhphi.example.mathquizapp.Models_User.QuestionModels;
import com.ntu.leminhphi.example.mathquizapp.databinding.ActivityUserQuestionsBinding;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class User_Questions extends AppCompatActivity {

    ActivityUserQuestionsBinding binding;
    FirebaseDatabase database ;
    ArrayList<QuestionModels> list;

    private int count = 0;
    private int position = 0;
    private int correct = 0;
    private int wrong = 0;
    private long questionTime = 10;
    private long timeLeft;
    CountDownTimer timer;
    private String doituongID,tendoituong;
    Dialog loadingdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserQuestionsBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_user_questions);

        doituongID = getIntent().getStringExtra("tenlopID");
        tendoituong = getIntent().getStringExtra("baihoc");

        loadingdialog = new Dialog(this);
        loadingdialog.setContentView(R.layout.loading_dialog);
        loadingdialog.setCancelable(true);
        loadingdialog.show();

        startTimer();

        database = FirebaseDatabase.getInstance();

        list = new ArrayList<>();

        database.getReference().child("tenlop").child(doituongID).child("baihoc")
                .child(tendoituong).child("cauhoi").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {

                            list.clear();

                            timer.start();

                            for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                QuestionModels questionModels = dataSnapshot.getValue(QuestionModels.class);
                                questionModels.setKey(dataSnapshot.getKey());
                                list.add(questionModels);

                                loadingdialog.dismiss();
                            }
                            if(list.size() > 0){
                                for(int i =0 ; i<4; i++){
                                    binding.linOption.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            checkAnswer((Button)view);
                                        }
                                    });
                                }
                                playAnimation(binding.tvquestion,0,list.get(position).getQuestion());

                                binding.btnNext.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        enaleOption(true);
                                        position++;

                                        if(position == list.size()){
                                            timer.cancel();

                                            long totalTime = questionTime * 60 * 100;
                                            Intent intent = new Intent(User_Questions.this, User_Score.class);
                                            intent.putExtra("time_taken",totalTime-timeLeft);
                                            intent.putExtra("correct",correct);
                                            intent.putExtra("wrong",wrong);
                                            intent.putExtra("total_question",list.size());
                                            startActivity(intent);
                                            finish();
                                            return;
                                        }

                                        count = 0;
                                        playAnimation(binding.tvquestion,position,list.get(position).getQuestion());

                                    }
                                });

                                binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        timer.cancel();

                                        long totalTime = questionTime * 60 * 100;
                                        Intent intent = new Intent(User_Questions.this, User_Score.class);
                                        intent.putExtra("time_taken",totalTime-timeLeft);
                                        intent.putExtra("correct",correct);
                                        intent.putExtra("wrong",wrong);
                                        intent.putExtra("total_question",list.size());
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                        else {
                            loadingdialog.dismiss();
                            Toast.makeText(User_Questions.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void startTimer() {
        long time = questionTime * 60 * 100;
        timer = new CountDownTimer(time + 1000,1000){
            @Override
            public void onTick(long l) {
                timeLeft = l;
                String remainingTime = String.format("%02d:%02d min",
                        TimeUnit.MILLISECONDS.toMinutes(l),
                        TimeUnit.MILLISECONDS.toSeconds(l)-
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                binding.tvTime.setText(remainingTime);
            }
            @Override
            public void onFinish() {
                long totalTime = questionTime * 60 * 100;
                Intent intent = new Intent(User_Questions.this, User_Score.class);
                intent.putExtra("time_taken",totalTime-timeLeft);
                intent.putExtra("correct",correct);
                intent.putExtra("wrong",wrong);
                intent.putExtra("total_question",list.size());
                startActivity(intent);
                User_Questions.this.finish();
            }
        };
    }

    private void enaleOption(boolean enable) {
        for(int i = 0; i < 4; i++){
            binding.linOption.getChildAt(i).setEnabled(enable);

            if(enable){
                binding.linOption.getChildAt(i).setBackgroundResource(R.drawable.btn_option_bg);
            }
        }
    }

    private void playAnimation(View view, int value, String data) {
        view.animate().alpha(value).scaleX(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {

                        if(value == 0 && count < 4){
                            String option = "";
                            if(count == 0){
                                option = list.get(position).getEdtA();
                            } else if (count == 1) {
                                option = list.get(position).getEdtB();
                            } else if (count == 2) {
                                option = list.get(position).getEdtC();
                            } else if (count ==3) {
                                option = list.get(position).getEdtD();
                            }
                            playAnimation(binding.linOption.getChildAt(count),0,option);
                            count++;
                        }
                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {

                        if(value == 0){
                            try{
                                ((TextView)view).setText(data);
                                binding.tvQuesCount.setText(position+ 1 +"/"+list.size());

                            }catch (Exception e){
                                ((Button)view).setText(data);

                            }
                            view.setTag(data);
                            playAnimation(view,1,data);
                        }

                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {

                    }
                });
    }

    private void checkAnswer(Button selectedOption) {

        enaleOption(false);
        if(selectedOption.getText().toString().equals(list.get(position).getAnswer())){
            correct++;
            selectedOption.setBackgroundResource(R.drawable.corret_option_bg);
        }
        else {
            wrong++;
            selectedOption.setBackgroundResource(R.drawable.wrong_option_bg);

            Button correctOption = (Button) binding.linOption.findViewWithTag(list.get(position).getAnswer());
            correctOption.setBackgroundResource(R.drawable.corret_option_bg);

        }
    }

}