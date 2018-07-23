package com.example.mapdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.astuetz.PagerSlidingTabStrip;
import com.google.android.gms.maps.model.LatLng;

public class ViewPagerActivity extends AppCompatActivity {

    Intent intent;
    String name;
    String m_id;
    String m_date;
    Fragment fragment = new PageFragment();
    Bundle bundle = new Bundle(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        intent = getIntent();
        m_id=intent.getStringExtra("id");
        name=intent.getStringExtra("name");
        m_date=intent.getStringExtra("date");
        intent.putExtra("id",m_id);
        intent.putExtra("name",name);
        intent.putExtra("date",m_date);
        bundle.putString("id",m_id);
        bundle.putString("name",name);
        bundle.putString("data",m_date);
        fragment.setArguments(bundle);
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
    }

}