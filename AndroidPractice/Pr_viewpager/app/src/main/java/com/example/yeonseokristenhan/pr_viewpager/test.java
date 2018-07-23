package com.example.yeonseokristenhan.pr_viewpager;

import android.app.Application;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Jaehyung on 2017-05-31.
 */

public class test extends AppCompatActivity {

    TextView m_t;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        m_t=(TextView)findViewById(R.id.test1);
        m_t.setText("바꾼다");
    }
}
