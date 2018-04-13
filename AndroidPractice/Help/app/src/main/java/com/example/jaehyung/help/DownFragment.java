package com.example.jaehyung.help;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DownFragment extends Fragment {
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root =(ViewGroup)inflater.inflate(R.layout.fragment_down, container, false);
        textView = (TextView)root.findViewById(R.id.textView);
        return root;
    }
    //public void setTextView(int result){textView.setText(result);}

}