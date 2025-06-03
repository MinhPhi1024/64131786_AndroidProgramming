package com.ntu.leminhphi.example.mathquizapp.Models;

public class QuestionModels {
    private String question, answer, option1, option2, option3,option4,key;

    public QuestionModels() {
    }

    public QuestionModels(String question, String option4, String option3, String option2, String option1, String answer) {
        this.question = question;
        this.option4 = option4;
        this.option3 = option3;
        this.option2 = option2;
        this.option1 = option1;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
