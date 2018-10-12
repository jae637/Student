package com.example.jaehyung.ibeacon;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.estimote.coresdk.cloud.google.model.Beacons;
import com.estimote.mgmtsdk.feature.fu.ota.BeaconOtaAdapter;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MonitoringActivity extends AppCompatActivity implements BeaconConsumer {
    protected static final String TAG = "MonitoringActivity";

    private ListView list = null;
    private BeaconAdapter adapter = null;
    private ArrayList<Beacon> arrayL = new ArrayList<Beacon>();
    private LayoutInflater inflater;

    private BeaconServiceUtility beaconUtil = null;
    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        beaconUtil = new BeaconServiceUtility(this);
        list = (ListView) findViewById(R.id.list);
        adapter = new BeaconAdapter();
        list.setAdapter(adapter);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        beaconUtil.onStart(beaconManager, this);
    }

    @Override
    protected void onStop() {
        beaconUtil.onStop(beaconManager, this);
        super.onStop();
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> Beacons, Region region) {
                arrayL.clear();
                arrayL.addAll((ArrayList<Beacon>) Beacons);
                adapter.notifyDataSetChanged();
            }
        });

        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.e("BeaconDetactorService", "didEnterRegion");
            }

            @Override
            public void didExitRegion(Region region) {
                Log.e("BeaconDetactorService", "didExitRegion");
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {
                Log.e("BeaconDetactorService", "didDetermineStateForRegion");
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class BeaconAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (arrayL != null && arrayL.size() > 0)
                return arrayL.size();
            else
                return 0;
        }

        @Override
        public Beacon getItem(int arg0) {
            return arrayL.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            try {
                ViewHolder holder;

                if (convertView != null) {
                    holder = (ViewHolder) convertView.getTag();
                } else {
                    holder = new ViewHolder(convertView = inflater.inflate(R.layout.tupple_monitoring, null));
                }
                if (null != arrayL.get(position).getId1())
                    holder.beacon_uuid.setText("UUID: " + arrayL.get(position).getServiceUuid());

                holder.beacon_major.setText("Major: " + arrayL.get(position).getId3());

                holder.beacon_minor.setText(", Minor: " + arrayL.get(position).getId2());

                holder.beacon_proximity.setText("Proximity: " + arrayL.get(position).getDistance());

                holder.beacon_rssi.setText(", Rssi: " + arrayL.get(position).getRssi());

                holder.beacon_txpower.setText(", TxPower: " + arrayL.get(position).getTxPower());

                holder.beacon_range.setText("" + arrayL.get(position).getDistance());

            } catch (Exception e) {
                e.printStackTrace();
            }

            return convertView;
        }

        private class ViewHolder {
            private TextView beacon_uuid;
            private TextView beacon_major;
            private TextView beacon_minor;
            private TextView beacon_proximity;
            private TextView beacon_rssi;
            private TextView beacon_txpower;
            private TextView beacon_range;

            public ViewHolder(View view) {
                beacon_uuid = (TextView) view.findViewById(R.id.BEACON_uuid);
                beacon_major = (TextView) view.findViewById(R.id.BEACON_major);
                beacon_minor = (TextView) view.findViewById(R.id.BEACON_minor);
                beacon_proximity = (TextView) view.findViewById(R.id.BEACON_proximity);
                beacon_rssi = (TextView) view.findViewById(R.id.BEACON_rssi);
                beacon_txpower = (TextView) view.findViewById(R.id.BEACON_txpower);
                beacon_range = (TextView) view.findViewById(R.id.BEACON_range);

                view.setTag(this);
            }
        }

    }
}
