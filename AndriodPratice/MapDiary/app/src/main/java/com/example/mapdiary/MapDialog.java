package com.example.mapdiary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by 린린 on 2017-05-17.
 */

public class MapDialog extends Dialog {

    Button ReadBtn,InsertBtn;
    LatLng point;
    String id, name;

    public MapDialog(Context context,LatLng latlng,String name, String id) {
        super(context);
        point = latlng;
        this.name = name;
        this.id = id;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(Bundle $savedInstanceState ) {
        super.onCreate( $savedInstanceState ) ;
        setContentView(R.layout.dialog_map) ;

        ReadBtn = (Button) findViewById(R.id.ReadBtn);
        InsertBtn = (Button) findViewById(R.id.InsertBtn);



        ReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),ReadingActivity.class);
                getContext().startActivity(intent);
                /*
                다이얼로그가 종료 될 때 수행될 처리를 위해 OnDissMissDialog라는게 존재한다는것만 알아두자
                // TODO Auto-generated method stub
                if( _listener == null ){}
                else {
                    _listener.onDismiss(MapDialog.this) ;
                }
                dismiss() ;
                */
            }
        });

        InsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),InsertActivity.class);
                intent.putExtra("lat",point.latitude);
                intent.putExtra("lng",point.longitude);
                intent.putExtra("name",name);
                intent.putExtra("id",id);
                getContext().startActivity(intent);
            }
        });
    }

    /*
    public void setOnDismissListener( OnDismissListener $listener ) {
        _listener = $listener ;
    }
    */
}