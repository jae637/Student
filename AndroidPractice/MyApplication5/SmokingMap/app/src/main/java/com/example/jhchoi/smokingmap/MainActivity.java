package com.example.jhchoi.smokingmap;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.*;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapCalloutCustomOverlay;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlappedPOIdataHandler;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapPathDataOverlay;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends NMapActivity {

    public LocationManager locationManager;
    public LocationListener locationListener;

    String NAVER_API_KEY = "GFadP4ET5DKgN1ysVMIi";


    ViewGroup mapLayout;
    NMapView mMapView;

    double latitude, longitude;

    NMapOverlayManager mMapOverlayManager;
    NMapMyLocationOverlay mMapMyLocationOverlay;
    NMapResourceProvider mMapResourceProvider;
    NMapCalloutCustomOverlay mMapCalloutCustomOverlay;
    NMapCalloutOverlay mMapCalloutOverlay;
    NMapOverlappedPOIdataHandler mMapOverlappedPOIdataHandler;
    NMapPathDataOverlay mMapPathDataOverlay;
    NMapPOIdataOverlay mMapPOIdataOverlay;
    NGeoPoint me = new NGeoPoint();


    NMapLocationManager mMapLocationManager; //단말기의 현재 위치 탐색 기능을 사용하기 위한 클래스이다.
    NMapCompassManager mMapCompassManager;


    NMapController mMapController;

    NGeoPoint[] place = new NGeoPoint[30];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingGPS();

        // 사용자의 현재 위치 // GPS 사용
        Location userLocation = getMyLocation();
        if (userLocation != null) {
            // TODO 위치를 처음 얻어왔을 때 하고 싶은 것
            latitude = userLocation.getLatitude();
            longitude = userLocation.getLongitude();
        }

        //NMAP 초기화
        init();
    }

    public void init() {
        mapLayout = findViewById(R.id.mapLayout);
        mMapView = new NMapView(this);

        mMapView.setClientId("GFadP4ET5DKgN1ysVMIi"); // 클라이언트 아이디 값 설정
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);

        mMapView.setScalingFactor(3f);
        mMapView.requestFocus();


        mMapLocationManager = new NMapLocationManager(this);
        mMapLocationManager.setOnLocationChangeListener(new )
        mMapOverlayManager = new NMapOverlayManager(this,mMapView,mMapResourceProvider);

        //현 위치 위도 경도로 저장
        me.longitude= getMyLocation().getLongitude();
        me.latitude =getMyLocation().getLatitude();

        mMapView.setOnMapViewTouchEventListener(new NMapView.OnMapViewTouchEventListener() {
            @Override
            public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {

            }

            @Override
            public void onLongPressCanceled(NMapView nMapView) {

            }

            @Override
            public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {

            }

            @Override
            public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {

            }

            @Override
            public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent1) {

            }

            @Override
            public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {

            }
        });
        // 초기 화면 세팅
        mMapView.setOnMapStateChangeListener(new NMapView.OnMapStateChangeListener() {
            @Override
            public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
                if (nMapError == null) {
                    //지도 내위치로 초기화 함
                    mMapController.setMapCenter(me);
                    Toast.makeText(getApplicationContext(),"X:"+longitude+"\nY:"+latitude,Toast.LENGTH_LONG).show();
                } else {
                    Log.d("초기화 에러에러에러에러", "errrrr");

                }
            }

            @Override
            public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

            }

            @Override
            public void onMapCenterChangeFine(NMapView nMapView) {

            }

            @Override
            public void onZoomLevelChange(NMapView nMapView, int i) {

            }

            @Override
            public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

            }
        });
        //줌버튼 생성
        //mMapView.setBuiltInZoomControls(true,null);
        mapLayout.addView(mMapView);
    }

    // 위치 구하는 함수 GPS 사용
    public Location getMyLocation() {
        Location currentLocation = null;
        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 사용자 권한 요청
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            // 수동으로 위치 구하기
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
            if (currentLocation != null) {
                double lng = currentLocation.getLongitude();
                double lat = currentLocation.getLatitude();
                Log.d("Main", "longtitude=" + lng + ", latitude=" + lat);
            }
        }
        return currentLocation;
    }

    //GPS 세팅 함수
    public void settingGPS() {
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                // TODO 위도, 경도로 하고 싶은 것
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
    }
}