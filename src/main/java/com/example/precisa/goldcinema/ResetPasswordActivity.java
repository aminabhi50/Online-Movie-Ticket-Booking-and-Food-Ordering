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

public class ResetPasswordActivity extends AppCompatActivity {

    protected static EditText otp,pwd,repwd;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        submit = (Button)findViewById(R.id.btnenter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goto9(view);
            }
        });
    }

    protected void goto9(View view)
    {
        pwd = (EditText) findViewById(R.id.et_pwd);
        repwd = (EditText) findViewById(R.id.et_rpwd);
        otp = (EditText) findViewById(R.id.et_otp);
        final String pwd1 = pwd.getText().toString();
        final String repwd1 = repwd.getText().toString();
        final String otp1 = otp.getText().toString();
        final String username1=getIntent().getExtras().getString("un");
        //final String value= getIntent().getExtras().getString("potp");
        //Toast.makeText(ResetPassword.this,"OTP Passed: "+value,Toast.LENGTH_SHORT).show();
        //username.getText().toString().equals("") ||
        if (otp.getText().toString().equals("") ||  pwd.getText().toString().equals("") || repwd.getText().toString().equals(""))
        {
            Toast.makeText(ResetPasswordActivity.this,"Enter all details correctly.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            String url = "http://kidnapped-drifts.000webhostapp.com/reset.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(ResetPasswordActivity.this, response, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ResetPasswordActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username1);
                    params.put("otp", otp1);
                    params.put("pwd", pwd1);
                    params.put("repwd", repwd1);
                    //params.put("rotp", username1);
                    return params;
                }
            };

            //adding our stringrequest to queue
            Volley.newRequestQueue(this).add(stringRequest);
        }
    }
}
