package com.example.jaehyung.beacon1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements BeaconConsumer{

    private BeaconManager beaconManger;
    private List<org.altbeacon.beacon.Beacon> beaconList = new ArrayList<org.altbeacon.beacon.Beacon>();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //실제로 비콘을 탐지하기 위한 비콘 매니저 객체를 초기화
        beaconManger = BeaconManager.getInstanceForApplication(this);
        textView = (TextView)findViewById(R.id.Textview);

        //여기가 중요, 기기에 따라서 setBeaconLayout 안의 내용을 바꿔줘야 하는듯
        //필자의 경우, 아래처럼 하니 잘 동작.
        beaconManger.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

        //비콘 탐지를 시작한다. 실제로는 서비스를 시작
        beaconManger.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //서비스 종료
        beaconManger.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManger.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<org.altbeacon.beacon.Beacon> beacons, Region region) {
                //비콘이 감지되면 해당 함수가 호출된다. Collection<Beacon> beacons에는 감지된 비콘의 리스트가,
                //region에는 비콘들에 대응하는 Region 객체가 들어온다.
                if (beacons.size()>0){
                    beaconList.clear();
                    for(org.altbeacon.beacon.Beacon beacon :beacons){
                        beaconList.add(beacon);
                    }
                }
            }
        });

        try {
            beaconManger.startRangingBeaconsInRegion(new Region("myRangingUniqueId", Identifier.fromUuid(UUID.fromString("74278bda-b644-4520-8f0c-720eaf059935")),Identifier.fromInt(4660),Identifier.fromInt(64001)));
        } catch (RemoteException e) {

        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            textView.setText("");

            for(Beacon beacon : beaconList){
                textView.append(("ID : " + beacon.getId2() + "Distance:" + Double.parseDouble(String.format("%.3f",beacon.getDistance()))+"m\n"));
            }

            handler.sendEmptyMessageDelayed(0,1000);
        }
    };

    public void OnButtonClicked(View view){
        handler.sendEmptyMessage(0);
    }
}
