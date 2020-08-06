package tdc.edu.vn.kiemtra.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context,"SQLDonHang",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table DonHang (ma text,ngay text,soluong text,sanpham text,khachhang text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
