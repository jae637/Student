package com.example.mapdiary;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadingActivity extends AppCompatActivity {
    String myJSON;
    JSONArray board = null;
    TextView m_title,m_content;

    private static final String TAG_RESULT = "RESULT";
    private static final String TAG_ID = "ID";
    private static final String TAG_LAT = "LATITUDE";
    private static final String TAG_LNG = "LONGTITUDE";
    private static final String DATE = "DATE";
    private static final String NUM = "NUM";
    private static final String MEMOTITLE = "MEMOTITLE";
    private static final String MEMOCONTENT = "MEMOCONTENT";

    int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        idx = getIntent().getIntExtra("idx",0);
        m_title = (TextView) findViewById(R.id.text_title);
        m_content = (TextView) findViewById(R.id.text_content);
        getData("http://13.112.211.84/mapdiary/read.php?idx="+Integer.toString(idx));
    }
    public void getData(String url)
    {
        class GetDataJSON extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
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
                        String lat = c.getString(TAG_LAT);
                        String lng = c.getString(TAG_LNG);
                        String date = c.getString(DATE);
                        int num = c.getInt(NUM);
                        String title = c.getString(MEMOTITLE);
                        String content = c.getString(MEMOCONTENT);

                        m_title.setText(title);
                        m_content.setText(content);


                    }
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);

    }
}
