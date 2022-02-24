package com.example.precisa.goldcinema;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    String j_string,un;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new BackgroundWork().execute();
        un = getIntent().getStringExtra("un");
    }

    public class BackgroundWork extends AsyncTask<String, Void, String> {

        String json_url;
        String json_string;

        @Override
        protected void onPreExecute() {
            json_url = "http://kidnapped-drifts.000webhostapp.com/movielist.php";
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
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
        final setterAdapter sadp = new setterAdapter(this, R.layout.row_layout);
        final ListView listView = (ListView)findViewById(R.id.lv);
        listView.setAdapter(sadp);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                setter st1 = (setter)adapterView.getItemAtPosition(i);
                String mn = st1.getMname();
                intent.putExtra("un",un);
                intent.putExtra("mn",mn);
                startActivity(intent);
            }
        });

        int count = 0;
        String mname,lang,img,type;

        while (count < jsonArray.length()) {
            JSONObject jo = jsonArray.getJSONObject(count);
            img = jo.getString("Image");
            mname = jo.getString("M_Name");
            lang = jo.getString("Lang");
            type = jo.getString("Type");
            setter st = new setter(mname,lang,img,type);
            sadp.add(st);
            count++;
        }
    }
}