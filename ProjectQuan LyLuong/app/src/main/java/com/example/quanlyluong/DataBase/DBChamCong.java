package com.example.quanlyluong.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlyluong.Model.ChamCong;
import com.example.quanlyluong.Model.PhongBan;

import java.util.ArrayList;

public class DBChamCong {
    DBHelper dbHelper;

    public DBChamCong(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void themChamCong(ChamCong chamCong) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("manv", chamCong.getMaNhanVien());
        values.put("ngaycham", chamCong.getThang());
        values.put("songaycong", chamCong.getSoNgayCong());
        db.insert("ChamCong", null, values);
        db.close();
    }

    public void suaChamCong(ChamCong chamCong) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("manv", chamCong.getMaNhanVien());
        values.put("ngaycham", chamCong.getThang());
        values.put("songaycong", chamCong.getSoNgayCong());
        db.update("ChamCong", values, "manv ='" + chamCong.getMaNhanVien() + "'", null);
        db.close();
    }

    public ArrayList<ChamCong> layDuLieu() {
        ArrayList<ChamCong> data = new ArrayList<>();
        String sql = "Select * from ChamCong ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                ChamCong chamCong = new ChamCong();
                chamCong.setMaNhanVien(cursor.getString(0));
                chamCong.setThang(cursor.getString(1));
                chamCong.setSoNgayCong(cursor.getString(2));
                data.add(chamCong);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public void xoaChamCong(ChamCong chamCong) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("ChamCong", "manv= '" + chamCong.getMaNhanVien() + "'and ngaycham = '" +chamCong.getThang()+"'"  , null);
        db.close();
    }

    public ArrayList<ChamCong> layChamCong(String manv) {
        ArrayList<ChamCong> data = new ArrayList<>();
        String sql = "select * from ChamCong where manv ='" + manv + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                ChamCong chamCong = new ChamCong();
                chamCong.setMaNhanVien(cursor.getString(0));
                chamCong.setThang(cursor.getString(1));
                chamCong.setSoNgayCong(cursor.getString(2));
                data.add(chamCong);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        db.close();
        return data;
    }

    public ArrayList<String> layDSNgayCham() {
        ArrayList<String> data = new ArrayList<>();
        String sql = "SELECT DISTINCT ngaycham FROM ChamCong INNER JOIN TamUng on TamUng.manv = ChamCong.manv  WHERE ChamCong.ngaycham = SUBSTR(TamUng.ngay, 4, 10)";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                ChamCong chamCong = new ChamCong();
                String ngayCham = "";
                chamCong.setThang(cursor.getString(0));
                ngayCham = chamCong.getThang();
                data.add(ngayCham);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    //Kiểm tra Số phiếu là duy nhất
    public boolean checkChamCong(String timeCham, String manv) {
        boolean check = false;
        String sql = "SELECT count(*) FROM ChamCong WHERE ngaycham LIKE \""+timeCham+"\" and manv LIKE \""+manv+"\" ";
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
