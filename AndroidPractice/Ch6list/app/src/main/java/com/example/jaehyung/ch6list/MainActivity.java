package com.example.jaehyung.ch6list;

import android.content.Intent;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button m_b1,m_b2;
    TextView m_t1;
    LinearLayout m_l1;
    Intent intent;
    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
        m_b1=(Button)findViewById(R.id.btn);
        m_b2=(Button)findViewById(R.id.btn2);
        m_t1=(TextView)findViewById(R.id.textview);
        m_l1=(LinearLayout)findViewById(R.id.mainactivity);
        color = 4;

        m_b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,ListView.class);
                startActivityForResult(intent,1);
            }
        });
        m_b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(),NewActivity.class);
                intent.putExtra("color",color);
                startActivity(intent);
            }
        });
        if (savedInstanceState!=null)
        {
            color = savedInstanceState.getInt("color");
            switch (color)
            {
                case 0:
                    m_t1.setText("빨강색 입니다");
                    m_l1.setBackgroundColor(Color.RED);
                    break;
                case 1:
                    m_t1.setText("파랑색 입니다");
                    m_l1.setBackgroundColor(Color.BLUE);
                    break;
                case 2:
                    m_t1.setText("노랑색 입니다");
                    m_l1.setBackgroundColor(Color.YELLOW);
                    break;
                case 3:
                    m_t1.setText("초록색 입니다");
                    m_l1.setBackgroundColor(Color.GREEN);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        color=data.getIntExtra("COLOR",4);
        switch (color)
        {
            case 0:
                m_t1.setText("빨강색 입니다");
                m_l1.setBackgroundColor(Color.RED);
                break;
            case 1:
                m_t1.setText("파랑색 입니다");
                m_l1.setBackgroundColor(Color.BLUE);
                break;
            case 2:
                m_t1.setText("노랑색 입니다");
                m_l1.setBackgroundColor(Color.YELLOW);
                break;
            case 3:
                m_t1.setText("초록색 입니다");
                m_l1.setBackgroundColor(Color.GREEN);
                break;
            default:
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("color",color);
    }
}
