package com.example.mapdiary;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by 린린 on 2017-05-16.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener ,GoogleMap.OnMapLongClickListener{
    GPSInfo gps;
    static double latitude, longtitude;
    GoogleMap mGoogleMap;
    Intent intent;
    String name;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        intent = getIntent();
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        Toast.makeText(getApplicationContext(),"환영합니다 "+name+"님",Toast.LENGTH_SHORT).show();


        gps = new GPSInfo(MapActivity.this);
        if (gps.isGetLocation()) {
            latitude = gps.getLatitude();
            longtitude = gps.getLongitude();
        }
        else
            gps.showSettingsAlert();

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }



    @Override
    public void onMapReady(final GoogleMap map) {
        mGoogleMap = map;

        LatLng MyPosition = new LatLng(latitude, longtitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(MyPosition);
        markerOptions.title("나의위치");
        markerOptions.snippet("지금 여기");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(MyPosition));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));
        mGoogleMap.setOnMapClickListener(this);
        mGoogleMap.setOnMapLongClickListener(this);
    }

    public void onMapLongClick(LatLng point) {
        Point screenPt = mGoogleMap.getProjection().toScreenLocation(point);
        LatLng latLng = mGoogleMap.getProjection().fromScreenLocation(screenPt);

        MapDialog dialog = new MapDialog(this,latLng,name,id) ;
        dialog.show() ;
    }




    public void onMapClick(LatLng point) {
        // 현재 위도와 경도에서 화면 포인트를 알려준다
        Point screenPt = mGoogleMap.getProjection().toScreenLocation(point);
        LatLng latLng = mGoogleMap.getProjection().fromScreenLocation(screenPt);

        Toast.makeText(getApplicationContext(),"위도: "+Double.toString(latLng.latitude)+"경도: "+Double.toString(latLng.longitude),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(0,1,0,"달력보기");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == 1){
            Intent intent = new Intent(MapActivity.this,CalendarActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            MainActivity.select = 0;
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}

