package com.example.precisa.goldcinema;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

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

import javax.net.ssl.HttpsURLConnection;

import static java.security.AccessController.getContext;

/**
 * Created by Precisa on 20-Feb-18.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String>{
    Context context;
    ProgressDialog progressDialog;
    AlertDialog.Builder alertDialog;
    Activity activity;
    String uname;
    BackgroundWorker(Context ctx)
    {
        this.context = ctx;
        activity = (Activity)ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://kidnapped-drifts.000webhostapp.com/login.php";
        String reg_url = "http://kidnapped-drifts.000webhostapp.com/reg.php";
        if(type.equals("login"))
        {
            try {
                URL url = new URL(login_url);
                try {
                    String un = params[1];
                    String pwd = params[2];
                    uname=un;
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data = URLEncoder.encode("un","UTF-8")+"="+URLEncoder.encode(un,"UTF-8")+"&"+
                            URLEncoder.encode("pwd","UTF-8")+"="+URLEncoder.encode(pwd,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while((line=bufferedReader.readLine())!=null)
                    {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    Thread.sleep(5000);
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("register"))
        {
            try {
                URL url = new URL(reg_url);
                try {
                    String un = params[1];
                    String pwd = params[2];
                    String email = params[3];
                    String mno = params[4];
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data = URLEncoder.encode("un","UTF-8")+"="+URLEncoder.encode(un,"UTF-8")+"&"+
                            URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                            URLEncoder.encode("pwd","UTF-8")+"="+URLEncoder.encode(pwd,"UTF-8")+"&"+
                            URLEncoder.encode("mno","UTF-8")+"="+URLEncoder.encode(mno,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while((line=bufferedReader.readLine())!=null)
                    {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    Thread.sleep(5000);
                    Log.d("Test","Test 3 Pass");
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(activity);
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Connecting to Server....");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String r) {
        progressDialog.dismiss();
        if(r!=null && r.equals("Registration Successful."))
        {
            showDialog("Registration Status",r,"reg_t");
        }
        else if(r!=null && r.equals("Some server error occurred. Try Again.."))
        {
            showDialog("Registration Status",r,"reg_f");
        }
        else if(r!=null && r.equals("User Exists...."))
        {
            showDialog("Registration Status",r,"reg_u");
        }
        else if(r!=null && r.equals("Login successful."))
        {
            Intent i = new Intent(activity, HomeActivity.class);
            i.putExtra("un",uname);
            activity.startActivity(i);
            activity.finish();
        }
        else if(r!=null && r.equals("Login unsuccessful."))
        {
            showDialog("Login Status",r,"login_f");
        }
    }

    public void showDialog(String t, String r, String c) {
        alertDialog.setTitle(t);
        if(c.equals("reg_t"))
        {
            alertDialog.setMessage(r);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                    activity.finish();
                }
            });
        }
        else if(c.equals("reg_u") || c.equals("reg_f"))
        {
            alertDialog.setMessage(r);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    EditText uname, pwd,email,mno;
                    uname = (EditText)activity.findViewById(R.id.et_un);
                    pwd = (EditText)activity.findViewById(R.id.et_pwd);
                    email = (EditText)activity.findViewById(R.id.et_email);
                    mno = (EditText)activity.findViewById(R.id.et_mn);
                    mno.setText("");
                    pwd.setText("");
                    email.setText("");
                    uname.setText("");
                    dialog.dismiss();
                }
            });
        }
        else if(c.equals("login_f"))
        {
            alertDialog.setMessage(r);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    EditText uname, pwd;
                    uname = (EditText)activity.findViewById(R.id.et_un);
                    pwd = (EditText)activity.findViewById(R.id.et_pwd);
                    pwd.setText("");
                    uname.setText("");
                    dialog.dismiss();
                }
            });
        }
        AlertDialog ad = alertDialog.create();
        ad.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
