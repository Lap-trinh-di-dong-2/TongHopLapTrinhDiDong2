package com.example.quanlyluong.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlyluong.Model.NhanVien;
import com.example.quanlyluong.Model.PhongBan;
import com.example.quanlyluong.Model.ThongKe;

import java.util.ArrayList;

public class DBNhanVien {
    DBHelper dbHelper;

    public DBNhanVien(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void themNhanVien(NhanVien nhanVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("manv", nhanVien.getMaNhanVien());
        values.put("tennv", nhanVien.getTenNhanVien());
        values.put("ngaysinh", nhanVien.getNgaySinh());
        values.put("gioitinh", nhanVien.getGioiTinh());
        values.put("mapb", nhanVien.getPhongBan());
        values.put("hesoluong", nhanVien.getHeSoLuong());
        values.put("hinh", nhanVien.getAnh());
        db.insert("NhanVien", null, values);
        db.close();
    }

    public void suaNhanVien(NhanVien nhanVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("manv", nhanVien.getMaNhanVien());
        values.put("tennv", nhanVien.getTenNhanVien());
        values.put("ngaysinh", nhanVien.getNgaySinh());
        values.put("gioitinh", nhanVien.getGioiTinh());
        values.put("mapb", nhanVien.getPhongBan());
        values.put("hesoluong", nhanVien.getHeSoLuong());
        values.put("hinh", nhanVien.getAnh());
        db.update("NhanVien", values, "manv ='" + nhanVien.getMaNhanVien() + "'", null);
        db.close();
    }

    public void xoaNhanVien(NhanVien nhanVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("NhanVien", "manv ='" + nhanVien.getMaNhanVien() + "'", null);
        db.close();
    }

    public ArrayList<NhanVien> layDSNhanVien() {
        ArrayList<NhanVien> data = new ArrayList<>();
        String sql = "Select * from NhanVien ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNhanVien(cursor.getString(0));
                nhanVien.setTenNhanVien(cursor.getString(1));
                nhanVien.setNgaySinh(cursor.getString(2));
                nhanVien.setGioiTinh(cursor.getString(3));
                nhanVien.setPhongBan(cursor.getString(4));
                nhanVien.setHeSoLuong(cursor.getString(5));
                nhanVien.setAnh(cursor.getBlob(6));
                data.add(nhanVien);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public ArrayList<NhanVien> layNhanVien(String manv) {
        ArrayList<NhanVien> data = new ArrayList<>();
        String sql = "select * from NhanVien where manv ='" + manv + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNhanVien(cursor.getString(0));
                nhanVien.setTenNhanVien(cursor.getString(1));
                nhanVien.setNgaySinh(cursor.getString(2));
                nhanVien.setGioiTinh(cursor.getString(3));
                nhanVien.setPhongBan(cursor.getString(4));
                nhanVien.setHeSoLuong(cursor.getString(5));
                nhanVien.setAnh(cursor.getBlob(6));
                data.add(nhanVien);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        db.close();
        return data;
    }


    public ArrayList<ThongKe> layDSThongKe() {
        ArrayList<ThongKe> data = new ArrayList<>();
        String sql = "select NhanVien.manv,NhanVien.tennv,PhongBan.tenpb,NhanVien.hesoluong,ChamCong.ngaycham,ChamCong.songaycong,TamUng.sotien " +
                "from NhanVien INNER JOIN  PhongBan on PhongBan.mapb = NhanVien.mapb " +
                "INNER JOIN  ChamCong on NhanVien.manv = ChamCong.manv  " +
                "INNER JOIN TamUng on NhanVien.manv = TamUng.manv WHERE ChamCong.ngaycham = SUBSTR(TamUng.ngay, 4, 10) ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                ThongKe thongKe = new ThongKe();
                thongKe.setMaNhanVien(cursor.getString(0));
                thongKe.setTenNhanVien(cursor.getString(1));
                thongKe.setTenPhongBan(cursor.getString(2));
                thongKe.setLuongCoBan(cursor.getString(3));
                thongKe.setNgayChamCong(cursor.getString(4));
                thongKe.setNgayCong(cursor.getString(5));
                thongKe.setTamUng(cursor.getString(6));
                data.add(thongKe);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public ArrayList<ThongKe> layThongKe(String ngayCham) {
        String sql = "select NhanVien.manv,NhanVien.tennv,PhongBan.tenpb,NhanVien.hesoluong,ChamCong.ngaycham,ChamCong.songaycong,TamUng.sotien " +
                "from NhanVien INNER JOIN  PhongBan on PhongBan.mapb = NhanVien.mapb " +
                "INNER JOIN  ChamCong on NhanVien.manv = ChamCong.manv  " +
                "INNER JOIN TamUng on NhanVien.manv = TamUng.manv WHERE ChamCong.ngaycham ='" + ngayCham + "' AND ChamCong.ngaycham = SUBSTR(TamUng.ngay, 4, 10) ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<ThongKe> data = new ArrayList<>();

        try {
            cursor.moveToFirst();
            do {
                ThongKe thongKe = new ThongKe();
                thongKe.setMaNhanVien(cursor.getString(0));
                thongKe.setTenNhanVien(cursor.getString(1));
                thongKe.setTenPhongBan(cursor.getString(2));
                thongKe.setLuongCoBan(cursor.getString(3));
                thongKe.setNgayChamCong(cursor.getString(4));
                thongKe.setNgayCong(cursor.getString(5));
                thongKe.setTamUng(cursor.getString(6));
                data.add(thongKe);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }


    public ArrayList<ThongKe> layThongKeBieuDo(String manv) {
        String sql = "select NhanVien.manv,NhanVien.tennv,PhongBan.tenpb,NhanVien.hesoluong,ChamCong.ngaycham,ChamCong.songaycong,TamUng.sotien from NhanVien INNER JOIN  PhongBan on PhongBan.mapb = NhanVien.mapb \n" +
                "  INNER JOIN  ChamCong on NhanVien.manv = ChamCong.manv INNER JOIN TamUng on NhanVien.manv = TamUng.manv WHERE Nhanvien.manv ='" + manv + "' AND ChamCong.ngaycham = SUBSTR(TamUng.ngay, 4, 10)";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<ThongKe> data = new ArrayList<>();

        try {
            cursor.moveToFirst();
            do {
                ThongKe thongKe = new ThongKe();
                thongKe.setMaNhanVien(cursor.getString(0));
                thongKe.setTenNhanVien(cursor.getString(1));
                thongKe.setTenPhongBan(cursor.getString(2));
                thongKe.setLuongCoBan(cursor.getString(3));
                thongKe.setNgayChamCong(cursor.getString(4));
                thongKe.setNgayCong(cursor.getString(5));
                thongKe.setTamUng(cursor.getString(6));
                data.add(thongKe);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }


    public ArrayList<ThongKe> locDSThongKe(String key) {
        ArrayList<ThongKe> data = new ArrayList<>();
        String sql = "select NhanVien.manv,NhanVien.tennv,PhongBan.tenpb,NhanVien.hesoluong,ChamCong.ngaycham,ChamCong.songaycong,TamUng.sotien from NhanVien INNER JOIN  PhongBan on PhongBan.mapb = NhanVien.mapb INNER JOIN  ChamCong on NhanVien.manv = ChamCong.manv  INNER JOIN TamUng on NhanVien.manv = TamUng.manv WHERE ChamCong.ngaycham LIKE \"%" + key + "%\" AND ChamCong.ngaycham = SUBSTR(TamUng.ngay, 4, 10) ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                ThongKe thongKe = new ThongKe();
                thongKe.setMaNhanVien(cursor.getString(0));
                thongKe.setTenNhanVien(cursor.getString(1));
                thongKe.setTenPhongBan(cursor.getString(2));
                thongKe.setLuongCoBan(cursor.getString(3));
                thongKe.setNgayChamCong(cursor.getString(4));
                thongKe.setNgayCong(cursor.getString(5));
                thongKe.setTamUng(cursor.getString(6));
                data.add(thongKe);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }


    public String layMaPhong(String tenPhong) {
        String maPhong = "";
        String sql = "SELECT mapb FROM PhongBan WHERE tenpb LIKE \"%" + tenPhong + "%\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                PhongBan phongBan = new PhongBan();
                phongBan.setMaPhong(cursor.getString(0));
                maPhong = phongBan.getMaPhong();
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return maPhong;
    }
    //Kiểm tra ráng buộc giữa Nhân viên và Tạm ứng khi xóa nhân viên
    public boolean checkXoaNhanVienTamUng(String maNhanVien) {
        boolean check = false;
        String sql = "SELECT count(*) FROM TamUng WHERE manv LIKE \"%" + maNhanVien + "%\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count > 0) {
            check = true;
        }
        return check;
    }

    //Kiểm tra ráng buộc giữa Nhân viên và Chấm công khi xóa nhân viên
    public boolean checkXoaNhanVienChamCong(String maNhanVien) {
        boolean check = false;
        String sql = "SELECT count(*) FROM ChamCong WHERE manv LIKE \"%" + maNhanVien + "%\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count > 0) {
            check = true;
        }
        return check;
    }

    //Kiểm tra mã nhân viên là duy nhất
    public boolean checkMaNhanVien(String manv) {
        boolean check = false;
        String sql = "SELECT count(*) FROM NhanVien WHERE manv LIKE \""+manv+"\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count  = cursor.getInt(0);
        if(count > 0) {
            check = true;
        }
        return check;
    }
}
