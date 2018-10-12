package com.example.mapdiary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 린린 on 2017-05-25.
 */

public class RegistDialog extends Dialog {

    Button cancelBtn,okBtn;
    double lat, lng;
    String name, id;
    String title, content;

    public RegistDialog(Context context,double lat,double lng,String name,String id,String title, String content) {
        super(context);
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.id = id;
        this.title = title;
        this.content = content;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate( Bundle $savedInstanceState ) {
        super.onCreate( $savedInstanceState ) ;
        setContentView(R.layout.dialog_register);

        cancelBtn = (Button) findViewById(R.id.cancel);
        okBtn = (Button) findViewById(R.id.OK);



        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SendMemo(id,lat,lng,title,content).execute();
                //디비에 등록하는 함수 돌린다음에 finish();
                Intent intent = new Intent(getContext(),MapActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("id",id);
                getContext().startActivity(intent);
            }
        });
    }

}