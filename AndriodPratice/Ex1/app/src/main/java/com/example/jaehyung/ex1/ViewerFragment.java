package com.example.jaehyung.ex1;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ViewerFragment extends Fragment {

    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_viewer, container, false);
        imageView = (ImageView)root.findViewById(R.id.imageView);
        return root;
    }
    public void setImageView(int resId){
        imageView.setImageResource(resId);
    }
}