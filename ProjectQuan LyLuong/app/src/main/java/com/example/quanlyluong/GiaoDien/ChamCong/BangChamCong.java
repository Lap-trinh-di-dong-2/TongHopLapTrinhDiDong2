package com.example.quanlyluong.GiaoDien.ChamCong;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyluong.Adapter.CustomAdapterChamCong;
import com.example.quanlyluong.DataBase.DBChamCong;
import com.example.quanlyluong.Library.LoadingDialog;
import com.example.quanlyluong.Model.ChamCong;
import com.example.quanlyluong.R;

import java.util.ArrayList;

public class BangChamCong extends AppCompatActivity {
    ImageView imgXoa, imgTamUng;
    ListView lvChamCong;
    CustomAdapterChamCong adapter_chamcong;
    ArrayList<ChamCong> data_chamcong = new ArrayList<>();
    LoadingDialog loadingDialog = new LoadingDialog(BangChamCong.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bang_chamcong);
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

        }, 1000);

    }

    private void HienThiDL() {
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
        data_chamcong = dbChamCong.layDuLieu();
        adapter_chamcong = new CustomAdapterChamCong(BangChamCong.this, R.layout.listview_chamcong, data_chamcong);
        adapter_chamcong.notifyDataSetChanged();
        lvChamCong.setAdapter(adapter_chamcong);
    }

    private void setControl() {
        lvChamCong = findViewById(R.id.lvBangChamCong);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}