package com.example.jaehyung.cjh_menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

class MyView extends View {

    int key;
    String str;
    int x,y,c1,c2,c3,c4;//왼쪽 위 오른쪽 위 왼쪽 아래 오른쪽 아래
    Paint paint;

    public MyView(Context context) {
        super(context);
        setBackgroundColor(Color.GREEN);
        str = " ";
        c1=0;
        c2=0;
        c3=0;
        c4=0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x= (int)event.getX(0);
        y=(int) event.getY(0);
        if(200<x&&x<400&&200<y&&y<400){
            c1++;
        }
        if(500<x&&x<700&&200<y&&y<400){
            c2++;
        }
        if(200<x&&x<400&&400<y&&y<700)
            c3++;
        if(500<x&&x<700&&500<y&&y<700)
            c4++;
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setTextSize(60);
        canvas.drawText("오늘의 메뉴 :"+str,100,100,paint);
        paint.setColor(Color.WHITE);
        canvas.drawRect(200,200,400,400,paint);
        canvas.drawRect(500,500,700,700,paint);
        canvas.drawRect(500,200,700,400,paint); // 오른쪽 위
        canvas.drawRect(200,500,400,700,paint); // 왼쪽 아래
        paint.setColor(Color.BLUE);
        if (c1%2==1)
            canvas.drawRect(200,200,400,400,paint);
        if (c2%2==1)
            canvas.drawRect(500,200,700,400,paint);
        if (c3%2==1)
            canvas.drawRect(200,500,400,700,paint);
        if (c4%2==1)
            canvas.drawRect(500,500,700,700,paint);
    }
}
public class MainActivity extends AppCompatActivity {

    LinearLayout m_view;
    MyView myview;
    Button m_b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_view=(LinearLayout)findViewById(R.id.myview);
        myview = (MyView) new MyView(this);
        m_b1=(Button)findViewById(R.id.btn);

        m_view.addView(myview);
        m_b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Listview.class);
                startActivityForResult(intent,1);
            }
        });
        if (savedInstanceState !=null)
        {
            myview.c1=savedInstanceState.getInt("c1");
            myview.c2=savedInstanceState.getInt("c2");
            myview.c3=savedInstanceState.getInt("c3");
            myview.c4=savedInstanceState.getInt("c4");
            myview.str=savedInstanceState.getString("str");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        myview.str=data.getStringExtra("STR");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("c1",myview.c1);
        outState.putInt("c2",myview.c2);
        outState.putInt("c3",myview.c3);
        outState.putInt("c4",myview.c4);
        outState.putString("str",myview.str);
    }
}
