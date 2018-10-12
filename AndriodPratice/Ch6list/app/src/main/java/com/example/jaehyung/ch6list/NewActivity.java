package com.example.jaehyung.ch6list;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity{

    TextView m_t2;
    LinearLayout m_l2;
    Button m_pb,m_sb;
    Intent intent;
    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        intent = getIntent();
        m_t2=(TextView)findViewById(R.id.textview2);
        m_l2=(LinearLayout)findViewById(R.id.subactivity);
        m_pb=(Button)findViewById(R.id.pbtn);
        m_sb=(Button)findViewById(R.id.sbtn);

        color = intent.getIntExtra("color",4);
        switch (color)
        {
            case 0:
                m_t2.setText(R.string.Red);
                m_l2.setBackgroundColor(Color.RED);
                break;
            case 1:
                m_t2.setText("파랑색 입니다");
                m_l2.setBackgroundColor(Color.BLUE);
                break;
            case 2:
                m_t2.setText("노랑색 입니다");
                m_l2.setBackgroundColor(Color.YELLOW);
                break;
            case 3:
                m_t2.setText("초록색 입니다");
                m_l2.setBackgroundColor(Color.GREEN);
                break;
            default:
                break;
        }

        if (savedInstanceState!=null)
        {
            color = savedInstanceState.getInt("color");
            switch (color)
            {
                case 0:
                    m_t2.setText(R.string.Red);
                    m_l2.setBackgroundColor(Color.RED);
                    break;
                case 1:
                    m_t2.setText("파랑색 입니다");
                    m_l2.setBackgroundColor(Color.BLUE);
                    break;
                case 2:
                    m_t2.setText("노랑색 입니다");
                    m_l2.setBackgroundColor(Color.YELLOW);
                    break;
                case 3:
                    m_t2.setText("초록색 입니다");
                    m_l2.setBackgroundColor(Color.GREEN);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("color",color);
    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.pbtn:
                startService(new Intent(this,MusicService.class));
                break;
            case R.id.sbtn:
                stopService(new Intent(this,MusicService.class));
                break;
        }
    }
}
