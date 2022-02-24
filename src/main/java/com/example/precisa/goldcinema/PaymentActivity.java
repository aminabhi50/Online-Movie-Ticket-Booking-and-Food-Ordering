package com.example.precisa.goldcinema;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    String un, mn, date, sno, stime, totalseat, tprice, sseats, popt;
    Button payment;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        un = getIntent().getStringExtra("un");
        mn = getIntent().getStringExtra("mn");
        date = getIntent().getStringExtra("date");
        sno = getIntent().getStringExtra("sno");
        stime = getIntent().getStringExtra("stime");
        totalseat = getIntent().getStringExtra("totalseat");
        tprice = getIntent().getStringExtra("price");
        sseats = getIntent().getStringExtra("select");

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        payment = (Button) findViewById(R.id.button3);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(id);
                popt = radioButton.getText().toString();
                onpayment(view);
            }
        });
    }

    public void onpayment(View view)
    {

        String url = "http://kidnapped-drifts.000webhostapp.com/book.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(PaymentActivity.this, response, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PaymentActivity.this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("un", un);
                params.put("mn", mn);
                params.put("date", date);
                params.put("sno", sno);
                params.put("stime", stime);
                params.put("tseat", totalseat);
                params.put("tprice", tprice);
                params.put("sseats", sseats);
                params.put("popt", popt);
                return params;
            }
        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}