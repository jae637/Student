package com.example.jaehyung.help;


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements UpFragment.TextSendCall {

    UpFragment up;
    DownFragment down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        up = (UpFragment)manager.findFragmentById(R.id.upFragment);
        down = (DownFragment)manager.findFragmentById(R.id.downFragment);
    }

    @Override
    public void printText(String str, String str2, int judge) {
        switch (judge) {
            case 0:
                down.textView.setText(Integer.parseInt(str) + Integer.parseInt(str2));
                break;
            case 1:
                down.textView.setText(Integer.parseInt(str) - Integer.parseInt(str2));
                break;
            case 2:
                down.textView.setText(Integer.parseInt(str) * Integer.parseInt(str2));
                break;
            case 3:
                down.textView.setText(Integer.parseInt(str) / Integer.parseInt(str2));
                break;
            case 4:
                down.textView.setText("");
                break;
            default:
                down.textView.setText("");
                break;

        }
    }

}
