package com.example.precisa.goldcinema;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FoodDetailsActivity extends AppCompatActivity {

    Button order,skip;
    String j_string;
    String fname,price,img;
    String un,mn,date,sno,stime,totalseat,tprice;
    ArrayList<String> select;
    public ArrayList<fsetter> foodOrders = new ArrayList<>();
    public ArrayList<fsetter> list = new ArrayList<>();
    private fsetterAdapter sadp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        new BackgroundWork().execute();
        un = getIntent().getStringExtra("un");
        mn = getIntent().getStringExtra("mn");
        date = getIntent().getStringExtra("date");
        sno = getIntent().getStringExtra("sno");
        stime = getIntent().getStringExtra("stime");
        totalseat = getIntent().getStringExtra("totalseat");
        tprice = getIntent().getStringExtra("price");
        select = getIntent().getStringArrayListExtra("select");


        skip = (Button)findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodDetailsActivity.this,BillActivity.class);
                intent.putExtra("un",un);
                intent.putExtra("mn",mn);
                intent.putExtra("date",date);
                intent.putExtra("sno",sno);
                intent.putExtra("stime",stime);
                intent.putStringArrayListExtra("select",select);
                intent.putExtra("totalseat",totalseat);
                intent.putExtra("price",tprice);
                startActivity(intent);
                finish();
            }
        });
    }

    public class BackgroundWork extends AsyncTask<String, Void, String> {

        String json_url;
        String json_string;

        @Override
        protected void onPreExecute() {
            json_url = "http://kidnapped-drifts.000webhostapp.com/foodlist.php";
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

        sadp = new fsetterAdapter(this, list);

        final GridView gridView = (GridView)findViewById(R.id.gv1);
        gridView.setAdapter(sadp);

        order = (Button)findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //placeorder(view);
                /*Intent intent = getIntent();
                Bundle args = intent.getBundleExtra("seats");
                ArrayList<Object> object = (ArrayList<Object>) args.getSerializable("ARRAYLIST");

                Intent intent1 = new Intent(FoodDetailsActivity.this,BillActivity.class);
                Bundle args1 = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)object);
                intent.putExtra("seats",args1);
                startActivity(intent1);*/
                Intent intent = new Intent(FoodDetailsActivity.this,BillActivity.class);
                intent.putExtra("un",un);
                intent.putExtra("mn",mn);
                intent.putExtra("date",date);
                intent.putExtra("sno",sno);
                intent.putExtra("stime",stime);
                intent.putStringArrayListExtra("select",select);
                intent.putExtra("totalseat",totalseat);
                intent.putExtra("price",tprice);
                startActivity(intent);
                finish();
            }
        });


        int count = 0;

        while (count < jsonArray.length()) {
            JSONObject jo = jsonArray.getJSONObject(count);
            fname = jo.getString("F_Name");
            price = jo.getString("Price");
            img = jo.getString("Image");
            fsetter st = new fsetter(fname,price,img);
            sadp.add(st);
            count++;
        }
    }

    /*public void placeorder(View view) {

        for(int i=0;i<sadp.list.size();i++)
        {
            if(sadp.list.get(i).fquantity > 0)
            {
                fsetter items = new fsetter(sadp.list.get(i).Fname,
                        sadp.list.get(i).Price,
                        sadp.list.get(i).Img
                );
                items.fquantity = sadp.list.get(i).fquantity;
                foodOrders.add(items);
            }
        }
        Toast.makeText(getApplicationContext(),sadp.list.size(),Toast.LENGTH_LONG).show();
    }*/
}