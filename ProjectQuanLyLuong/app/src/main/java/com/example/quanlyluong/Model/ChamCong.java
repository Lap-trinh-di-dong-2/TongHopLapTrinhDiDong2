package com.example.quanlyluong.Model;

public class ChamCong {
    String maNhanVien,thang,soNgayCong;

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getSoNgayCong() {
        return soNgayCong;
    }

    public void setSoNgayCong(String soNgayCong) {
        this.soNgayCong = soNgayCong;
    }

    @Override
    public String toString() {
        return "ChamCong{" +
                "maNhanVien='" + maNhanVien + '\'' +
                ", thang='" + thang + '\'' +
                ", soNgayCong='" + soNgayCong + '\'' +
                '}';
    }
}
