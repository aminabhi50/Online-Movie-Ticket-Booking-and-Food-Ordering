package com.example.precisa.goldcinema;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BillActivity extends AppCompatActivity {

    String un,mn,date,sno,stime,totalseat,tprice,sseats;
    ArrayList<String> select;
    TextView un1,mn1,date1,stime1,sno1,seat1,tprice1,sseats1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        un = getIntent().getStringExtra("un");
        mn = getIntent().getStringExtra("mn");
        date = getIntent().getStringExtra("date");
        sno = getIntent().getStringExtra("sno");
        stime = getIntent().getStringExtra("stime");
        totalseat = getIntent().getStringExtra("totalseat");
        tprice = getIntent().getStringExtra("price");
        select = getIntent().getStringArrayListExtra("select");

        un1 = (TextView)findViewById(R.id.un1);
        mn1 = (TextView)findViewById(R.id.mn1);
        date1 = (TextView)findViewById(R.id.date1);
        stime1 = (TextView)findViewById(R.id.stime1);
        sno1 = (TextView)findViewById(R.id.sno1);
        seat1 = (TextView)findViewById(R.id.seat1);
        tprice1 = (TextView)findViewById(R.id.tprice1);
        sseats1 = (TextView)findViewById(R.id.sseats1);
        sseats = TextUtils.join(", ",select);

        un1.setText(un);
        mn1.setText(mn);
        date1.setText(date);
        sno1.setText(sno);
        seat1.setText(totalseat);
        tprice1.setText(tprice);
        sseats1.setText(sseats);
        stime1.setText(stime);

        Button payment = (Button)findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BillActivity.this,PaymentActivity.class);
                intent.putExtra("un",un);
                intent.putExtra("mn",mn);
                intent.putExtra("date",date);
                intent.putExtra("sno",sno);
                intent.putExtra("stime",stime);
                intent.putExtra("select",sseats);
                intent.putExtra("totalseat",totalseat);
                intent.putExtra("price",tprice);
                startActivity(intent);
            }
        });
        /*LinearLayout lv = new LinearLayout(this);
        TextView tv = new TextView(this);
        tv.setTextSize(15);
        lv.addView(tv);
        for (int i=0; i<st.size();i++){
            tv.append(st.get(i));
            tv.append("\n");
        }
        setContentView(lv);*/
    }
}
