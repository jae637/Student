package com.example.jaehyung.help2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DownFragment extends android.support.v4.app.Fragment {
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root =(ViewGroup)inflater.inflate(R.layout.fragment_down, container, false);
        textView = (TextView)root.findViewById(R.id.textView);
        return root;
    }
    public void setTextView(int num1,int num2, int judge){
        int result=0;
        switch (judge){
            case R.id.bp:
                result=num1+num2;
                break;
            case R.id.bm:
                result=num1-num2;
                break;
            case R.id.by:
                result=num1*num2;
                break;
            case R.id.bd:
                result=num1/num2;
                break;
            case R.id.bc:
                break;
        }
        textView.setText(String.valueOf(result));
    }

    public void setNum(int num1, int num2, int judge){

    }

}