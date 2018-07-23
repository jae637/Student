package com.example.mapdiary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedList;

/**
 * Created by 린린 on 2017-05-26.
 */

public class CalendarDialog extends Dialog {

    ArrayAdapter<String> adapter;
    ListView listView;
    Button tomap;
    String id,name;
    LinkedList<Integer> idx;
    LinkedList<String> date;
    int i;

    public CalendarDialog(Context context, LinkedList<Integer> idx, LinkedList<String> date,String id, String name) {
        super(context);
        this.idx = idx;
        this.date = date;
        this.id = id;
        this.name = name;
        Log.i("asd",Integer.toString(date.size()));
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        for(i = 0 ; i<date.size();i++){
            adapter.add(date.get(i) + ": 기록 " + Integer.toString(i+1));
        }

        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(Bundle $savedInstanceState ) {
        super.onCreate( $savedInstanceState ) ;
        setContentView(R.layout.dialog_calendar) ;

        tomap = (Button) findViewById(R.id.tomap);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                // TODO 아이템 클릭시에 구현할 내용은 여기에.
                //Log.i("asda",Integer.toString(idx.get(position)));
                int m_idx = idx.get(position);
                Intent intent = new Intent(getContext(),ReadingActivity.class);
                intent.putExtra("idx",m_idx);
                getContext().startActivity(intent);
            }
        });
        tomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MapActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("date",date.get(0));
                getContext().startActivity(intent);

            }
        });
    }

    /*
    public void setOnDismissListener( OnDismissListener $listener ) {
        _listener = $listener ;
    }
    */
}