package com.example.mapdiary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertMemoActivity extends AppCompatActivity {
    String name,id;
    String m_title, m_content;
    double lat,lng;
    EditText title, content;
    Button register;
    RegistDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_memo);

        lat =  getIntent().getDoubleExtra("lat",0);
        lng =  getIntent().getDoubleExtra("lng",0);
        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");


        title = (EditText) findViewById(R.id.memo_title);
        content = (EditText) findViewById(R.id.memo_content);

        register = (Button) findViewById(R.id.register_memo);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_title = title.getText().toString();
                m_content = content.getText().toString();

                dialog = new RegistDialog(InsertMemoActivity.this,lat,lng,name,id,m_title,m_content);
                dialog.show();
            }
        });



    }
}
