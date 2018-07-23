package com.example.mapdiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by yeonseo kristen han on 2017-05-25.
 */

// In this case, the fragment displays simple text based on the page
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    Intent intent;
    String name;
    String m_id;
    String m_date;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = Intent.getIntent(this);
        mPage = getArguments().getInt(ARG_PAGE);
        m_id=getArguments().getString("id");
        m_date=getArguments().getString("data");
        name=getArguments().getString("name");
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=null;
        switch (mPage)
        {
            case 1:
                view=inflater.inflate(R.layout.activity_map,container,false);
                break;
            case 2:
                view=inflater.inflate(R.layout.activity_calendar,container,false);
                break;
            default:
                break;
        }
        startActivity(intent);
        return view;
    }
}

