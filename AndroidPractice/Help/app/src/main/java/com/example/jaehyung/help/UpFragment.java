package com.example.jaehyung.help;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class UpFragment extends Fragment {

    EditText et1, et2;
    String num1, num2;
    Button bp, bm, by, bd, bc;
    int result;

    public static interface TextSendCall {
        public void printText(String str, String str2, int judge);
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
                callback.printText(et1.getText().toString(),et2.getText().toString(),0);
            }
        });
        bm = (Button) root.findViewById(R.id.bm);
        bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.printText(et1.getText().toString(),et2.getText().toString(),1);
            }
        });
        by = (Button) root.findViewById(R.id.by);
        by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.printText(et1.getText().toString(),et2.getText().toString(),2);
            }
        });
        bd = (Button) root.findViewById(R.id.bd);
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.printText(et1.getText().toString(),et2.getText().toString(),3);
            }
        });
        bc = (Button) root.findViewById(R.id.bc);
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.printText(et1.getText().toString(),et2.getText().toString(),4);
            }
        });
        return root;
    }
}