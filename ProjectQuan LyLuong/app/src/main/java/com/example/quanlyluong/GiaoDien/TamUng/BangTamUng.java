package com.example.quanlyluong.GiaoDien.TamUng;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyluong.Adapter.CustomAdapterTamUng;
import com.example.quanlyluong.DataBase.DBTamUng;
import com.example.quanlyluong.Library.LoadingDialog;
import com.example.quanlyluong.Model.TamUng;
import com.example.quanlyluong.R;

import java.util.ArrayList;

public class BangTamUng extends AppCompatActivity {
    ImageView imgXoa;
    ListView lvDanhSachTU;
    CustomAdapterTamUng adapterTU;
    ArrayList<TamUng> dataTU = new ArrayList<>();
    LoadingDialog loadingDialog = new LoadingDialog(BangTamUng.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bang_tamung);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {

        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
                HienThiDL();
            }

        }, 2000);

    }

    private void HienThiDL() {
        DBTamUng dbTamUng = new DBTamUng(getApplicationContext());
        dataTU = dbTamUng.layDuLieu();
        adapterTU = new CustomAdapterTamUng(BangTamUng.this, R.layout.listview_tamung, dataTU);
        adapterTU.notifyDataSetChanged();
        lvDanhSachTU.setAdapter(adapterTU);
    }

    private void setControl() {
        imgXoa = findViewById(R.id.imgXoa);
        lvDanhSachTU = findViewById(R.id.lvBangTamUng);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }


}