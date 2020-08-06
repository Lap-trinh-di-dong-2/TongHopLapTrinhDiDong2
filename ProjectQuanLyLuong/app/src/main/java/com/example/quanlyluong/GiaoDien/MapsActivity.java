package com.example.quanlyluong.GiaoDien;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.FragmentActivity;

import com.example.quanlyluong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Spinner spinner_map_type;
    ArrayAdapter<String> adapterMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_maps_api);
        setControl();
        setEvent();
        createMap();

    }

    private void setEvent() {
        String[] arrMap = getResources().getStringArray(R.array.map_type);
        adapterMap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrMap);
        spinner_map_type.setAdapter(adapterMap);
        spinner_map_type.setSelection(2);
        spinner_map_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int type = GoogleMap.MAP_TYPE_NORMAL;

                switch (i) {
                    case 0:
                        type = GoogleMap.MAP_TYPE_NONE;
                        break;
                    case 1:
                        type = GoogleMap.MAP_TYPE_NORMAL;
                        break;
                    case 2:
                        type = GoogleMap.MAP_TYPE_SATELLITE;
                        break;
                    case 3:
                        type = GoogleMap.MAP_TYPE_TERRAIN;
                        break;
                    case 4:
                        type = GoogleMap.MAP_TYPE_HYBRID;
                        break;
                }
                mMap.setMapType(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setControl() {
        spinner_map_type = findViewById(R.id.spin_map);
    }

    private void createMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        createMap();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng cdcntd = new LatLng(10.852169, 106.758538);
        mMap = googleMap;

//        mMap.addMarker(new MarkerOptions().position(hcm).title("Marker in HCM City"));
//        CameraPosition cp = new CameraPosition.Builder().target(hcm).zoom(25).build();
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        MarkerOptions options = new MarkerOptions();
        options.position(cdcntd);
        options.title("Trường Cao Đẳng Công Nghệ Thủ Đức").snippet("Nơi tôi đang theo học");
//        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        Marker marker = mMap.addMarker(options);
        marker.showInfoWindow();
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cdcntd, 17));
    }


}
