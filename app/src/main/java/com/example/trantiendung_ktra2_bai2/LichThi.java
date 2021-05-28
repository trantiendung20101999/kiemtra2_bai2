package com.example.trantiendung_ktra2_bai2;

import java.io.Serializable;

public class LichThi implements Serializable {
    private int id;
    private String tenmonhoc;
    private String date;
    private String time;
    private String type;

    public LichThi() {
    }

    public LichThi(String tenmonhoc, String date, String time, String type) {
        this.tenmonhoc = tenmonhoc;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    public LichThi(int id, String tenmonhoc, String date, String time, String type) {
        this.id = id;
        this.tenmonhoc = tenmonhoc;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenmonhoc() {
        return tenmonhoc;
    }

    public void setTenmonhoc(String tenmonhoc) {
        this.tenmonhoc = tenmonhoc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
