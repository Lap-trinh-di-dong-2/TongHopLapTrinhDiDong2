package tdc.edu.vn.kiemtra.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

import tdc.edu.vn.kiemtra.Adapter.AdapterRecyclerView;
import tdc.edu.vn.kiemtra.DataBase.DBDonHang;
import tdc.edu.vn.kiemtra.Model.DonHang;
import tdc.edu.vn.kiemtra.R;

public class RecyclerView extends AppCompatActivity {
    AdapterRecyclerView showAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();
    }
    public  void initView()
    {
        androidx.recyclerview.widget.RecyclerView recyclerView = (androidx.recyclerview.widget.RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        DBDonHang dbDonHang = new DBDonHang(getApplicationContext());
        ArrayList<DonHang> arrayList = new ArrayList<>();
        showAdapter =  new AdapterRecyclerView(R.layout.card_view,dbDonHang.layDuLieu());
        recyclerView.setAdapter(showAdapter);
    }
}