package tdc.edu.vn.quanlynhanvien.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.Vector;

import tdc.edu.vn.quanlynhanvien.Adapter.MyRecyclerViewAdapter;
import tdc.edu.vn.quanlynhanvien.Database.DBNhanVien;
import tdc.edu.vn.quanlynhanvien.Model.CardViewModel;
import tdc.edu.vn.quanlynhanvien.R;

public class MainRecyclerView extends AppCompatActivity {
    private Vector<CardViewModel> data;
    RecyclerView recyclerView;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler_view);
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        KhoiTao();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(R.layout.card_view_layout, dbNhanVien.duLieu());
        recyclerView.setAdapter(adapter);
    }

    private void KhoiTao() {
        //Initiation of data
//        data = new Vector<CardViewModel>();
//        data.add(new CardViewModel("NV1","Trần Bình An","25-02-1997","Nam",R.drawable.nam));
//        data.add(new CardViewModel("NV2","Mai Xuân Trí","21-11-2000","Nam",R.drawable.nam));
//        data.add(new CardViewModel("NV3","Hoàng Linh","25-06-1998","Nữ",R.drawable.nu));
//        data.add(new CardViewModel("NV4","Thúy Nga","25-06-1999","Nữ",R.drawable.nu));

    }

}