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

public class DetailsActivity extends AppCompatActivity {

    String j_string,un,mn;
    int count = 0;
    String mname,cast,img,p,d,desc,sno1,sno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        un = getIntent().getStringExtra("un");
        mn = getIntent().getStringExtra("mn");
        new BackgroundWork().execute(mn);
    }

    public class BackgroundWork extends AsyncTask<String, Void, String> {

        String json_url;
        String json_string;
        String mname;

        @Override
        protected void onPreExecute() {
            json_url = "http://kidnapped-drifts.000webhostapp.com/details.php";
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
        dsetterAdapter sadp = new dsetterAdapter(this, R.layout.drow_layout);
        ListView listView = (ListView)findViewById(R.id.dlv);
        listView.setAdapter(sadp);

        while (count < jsonArray.length()) {
            JSONObject jo = jsonArray.getJSONObject(count);
            cast = jo.getString("Cast");
            img = jo.getString("Image");
            p = jo.getString("P");
            d = jo.getString("D");
            desc = jo.getString("Desc");
            sno = jo.getString("sno");
            mname = mn;
            sno1 = sno;
            dsetter st = new dsetter(mname,cast,img,p,d,desc);
            sadp.add(st);
            count++;
        }
        Button btn = (Button)findViewById(R.id.book);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity.this,ShowtimeActivity.class);
                i.putExtra("un",un);
                i.putExtra("mn",mn);
                i.putExtra("sno",sno1);
                startActivity(i);
            }
        });
    }
}