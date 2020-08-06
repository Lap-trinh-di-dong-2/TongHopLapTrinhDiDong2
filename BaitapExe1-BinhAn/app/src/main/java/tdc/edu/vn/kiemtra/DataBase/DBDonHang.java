package tdc.edu.vn.kiemtra.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tdc.edu.vn.kiemtra.Model.DonHang;

public class DBDonHang {
    DBHelper dbHelper;

    public DBDonHang(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void themDonHang(DonHang donHang)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ma",donHang.getMa());
        values.put("ngay",donHang.getNgay());
        values.put("soluong",donHang.getSoLuong());
        values.put("sanpham",donHang.getSanPham());
        values.put("khachhang",donHang.getKhachHang());
        db.insert("DonHang",null,values);
    }
    public void xoaDonHang(String ma)
    {
        ArrayList<DonHang>data = new ArrayList<>();
        String sql = "Delete from DonHang where ma = '"+ ma +"'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
    }
    public void themDanhSach(ArrayList<DonHang>dsDonHang)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for(int i=0;i<dsDonHang.size();i++)
        {
            themDonHang(dsDonHang.get(i));
        }
    }

    public ArrayList<DonHang>layDuLieu()
    {
        ArrayList<DonHang>data = new ArrayList<>();
        String sql = "Select * from DonHang ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do{
            DonHang donHang = new DonHang();
            donHang.setMa(cursor.getString(0));
            donHang.setNgay(cursor.getString(1));
            donHang.setSoLuong(cursor.getString(2));
            donHang.setSanPham(cursor.getString(3));
            donHang.setKhachHang(cursor.getString(4));
            data.add(donHang);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<DonHang>layDuLieu(String ma)
    {
        ArrayList<DonHang>data = new ArrayList<>();
        String sql = "Select * from DonHang where ma = '"+ ma +"'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        try {
            cursor.moveToFirst();
            do {
            DonHang donHang = new DonHang();
            donHang.setMa(cursor.getString(0));
            donHang.setNgay(cursor.getString(1));
            donHang.setSoLuong(cursor.getString(2));
            data.add(donHang);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return data;
    }
}
