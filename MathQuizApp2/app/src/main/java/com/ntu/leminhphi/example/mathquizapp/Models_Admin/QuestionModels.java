package com.ntu.leminhphi.example.mathquizapp.Models_Admin;

public class QuestionModels {
    private String question, answer, edtA, edtB, edtC, edtD,key;

    public QuestionModels() {
    }

    public QuestionModels(String question, String answer, String edtA, String edtB, String edtC, String edtD) {
        this.question = question;
        this.answer = answer;
        this.edtA = edtA;
        this.edtB = edtB;
        this.edtC = edtC;
        this.edtD = edtD;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEdtA() {
        return edtA;
    }

    public void setEdtA(String edtA) {
        this.edtA = edtA;
    }

    public String getEdtB() {
        return edtB;
    }

    public void setEdtB(String edtB) {
        this.edtB = edtB;
    }

    public String getEdtC() {
        return edtC;
    }

    public void setEdtC(String edtC) {
        this.edtC = edtC;
    }

    public String getEdtD() {
        return edtD;
    }

    public void setEdtD(String edtD) {
        this.edtD = edtD;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
