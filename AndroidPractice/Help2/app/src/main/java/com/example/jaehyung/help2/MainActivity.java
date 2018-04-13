package com.example.jaehyung.help2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements UpFragment.TextSendCall {

    UpFragment up;
    DownFragment down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        up = (UpFragment)manager.findFragmentById(R.id.upFragment);
        down = (DownFragment)manager.findFragmentById(R.id.downFragment);
        down.textView.setText("Result");
    }

    @Override
    public void printText(int num1,int num2, int judge) {
            down.setTextView(num1,num2,judge);
    }

}
