package com.example.mapdiary;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String myJSON;
    JSONArray board = null;

    private static final String TAG_RESULT = "RESULT";
    private static final String TAG_ID = "ID";
    private static final String TAG_PASSWORD = "PASSWORD";
    private static final String TAG_NAME = "NAME";

    String User_id;
    String User_password;
    String User_name;
    String m_date;

    Button login_btn, sign_up_btn;
    EditText id_edit;
    EditText password_edit;

    static int select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doCurrentDate();

        sign_up_btn = (Button) findViewById(R.id.sign_up);
        login_btn = (Button) findViewById(R.id.login);
        id_edit = (EditText) findViewById(R.id.id_edit);
        password_edit = (EditText) findViewById(R.id.password_edit);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData("http://13.112.211.84/mapdiary/mapdiary.php");
            }
        });
    }

    public void getData(String url)
    {
        class GetDataJSON extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                User_id = id_edit.getText().toString();
                User_password = password_edit.getText().toString();
                Log.i("asd1",User_id);
                Log.i("asd2",User_password);
            }

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!=null)
                    {
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();
                }
                catch (Exception e)
                {
                    return null;
                }
            }

            protected void onPostExecute(String result)
            {
                myJSON = result;

                try {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    board = jsonObj.getJSONArray(TAG_RESULT);

                    for (int i = 0; i < board.length(); i++) // 갯수만큼 리스트에 저장
                    {
                        JSONObject c = board.getJSONObject(i);
                        String id = c.getString(TAG_ID);
                        String password = c.getString(TAG_PASSWORD);
                        String name = c.getString(TAG_NAME);

                        if(id.equals(User_id)) {
                            Log.i("num1",Integer.toString(select));
                            if (password.equals(User_password)) {
                                Log.i("num2",Integer.toString(select));
                                select = 1;
                                User_name = name;
                                Log.i("num3",Integer.toString(select));
                                break;
                            }
                        }
                    }
                    if(select == 1) {
                        Intent intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
                        intent.putExtra("id", User_id);
                        intent.putExtra("name", User_name);
                        intent.putExtra("date",m_date);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }
                /*
                Intent intent = new Intent(getApplicationContext(), function1.class);
                intent.putExtra("id", id_list);
                intent.putExtra("password", password_list);
                intent.putExtra("name", name);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                */
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);

    }
    public void doCurrentDate(){
        // Date 로 구하기
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
        m_date = fm1.format(new Date());
    }
}