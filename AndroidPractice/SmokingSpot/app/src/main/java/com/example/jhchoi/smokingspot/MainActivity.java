package com.example.jhchoi.smokingspot;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;


import com.nhn.android.maps.*;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.NMapView.OnMapStateChangeListener;

import com.nhn.android.mapviewer.overlay.NMapCalloutCustomOverlay;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay.ResourceProvider;
import com.nhn.android.mapviewer.overlay.NMapOverlappedPOIdataHandler;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager.OnCalloutOverlayListener;

import java.util.Map;

public class MainActivity extends NMapActivity implements  OnMapStateChangeListener,OnCalloutOverlayListener{

    int markerId = NMapPOIflagType.PIN;

    ViewGroup mapLayout;
    NMapView mMapView;
    NMapViewerResourceProvider mMapViewerResourceProvider;
    NMapOverlayManager mOverlayManager;
    NMapController mMapController;
    NMapLocationManager mMapLocationManager;
    NMapMyLocationOverlay mMapMyLocationOverlay;
    NMapPOIdata poiData;
    NGeoPoint me;

    GpsInfo gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapinit();
    }

    void Mapinit() {
        //초기값 gps NGEOPoint는 먹통임
        gps = new GpsInfo(this);
        me = new NGeoPoint();
        mapLayout = findViewById(R.id.map_view);
        mMapView = new NMapView(this);
        mMapLocationManager = new NMapLocationManager(this);
        //현재위치 활성화 중요
        mMapLocationManager.enableMyLocation(false);
        mMapLocationManager.setOnLocationChangeListener(onMyLocationChangeListener);
        //파란색 점 활성화 시간 조정
        mMapLocationManager.setStartTimeout(500);
        mMapLocationManager.setUpdateFrequency(0,0);
        mMapLocationManager.setStartAccuracy(100);
        mMapView.setClientId("hGmPWCJSvV2yASNzXKjb"); // 클라이언트 아이디 값 설정
        mMapView.setClickable(true);
        mMapView.setOnMapStateChangeListener(this);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapController = mMapView.getMapController();


        //오버레이 리소스 관리객체 할당
        mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
        //오버레이 관리자 추가
        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);

        if (gps.isGetLocation()) {
            me.latitude = gps.getLatitude();
            me.longitude = gps.getLongitude();
            //Toast.makeText(getApplicationContext(), "당신의 위치 - \n위도: " + me.latitude + "\n경도: " + me.longitude, Toast.LENGTH_LONG).show();
        } else {
            // GPS 를 사용할수 없으므로
            gps.showSettingsAlert();
        }

        poiData = new NMapPOIdata(0, mMapViewerResourceProvider);
        POIsetting(poiData);

        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
        mOverlayManager.setOnCalloutOverlayListener((OnCalloutOverlayListener) this);
        poiDataOverlay.showAllPOIdata(30); //0

        //현재위치 오버레이 띄우는 부분
        mMapMyLocationOverlay = mOverlayManager.createMyLocationOverlay(mMapLocationManager, null);
        mMapMyLocationOverlay.refresh();

        //지도 추가
        mapLayout.addView(mMapView);
    }

    void POIsetting(NMapPOIdata poiData){
        poiData.beginPOIdata(0);
        //여기안에 데이터가 들어가면 됨
        //경도 위도 순
        poiData.addPOIitem( 126.7341531,37.338842, "산융", markerId, 0);
        poiData.addPOIitem( 126.734940,37.339652, "E동", markerId, 0);
        poiData.addPOIitem(126.734057,37.340684 , "종합관", markerId, 0);
        poiData.addPOIitem(126.732458,37.339899, "비지니스 센터", markerId, 0);
        //여기까지
        poiData.endPOIdata();
    }

    //------------------------------------------------------------------
    // 초기 화면 세팅 부분
    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
        if (nMapError == null) { // success
            mMapController.setMapCenter(me, 30);
        } else { // fail
            Log.e("missMessge", "onMapInitHandler: error=" + nMapError.toString());
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

    //-------------------------------------------------------------------//
    private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {
        @Override
        public boolean onLocationChanged(NMapLocationManager locationManager, NGeoPoint myLocation) {
            me=myLocation;
            if (mMapController != null) {
                mMapController.animateTo(me);
            }
            return true;
        }

        @Override
        public void onLocationUpdateTimeout(NMapLocationManager locationManager) {
            mMapController.animateTo(locationManager.getMyLocation());
        }

        @Override
        public void onLocationUnavailableArea(NMapLocationManager locationManager, NGeoPoint myLocation) {
        }
    };

    public void Action(String Text){
        Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay nMapOverlay, NMapOverlayItem nMapOverlayItem, Rect rect) {
        Action(nMapOverlayItem.getTitle());
        return new NMapCalloutBasicOverlay(nMapOverlay, nMapOverlayItem, rect);
    }
}
