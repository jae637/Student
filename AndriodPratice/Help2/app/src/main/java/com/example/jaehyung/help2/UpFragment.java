package com.example.jaehyung.help2;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpFragment extends android.support.v4.app.Fragment {

    EditText et1, et2;
    int num1=0, num2=0;
    Button bp, bm, by, bd, bc;
    int result;

    public static interface TextSendCall {
        public void printText(int num1, int num2, int judge);
    }

    public TextSendCall callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TextSendCall)
            callback = (TextSendCall) context;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_up, container, false);
        et1 = (EditText) root.findViewById(R.id.editText);
        et2 = (EditText) root.findViewById(R.id.editText2);
        bp = (Button) root.findViewById(R.id.bp);
        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    setNum();
                    callback.printText(num1,num2,R.id.bp);
                }
            }
        });
        bm = (Button) root.findViewById(R.id.bm);
        bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    setNum();
                    callback.printText(num1 , num2,R.id.bm);
                }
            }
        });
        by = (Button) root.findViewById(R.id.by);
        by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback!=null){
                    setNum();
                    callback.printText(num1 , num2,R.id.by);
                }
            }
        });
        bd = (Button) root.findViewById(R.id.bd);
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback !=null) {
                    setNum();
                    callback.printText(num1, num2, R.id.bd);
                }
            }
        });
        bc = (Button) root.findViewById(R.id.bc);
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback !=null) {
                    setNum();
                    callback.printText(0, 0, R.id.bc);
                }
            }
        });
        return root;
    }
    /*public void help(View v){
        setNum();
        switch (v.getId()){
            case R.id.bp:
                callback.printText(num1,num2,R.id.bp);
                break;
            case R.id.bc:
                callback.printText(num1,num2,R.id.bc);
                break;
            case R.id.bm:
                callback.printText(num1,num2,R.id.bm);
                break;
            case R.id.by:
                callback.printText(num1,num2,R.id.by);
                break;
            case R.id.bd:
                callback.printText(num1,num2,R.id.bd);
                break;
        }
    }*/

    public void setNum(){
        if (et1.getText().equals(null) || et2.getText().equals(null)){
            num1=0;
            num2=0;
            return;
        }
        num1 = Integer.parseInt(et1.getText().toString());
        num2 = Integer.parseInt(et2.getText().toString());
    }
}