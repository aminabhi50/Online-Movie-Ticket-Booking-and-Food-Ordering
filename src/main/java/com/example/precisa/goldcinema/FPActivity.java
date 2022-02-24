package com.example.precisa.goldcinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FPActivity extends AppCompatActivity {

    String temp;
    EditText etUn;
    Button btnEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp);

        etUn = (EditText) findViewById(R.id.et_mn);
        btnEnter = (Button) findViewById(R.id.btnenter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                //Toast.makeText(AddressPage.this,"in button event..",Toast.LENGTH_SHORT).show();
                otp(v);

            }
        });
    }
    public void otp(View v)
    {
        /*email=(EditText)findViewById(R.id.editText8);

        if (email.getText().toString().equals(""))
        {
            Toast.makeText(otp.this,"Enter Username && Passwoed.",Toast.LENGTH_SHORT).show();
        }
        else {*/
        final String username1 = etUn.getText().toString();
        String url = "http://kidnapped-drifts.000webhostapp.com/otp.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        temp = response;
                        Toast.makeText(FPActivity.this, temp, Toast.LENGTH_SHORT).show();
                        if(temp.equals("Username does not exists"))
                        {
                            Intent Intent3 = new Intent(FPActivity.this, LoginActivity.class);
                            //Intent3.putExtra("un",username1);
                            startActivity(Intent3);
                            finish();
                        }
                        else
                        {
                            Intent Intent3 = new Intent(FPActivity.this, ResetPasswordActivity.class);
                            Intent3.putExtra("un",username1);
                            startActivity(Intent3);
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FPActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", username1);
                return params;
            }
        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}