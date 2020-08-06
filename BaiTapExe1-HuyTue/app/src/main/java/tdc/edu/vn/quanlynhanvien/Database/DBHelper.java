package tdc.edu.vn.quanlynhanvien.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context,"SQLNhanVien",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table NhanVien (ma text,ten text,ngaysinh text,gioitinh text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
