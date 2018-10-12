package com.example.yeonseokristenhan.pr_viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by yeonseo kristen han on 2017-05-25.
 */

// In this case, the fragment displays simple text based on the page
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    Intent intent;
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
        mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        switch (mPage)
        {
            case 1:
                view = inflater.inflate(R.layout.fragment_page,container, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.test_layout,container,false);
                callClass(intent);
                break;
            default:
                break;
        }
        return view;
    }

    public void callClass(Intent intent)
    {
        intent= new Intent(getActivity(),test.class);
        startActivity(intent);
    }


}

