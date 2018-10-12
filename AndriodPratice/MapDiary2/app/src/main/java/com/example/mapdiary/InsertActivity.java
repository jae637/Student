package com.example.mapdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InsertActivity extends AppCompatActivity {

    String name,id,date;
    TextView lat_tv,lng_tv;
    double lat,lng;
    Button insertMemo_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        lat = getIntent().getDoubleExtra("lat",0);
        lng = getIntent().getDoubleExtra("lng",0);
        name = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("date");
        id = getIntent().getStringExtra("id");


        lat_tv = (TextView) findViewById(R.id.lat_tv);
        lng_tv = (TextView) findViewById(R.id.lng_tv);

        lat_tv.setText("위도: "+Double.toString(lat));
        lng_tv.setText("경도: "+Double.toString(lng));

        insertMemo_btn = (Button) findViewById(R.id.insertMemoBtn);

        insertMemo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),InsertMemoActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lng",lng);
                intent.putExtra("name",name);
                intent.putExtra("date",date);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}
