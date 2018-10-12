package com.example.jhchoi.smokingspot;

import android.content.Intent;
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

import org.w3c.dom.Text;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends NMapActivity implements  OnMapStateChangeListener,OnCalloutOverlayListener{

    int markerId = NMapPOIflagType.PIN;

    static final String URL = "http://www.smokingspot.co.kr";
    RetrofitRepo repo;

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
        //네이버 맵 세팅 부분
        mMapView.setClientId("hGmPWCJSvV2yASNzXKjb"); // 클라이언트 아이디 값 설정
        mMapView.setClickable(true);
        mMapView.setOnMapStateChangeListener(this);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();
        //중심지 세팅 부분
        mMapController = mMapView.getMapController();
        //오버레이 리소스 관리객체 할당
        mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
        //오버레이 관리자 추가
        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);
        //마크 객체 생성
        poiData = new NMapPOIdata(0, mMapViewerResourceProvider);

        //GPS 사용해 위치 잡음
        if (gps.isGetLocation()) {
            me.latitude = gps.getLatitude();
            me.longitude = gps.getLongitude();
            //Toast.makeText(getApplicationContext(), "당신의 위치 - \n위도: " + me.latitude + "\n경도: " + me.longitude, Toast.LENGTH_LONG).show();
        } else {
            // GPS 를 사용할수 없으므로
            gps.showSettingsAlert();
        }

        //레트로핏이 스레드로 돌아가기 때문에 마크업 부분을 함수 안에다 생성 (데이터를 받아와 핀을 찍는다)
        index();

        //지도 추가
        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
        mOverlayManager.setOnCalloutOverlayListener((OnCalloutOverlayListener) this);
        poiDataOverlay.showAllPOIdata(30); //0
        //여기까지 생성

        //현재위치 오버레이 띄우는 부분
        mMapMyLocationOverlay = mOverlayManager.createMyLocationOverlay(mMapLocationManager, null);
        //? 이거 왜넣었지?
        ///mMapMyLocationOverlay.refresh();
        mapLayout.addView(mMapView);
    }

    void POIsetting(NMapPOIdata poiData){
        //이제 핀 찍는 시작 함수
        poiData.beginPOIdata(0);

        //여기안에 데이터가 들어가면 됨
        //경도 위도 순
        //레트로핏 사이즈 만큼 핀을 찍음.
        for(int i=0;i<repo.location.size();i++) {
            Log.e(""+repo.location.size(),repo.location.get(i).lat);
            //스트링으로 받아오기 때문에 타입 변형이 필요함
            poiData.addPOIitem(Double.parseDouble(repo.location.get(i).lng),Double.parseDouble(repo.location.get(i).lat),repo.location.get(i).name,markerId,Integer.parseInt(repo.location.get(i).id));
        }
        //test 문
        poiData.addPOIitem(me.longitude,me.latitude,"sample",markerId,null);
        //여기까지
        //여기까지 핀 찍음
        poiData.endPOIdata();
        //마크 객체 생성된거 띄우는 부분
        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
        mOverlayManager.setOnCalloutOverlayListener((OnCalloutOverlayListener) this);
        poiDataOverlay.showAllPOIdata(30); //0
        //여기까지 생성

        //현재위치 오버레이 띄우는 부분
        mMapMyLocationOverlay = mOverlayManager.createMyLocationOverlay(mMapLocationManager, null);
        //오버레이 다시 그리기
        mMapMyLocationOverlay.refresh();
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

    public void index() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<RetrofitRepo> call = retrofitService.getFirst("ashtray");

        //주소를 보여주는 로그
        Log.e(call.request().toString(),call.request().toString());
        call.enqueue(new Callback<RetrofitRepo>() {
            @Override
            public void onResponse(Call<RetrofitRepo> call, Response<RetrofitRepo> response) {
                repo = response.body();
                Log.e(repo.location.get(0).lat,repo.location.get(0).lng);
                POIsetting(poiData);
            }

            @Override
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {

            }
        });
    }
}
