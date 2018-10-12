package com.example.jaehyung.ch6list;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * Created by Jaehyung on 2017-06-11.
 */

public class ListView extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] value={"빨강색","파랑색","노랑색","초록색"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,value);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(android.widget.ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent();
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this,item + "을 선택하셨습니다",Toast.LENGTH_SHORT).show();
        intent.putExtra("COLOR",position);
        setResult(RESULT_OK,intent);
        finish();
    }
}
