package com.example.jaehyung.timetable;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Arrays;

class MyView extends View {

    int key;
    int x,y;
    int sizex,sizey;
    int[] s,red,blue,green,ma,gray;
    int colors;
    Paint p;

    public MyView(Context context) {
        super(context);
        colors=1;
        x=0;
        y=0;
        sizex=30;
        sizey=154;
        s= new int[65];
        gray= new int[65];
        ma= new int[65];
        green= new int[65];
        blue= new int[65];
        red= new int[65];
        Arrays.fill(s,0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x=(int)event.getX(0);
        y=(int)event.getY(0);
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p=new Paint();
        p.setTextSize(60);
        canvas.drawText("월",100,100,p);
        canvas.drawText("화",300,100,p);
        canvas.drawText("수",500,100,p);
        canvas.drawText("목",700,100,p);
        canvas.drawText("금",900,100,p);

        for(int i=0;i<65;i++) {
            p.setColor(Color.WHITE);
            p.setStyle(Paint.Style.FILL);
            if (sizex < x && x < sizex + 200 && sizey < y && y < sizey + 100) {
                s[i]++;
                red[i]=0;
                blue[i]=0;
                green[i]=0;
                gray[i]=0;
                ma[i]=0;
                switch (colors)
                {
                    case 1:
                        red[i]=1;
                        break;
                    case 2:
                        blue[i]=1;
                        break;
                    case 3:
                        green[i]=1;
                        break;
                    case 4:
                        ma[i]=1;
                        break;
                    case 5:
                        gray[i]=1;
                        break;
                }
            }
            if(s[i]%2==1) {
                if (red[i]==1)
                    p.setColor(Color.RED);
                if (blue[i]==1)
                    p.setColor(Color.BLUE);
                if (green[i]==1)
                    p.setColor(Color.GREEN);
                if (ma[i]==1)
                    p.setColor(Color.MAGENTA);
                if (gray[i]==1)
                    p.setColor(Color.GRAY);
            }
            canvas.drawRect(sizex, sizey, sizex + 200, sizey + 100, p);
            p.setTextSize(30);
            p.setColor(Color.BLACK);
            canvas.drawText(((i+1)%13)+"",sizex+50,sizey+50,p);
            p.setColor(Color.BLACK);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawRect(sizex, sizey, sizex + 200, sizey + 100, p);
            if (sizey > 1300) {
                sizey = 50;
                sizex = sizex + 204;
            }

            sizey = sizey + 104;
        }
        sizex=30;
        sizey=154;
    }
}


public class MainActivity extends AppCompatActivity {

    LinearLayout m_l1;
    MyView m_v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        m_l1= (LinearLayout)findViewById(R.id.frame);
        m_v1=(MyView)new MyView(this);
        m_l1.addView(m_v1);

        if (savedInstanceState !=null)
        {
            m_v1.s=savedInstanceState.getIntArray("s");
            m_v1.red=savedInstanceState.getIntArray("red");
            m_v1.blue=savedInstanceState.getIntArray("blue");
            m_v1.green=savedInstanceState.getIntArray("green");
            m_v1.ma=savedInstanceState.getIntArray("ma");
            m_v1.gray=savedInstanceState.getIntArray("gray");
            m_v1.colors=savedInstanceState.getInt("color");
        }
    }
//int[] s,red,blue,green,ma,gray;
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("s",m_v1.s);
        outState.putIntArray("red",m_v1.red);
        outState.putIntArray("blue",m_v1.blue);
        outState.putIntArray("green",m_v1.green);
        outState.putIntArray("ma",m_v1.ma);
        outState.putIntArray("gray",m_v1.gray);
        outState.putInt("color",m_v1.colors);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem item1 = menu.add(0,1,0,"빨강");
        menu.add(0,2,0,"파랑");
        menu.add(0,3,0,"초록");
        menu.add(0,4,0,"자주");
        menu.add(0,5,0,"회색");
        menu.add(0,6,0,"전부 지우기");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case 1:
                m_v1.colors=1;
                break;
            case 2:
                m_v1.colors=2;
                break;
            case 3:
                m_v1.colors=3;
                break;
            case 4:
                m_v1.colors=4;
                break;
            case 5:
                m_v1.colors=5;
                break;
            case 6:
                for(int a=0; a < 65 ; a++)
                    m_v1.s[a]=0;
                m_v1.invalidate();
                m_v1.x=0;
                m_v1.y=0;
                m_v1.colors=1;
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}