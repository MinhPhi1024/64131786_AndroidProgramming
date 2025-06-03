package com.ntu.leminhphi.example.mathquizapp.Models;

public class DoiTuongModels {

    private String tenlop,hinh, key;
    public DoiTuongModels() {
    }

    public DoiTuongModels(String tenlop, String hinh) {
        this.tenlop = tenlop;
        this.hinh = hinh;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
