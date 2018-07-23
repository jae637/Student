package com.example.jaehyung.cjh_menu;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Jaehyung on 2017-06-12.
 */

public class Listview extends ListActivity{

    int del;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] values = {"삭제기능","물냉면","회냉면","비빔밥","회덮밥","감자전","불고기","갈비찜","육회","갈비탕"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent();
        String item = (String) getListAdapter().getItem(position);
        intent.putExtra("STR",item);
        Toast.makeText(getApplicationContext(),item ,Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK,intent);
        if (position==0) {
            v.setBackgroundColor(Color.RED);
            del=1;
        }
        if(position!=0&&del!=1)
            finish();
        if (del==1&&position!=0)
        {
            l.removeView(v);
        }
    }
}
