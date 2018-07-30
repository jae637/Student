package com.example.mapdiary;

import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

import static com.example.mapdiary.R.id.map;

/**
 * Created by 린린 on 2017-05-16.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener ,GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

    String myJSON;
    JSONArray board = null;
    LinkedList<Integer> list;

    private static final String TAG_RESULT = "RESULT";
    private static final String TAG_IDX = "IDX";
    private static final String TAG_ID = "ID";
    private static final String TAG_LAT = "LATITUDE";
    private static final String TAG_LNG = "LONGTITUDE";
    private static final String TAG_DATE = "DATE";

    GPSInfo gps;
    static double latitude, longtitude;
    GoogleMap mGoogleMap;
    Intent intent;
    String name;
    String m_id;
    String m_date;
    LatLng MyPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        list = new LinkedList<Integer>();
        intent = getIntent();
        name = intent.getStringExtra("name");
        try {
            m_id = intent.getStringExtra("id");
        }
        catch (Exception e)
        {}
        m_date = intent.getStringExtra("date");
        Toast.makeText(getApplicationContext(),"환영합니다 "+name+"님",Toast.LENGTH_SHORT).show();
        MainActivity.select = 0;
        getData("http://13.112.211.84/mapdiary/mapinfo.php");


        gps = new GPSInfo(MapActivity.this);
        if (gps.isGetLocation()) {
            latitude = gps.getLatitude();
            longtitude = gps.getLongitude();
        }
        else
            gps.showSettingsAlert();

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);


    }



    @Override
    public void onMapReady(final GoogleMap map) {
        mGoogleMap = map;

        MyPosition = new LatLng(latitude, longtitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(MyPosition);
        markerOptions.title("나의위치");
        markerOptions.snippet("지금 여기");


        map.moveCamera(CameraUpdateFactory.newLatLng(MyPosition));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));
        mGoogleMap.setOnMapClickListener(this);
        mGoogleMap.setOnMapLongClickListener(this);
    }

    public void onMapLongClick(LatLng point) {
        Point screenPt = mGoogleMap.getProjection().toScreenLocation(point);
        LatLng latLng = mGoogleMap.getProjection().fromScreenLocation(screenPt);

        MapDialog dialog = new MapDialog(this,latLng,name,m_id,m_date) ;
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
            intent.putExtra("id",m_id);
            intent.putExtra("name",name);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            MainActivity.select = 0;
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    */

    public void getData(String url)
    {
        class GetDataJSON extends AsyncTask<String,Void,String> implements GoogleMap.OnMarkerClickListener {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!=null)
                    {
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();
                }
                catch (Exception e)
                {
                    return null;
                }
            }

            protected void onPostExecute(String result)
            {
                myJSON = result;

                try {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    board = jsonObj.getJSONArray(TAG_RESULT);

                    for (int i = 0; i < board.length(); i++) // 갯수만큼 리스트에 저장
                    {
                        Log.i("zxc",Integer.toString(board.length()));
                        JSONObject c = board.getJSONObject(i);
                        int idx = c.getInt(TAG_IDX);
                        String id = c.getString(TAG_ID);
                        String lat = c.getString(TAG_LAT);
                        String lng = c.getString(TAG_LNG);
                        String date = c.getString(TAG_DATE);
                        double la = Double.valueOf(lat);
                        double la2 = Double.valueOf(lng);
                        if(id.equals(m_id))
                            if(date.equals(m_date)){
                                list.add(idx);
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(new LatLng(la,la2));
                                markerOptions.title(date + " 나의 기록");
                                markerOptions.snippet(Integer.toString(idx));
                                mGoogleMap.addMarker(markerOptions);
                                mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                                mGoogleMap.setOnMarkerClickListener(this);
                            }


                    }

                }
                catch(JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public boolean onMarkerClick(Marker marker) {
                int idx = Integer.parseInt(marker.getSnippet());
                Intent intent = new Intent(MapActivity.this,ReadingActivity.class);
                intent.putExtra("idx",idx);
                startActivity(intent);
                return false;
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        int idx = Integer.parseInt(marker.getSnippet());
        Intent intent = new Intent(MapActivity.this,ReadingActivity.class);
        intent.putExtra("idx",idx);
        startActivity(intent);
        return false;
    }
}

