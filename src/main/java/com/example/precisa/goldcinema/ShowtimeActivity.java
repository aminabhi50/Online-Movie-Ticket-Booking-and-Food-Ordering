package com.example.precisa.goldcinema;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class ShowtimeActivity extends AppCompatActivity {

    Spinner spinner;
    Button b1,b2,b3,b4;
    String mn,un,j_string,today,next,nn,d,sno;
    Date date;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtime);

        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transfer(b1.getText().toString());
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transfer(b2.getText().toString());
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transfer(b3.getText().toString());
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transfer(b4.getText().toString());
            }
        });
        spinner = (Spinner)findViewById(R.id.sp1);

        date = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
        c = Calendar.getInstance();
        c.setTime(date);
        today = fmt.format(date);
        c.add(Calendar.DATE, 1);
        next = fmt.format(c.getTime());
        c.add(Calendar.DATE, 1);
        nn = fmt.format(c.getTime());
        String[] dates = {today,next,nn};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,dates);
        spinner.setAdapter(adapter);

        un = getIntent().getStringExtra("un");
        mn = getIntent().getStringExtra("mn");
        sno = getIntent().getStringExtra("sno");
        d = spinner.getSelectedItem().toString();
        new BackgroundWork().execute(mn);

    }
    public void transfer(String st)
    {
        Intent i = new Intent(ShowtimeActivity.this,SeatSelectionActivity.class);
        i.putExtra("un",un);
        i.putExtra("mn",mn);
        i.putExtra("date",d);
        i.putExtra("sno",sno);
        i.putExtra("stime",st);
        startActivity(i);
    }

    public class BackgroundWork extends AsyncTask<String, Void, String> {

        String json_url;
        String json_string;
        String mname;

        @Override
        protected void onPreExecute() {
            json_url = "http://kidnapped-drifts.000webhostapp.com/showtime.php";
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                mname=strings[0];
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("mn","UTF-8")+"="+URLEncoder.encode(mname,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            j_string = s;
            try {
                jsontask(j_string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void jsontask(String s) throws JSONException {

        JSONObject jsonObject = new JSONObject(s);
        JSONArray jsonArray = jsonObject.getJSONArray("response");

        int count = 0;
        String t1,t2,t3,t4;

        while (count < jsonArray.length()) {
            JSONObject jo = jsonArray.getJSONObject(count);
            t1 = jo.getString("t1");
            t2 = jo.getString("t2");
            t3 = jo.getString("t3");
            t4 = jo.getString("t4");
            count++;
            b1.setText(t1);
            b2.setText(t2);
            b3.setText(t3);
            b4.setText(t4);
        }
    }
}