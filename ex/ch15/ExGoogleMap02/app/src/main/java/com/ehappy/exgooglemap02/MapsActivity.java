package com.ehappy.exgooglemap02;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap; //宣告 google map 物件
    float zoom;
    Spinner spnGeoPoint,spnMapType;
    String[] Names= new String[] {"台北101","日月潭","高雄六合夜市"};
    String[] MapTypes= new String[] {"一般地圖","混合地圖","衛星地圖","地形圖"};
    LatLng[] aryLatLng= new LatLng[]{ new LatLng(25.033611, 121.565000),
            new LatLng(23.862696, 120.904211), new LatLng(22.633428, 120.302368)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        spnGeoPoint=(Spinner) findViewById(R.id.spnGeoPoint);
        spnMapType=(Spinner) findViewById(R.id.spnMapType);

        // 建立 ArrayAdapter
        ArrayAdapter<String> adapterspnGeoPoint=new ArrayAdapter<String>
            (this,android.R.layout.simple_spinner_item,Names);
        ArrayAdapter<String> adapterspnMapType=new ArrayAdapter<String>
            (this,android.R.layout.simple_spinner_item,MapTypes);

        // 設定 Spinner 顯示的格式
        adapterspnGeoPoint.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterspnMapType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 設定 Spinner 的資料來源
        spnGeoPoint.setAdapter(adapterspnGeoPoint);
        spnMapType.setAdapter(adapterspnMapType);

        // 設定 spnGeoPoint 元件 ItemSelected 事件的 listener
        spnGeoPoint.setOnItemSelectedListener(spnGeoPointListener);
        spnMapType.setOnItemSelectedListener(spnMapTypeListener);

        // 利用 getSupportFragmentManager() 方法取得管理器
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        // 以非同步方式取得 GoogleMap 物件
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // 取得 GoogleMap 物件
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);       // 一般地圖

        mMap.getUiSettings().setCompassEnabled(true);     // 顯示指南針
        mMap.getUiSettings().setZoomControlsEnabled(true);// 顯示縮放圖示

        zoom=17; //設定放大倍率1(地球)-21(街景)
    }

    //  定義 onItemSelected 方法
    private Spinner.OnItemSelectedListener spnGeoPointListener=
     new Spinner.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {

            // 建立觀看地圖的視點位罝
            CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(aryLatLng[position]) // 地圖的中心點
                .zoom(zoom)                  // 放大倍率 17
                .bearing(0)                  // 指南針旋轉 0 度
                .tilt(0)                     // 俯視的角度
                .build();                    // 建立 CameraPosition 物件
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // TODO Auto-generated method stub
        }
    };

    private Spinner.OnItemSelectedListener spnMapTypeListener=
     new Spinner.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            String sel=parent.getSelectedItem().toString();
            switch (sel){
                case "一般地圖":
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); // 一般地圖
                    break;
                case "混合地圖":
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); // 混合地圖
                    break;
                case "衛星地圖":
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); // 衛星地圖
                    break;
                case "地形圖":
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);   // 地形圖
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // TODO Auto-generated method stub
        }
    };

}
