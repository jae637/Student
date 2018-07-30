package com.example.mapdiary;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

@SuppressLint("SimpleDateFormat")
public class CalendarActivity extends AppCompatActivity {
    private CaldroidFragment caldroidFragment;
    SimpleDateFormat formatter;

    int num = 0;
    Date m_date;
    String myJSON;
    JSONArray board = null;
    private static final String TAG_RESULT = "RESULT";
    private static final String TAG_IDX = "IDX";
    private static final String TAG_ID = "ID";
    private static final String TAG_DATE = "DATE";

    LinkedList<String> l_date;
    LinkedList<Integer> l_idx;

    String m_id;
    String m_name;

    // 캘린더 시작전에 동작하는 함수라고 생각하면 된다.
    private void setCustomResourceForDates() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        m_id = getIntent().getStringExtra("id");
        m_name = getIntent().getStringExtra("name");

        l_idx = new LinkedList<Integer>();
        l_date = new LinkedList<String>();

       formatter = new SimpleDateFormat("yyyy-MM-dd");

        // Setup caldroid fragment
        // **** If you want normal CaldroidFragment, use below line ****
        caldroidFragment = new CaldroidFragment();

        // //////////////////////////////////////////////////////////////////////
        // **** This is to show customized fragment. If you want customized
        // version, uncomment below line ****
//		 caldroidFragment = new CaldroidSampleCustomFragment();

        // Setup arguments

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

            // Uncomment this to customize startDayOfWeek
            // args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
            // CaldroidFragment.TUESDAY); // Tuesday

            // Uncomment this line to use Caldroid in compact mode
            // args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

            // Uncomment this line to use dark theme
//            args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);

            caldroidFragment.setArguments(args);
        }

        setCustomResourceForDates();

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                // 여기부분에서 이제 달력을 클릭하면 어떤 동작이 발생하는지 정의 해 주어야 한다.

                m_date = date;
                getData("http://13.112.211.84/mapdiary/mapinfo.php");

                //Toast.makeText(getApplicationContext(), formatter.format(date),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                Toast.makeText(getApplicationContext(),
                        "Long click " + formatter.format(date),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {
                    Toast.makeText(getApplicationContext(),
                            "Caldroid view is created", Toast.LENGTH_SHORT)
                            .show();
                }
            }

        };

        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);

        final TextView textView = (TextView) findViewById(R.id.textview);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);

        if (caldroidFragment != null) {
            caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
        }
    }
    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            protected void onPostExecute(String result) {
                l_idx.clear();
                l_date.clear();
                myJSON = result;

                try {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    board = jsonObj.getJSONArray(TAG_RESULT);

                    for (int i = 0; i < board.length(); i++) // 갯수만큼 리스트에 저장
                    {
                        JSONObject c = board.getJSONObject(i);
                        int idx = c.getInt(TAG_IDX);
                        String id = c.getString(TAG_ID);
                        String date = c.getString(TAG_DATE);
                        if(id.equals(m_id))
                            if(date.equals(formatter.format(m_date))){
                                l_date.add(date);
                                l_idx.add(idx);
                            }



                    }

                }

                catch(JSONException e) {
                    e.printStackTrace();
                }

                CalendarDialog calD = new CalendarDialog(CalendarActivity.this,l_idx,l_date,m_id,m_name);
                calD.show();
            }

        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);

    }

}