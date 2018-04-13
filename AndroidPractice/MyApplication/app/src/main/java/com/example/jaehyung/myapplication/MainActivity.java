package com.example.jaehyung.myapplication;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.graphics.Region;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.estimote.internal_plugins_api.scanning.MacAddress;

import org.altbeacon.beacon.BeaconConsumer;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements BeaconConsumer {

    private BeaconManager beaconManager;
    BeaconRegion region;

    private TextView caryF, caryR, caryL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        caryF = (TextView) findViewById(R.id.caryF);
        caryR = (TextView) findViewById(R.id.caryR);
        caryL = (TextView) findViewById(R.id.caryL);
        beaconManager = new BeaconManager(this);

        beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion beaconRegion, List<Beacon> beacons) {
                if (!beacons.isEmpty()) {
                    Beacon caryf = null, caryr = null, caryl = null;
                    Beacon nearestBeacon = beacons.get(0);
                    for (int i = 0; i < beacons.size(); i++) {
                        if (beacons.get(i).getMacAddress().toString().equals("[D4:36:39:D8:DA:A9]"))
                            caryf = beacons.get(i);
                        if (beacons.get(i).getMacAddress().toString().equals("[D4:36:39:D8:B3:F7]"))
                            caryr = beacons.get(i);
                        if (beacons.get(i).getMacAddress().toString().equals("[D4:36:39:D8:B3:0B]"))
                            caryl = beacons.get(i);
                    }

                    Log.d("Airport", "Nearest places: " + nearestBeacon.getRssi());
                    caryF.setText(caryf.getMacAddress().toString());
                    caryF.append("(caryF)");
                    caryF.append(":" + caryf.getRssi());
                    caryR.setText(caryr.getMacAddress().toString());
                    caryR.append("(caryR)");
                    caryR.append(":" + caryr.getRssi());
                    caryL.setText(caryl.getMacAddress().toString());
                    caryL.append("(caryL)");
                    caryL.append(":" + caryl.getRssi());
                }
            }
        });

        try {
            region = new BeaconRegion("iBeacon", UUID.fromString("74278bda-b644-4520-8f0c-720eaf059935"), 4660, 64001);//본인이 연결할 비콘의 아이디와 메이저(0x1234)/마이너(0xfa01 코드를 알아야 한다.);
        } catch (Exception e) {
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion beaconRegion, List<Beacon> beacons) {
                Toast.makeText(getApplicationContext(), "접근", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onExitedRegion(BeaconRegion beaconRegion) {
                Toast.makeText(getApplicationContext(), "끊어짐", Toast.LENGTH_LONG).show();
            }
        });
    }
}
