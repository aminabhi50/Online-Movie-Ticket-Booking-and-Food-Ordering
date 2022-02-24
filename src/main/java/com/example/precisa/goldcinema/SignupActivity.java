package com.example.precisa.goldcinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    EditText etUsername, etEmail, etPassword, etMobileno;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etUsername = (EditText) findViewById(R.id.et_un);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_pwd);
        etMobileno = (EditText) findViewById(R.id.et_mn);
        btnSignup = (Button) findViewById(R.id.btn_reg);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((etUsername.getText().length() >= 0 && etUsername.getText().length() < 8)
                        || (etPassword.getText().length() >= 0 && etPassword.getText().length() < 8)) {
                    etUsername.setError("Length should be between 8-12 characters.");
                    etPassword.setError("Length should be between 8-12 characters.");
                }
                if (etMobileno.length() == 0) {
                    etMobileno.setError("This field is required.");
                }
                if(etEmail.getText().length() == 0)
                {
                    etEmail.setError("This field is required.");
                }
                if(etEmail.getText().length() != 0)
                {
                    String e = etEmail.getText().toString();
                    boolean result = !TextUtils.isEmpty(e) && android.util.Patterns.EMAIL_ADDRESS.matcher(e).matches();
                    if(result == false)
                    {
                        etEmail.setError("Invalid Email.");
                    }
                }
                if (etMobileno.length() < 10) {
                    etMobileno.setError("Length should be 10 digits.");
                }
                if (etUsername.getText().length()>=8
                        && etMobileno.length()==10 && etPassword.getText().length()>=8 && etEmail.getText().length()>0)
                {
                    onsignup();
                }
            }
        });
    }

    public void onsignup() {
        String uname = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String pwd = etPassword.getText().toString();
        String mno = etMobileno.getText().toString();
        String type = "register";

        BackgroundWorker backgroundWorker = new BackgroundWorker(SignupActivity.this);
        backgroundWorker.execute(type, uname, email, pwd, mno);
    }
}
