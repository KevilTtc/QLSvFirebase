package com.example.qlsvfirebase.object;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String Mssv;
    private String HoTen;
    private String Email;
    private String SoDT;



    public Student( String masv, String hoTen, String email, String soDT) {
        this.Mssv = masv;
        this.HoTen = hoTen;
        this.Email = email;
        this.SoDT = soDT;
    }

    public Student() {
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        this.HoTen = hoTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMssv() {
        return Mssv;
    }

    public void setMssv(String mssv) {
        this.Mssv = mssv;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String soDT) {
        this.SoDT = soDT;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", Mssv='" + Mssv + '\'' +
                ", HoTen='" + HoTen + '\'' +
                ", Email='" + Email + '\'' +
                ", SoDT='" + SoDT + '\'' +
                '}';
    }
}
