package tdc.edu.vn.quanlynhanvien.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Vector;

import tdc.edu.vn.quanlynhanvien.Model.CardViewModel;
import tdc.edu.vn.quanlynhanvien.Model.NhanVien;

public class DBNhanVien {
    DBHelper dbHelper;

    public DBNhanVien(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void themNhanVien(NhanVien nhanVien)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ma",nhanVien.getMa());
        values.put("ten",nhanVien.getHoTen());
        values.put("ngaysinh",nhanVien.getNgaySinh());
        values.put("gioitinh",nhanVien.getGioiTinh());
        db.insert("NhanVien",null,values);
        db.close();
    }

    public void suaNhanVien(NhanVien nhanVien) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ma",nhanVien.getMa());
        values.put("ten",nhanVien.getHoTen());
        values.put("ngaysinh",nhanVien.getNgaySinh());
        values.put("gioitinh",nhanVien.getGioiTinh());
        db.update("NhanVien", values, "ma ='" + nhanVien.getMa() + "'", null);
        db.close();
    }

    public void xoaNhanVien(NhanVien nhanVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("NhanVien", "ma ='" + nhanVien.getMa() + "'", null);
        db.close();
    }

    public void themDanhSach(ArrayList<NhanVien> dsNhanVien)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for(int i=0;i<dsNhanVien.size();i++)
        {
            themNhanVien(dsNhanVien.get(i));
        }
    }


    public ArrayList<NhanVien>layDuLieu()
    {
        ArrayList<NhanVien>data = new ArrayList<>();
        String sql = "Select * from NhanVien ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do{
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMa(cursor.getString(0));
            nhanVien.setHoTen(cursor.getString(1));
            nhanVien.setNgaySinh(cursor.getString(2));
            nhanVien.setGioiTinh(cursor.getString(3));
            data.add(nhanVien);
        }
        while (cursor.moveToNext());
        return data;
    }
    public ArrayList<NhanVien>layDuLieu(String ma)
    {
        ArrayList<NhanVien>data = new ArrayList<>();
        String sql = "Select * from NhanVien where ma = '"+ ma +"'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        try {
            cursor.moveToFirst();
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMa(cursor.getString(0));
                nhanVien.setHoTen(cursor.getString(1));
                nhanVien.setNgaySinh(cursor.getString(2));
                nhanVien.setGioiTinh(cursor.getString(3));
                data.add(nhanVien);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }
        return data;
    }


    public Vector<CardViewModel>duLieu()
    {
        Vector<CardViewModel> data = new Vector<>();
        String sql = "Select * from NhanVien ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do{
            CardViewModel cardViewModel = new CardViewModel();
            cardViewModel.setMa(cursor.getString(0));
            cardViewModel.setHoTen(cursor.getString(1));
            cardViewModel.setNgaySinh(cursor.getString(2));
            cardViewModel.setGioiTinh(cursor.getString(3));
            data.add(cardViewModel);
        }
        while (cursor.moveToNext());
        return data;
    }

}
