package com.example.precisa.goldcinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
        TextView tvFp;
        EditText etUsername,etPassword;
        Button btnLogin,btnReg;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            etUsername = (EditText) findViewById(R.id.et_un);
            etPassword = (EditText) findViewById(R.id.et_pwd);
            btnLogin = (Button) findViewById(R.id.btn_login);
            btnReg = (Button) findViewById(R.id.btn_reg);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((etUsername.getText().length() >= 0 && etUsername.getText().length() < 8)
                            || (etPassword.getText().length() >= 0 && etPassword.getText().length() < 8)) {
                        etUsername.setError("Length should be between 8-12 characters.");
                        etPassword.setError("Length should be between 8-12 characters.");
                    }
                    else
                    {
                        onlogin();
                    }
                }
            });
            btnReg.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                    startActivity(i);
                }
            });
            tvFp = (TextView)findViewById(R.id.tv_fp);
            tvFp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(LoginActivity.this,FPActivity.class);
                    startActivity(i);
                }
            });
        }

        public void onlogin() {
            String uname = etUsername.getText().toString();
            String pwd = etPassword.getText().toString();
            String type = "login";

            BackgroundWorker backgroundWorker = new BackgroundWorker(LoginActivity.this);
            backgroundWorker.execute(type, uname, pwd);
        }
}
