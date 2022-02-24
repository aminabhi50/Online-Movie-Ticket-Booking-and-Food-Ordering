package com.example.precisa.goldcinema;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class SeatSelectionActivity extends AppCompatActivity implements Serializable{

    GridView gridView;
    ArrayList<Seat> gridArray = new ArrayList<Seat>();
    ArrayList<String> select = new ArrayList<String>();
    seatAdapter sadp;
    TextView total,price;
    int c=0,tp=0;
    public Bitmap seatIcon;
    public Bitmap seatSelect;
    Button order;
    String t,rs,un,mn,date,sno,stime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        seatIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.seat_layout_screen_nor_avl);
        seatSelect = BitmapFactory.decodeResource(this.getResources(), R.drawable.seat_layout_screen_nor_std);
        totalSeat(50);

        total = (TextView)findViewById(R.id.seatsSelected);
        total.setText(0+"");

        price = (TextView)findViewById(R.id.tprice);
        price.setText("Rs. "+0+"");
        gridView = (GridView) findViewById(R.id.gridView1);
        sadp = new seatAdapter(this, R.layout.seatrow_layout, gridArray);
        gridView.setAdapter(sadp);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Seat seat = gridArray.get(i);
                Bitmap seatcompare = seat.getImage();
                if (seatcompare == seatIcon) {
                    if(c<15) {
                        seatSelected(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"You can select maximum 15 seats.",Toast.LENGTH_LONG).show();
                    }
                } else {
                    seatDeselcted(i);
                }
            }
        });
        un = getIntent().getStringExtra("un");
        mn = getIntent().getStringExtra("mn");
        date = getIntent().getStringExtra("date");
        sno = getIntent().getStringExtra("sno");
        stime = getIntent().getStringExtra("stime");
        order = (Button)findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeatSelectionActivity.this,FoodDetailsActivity.class);
                /*Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)gridArray);
                intent.putExtra("seats",args);*/
                intent.putExtra("un",un);
                intent.putExtra("mn",mn);
                intent.putExtra("date",date);
                intent.putExtra("sno",sno);
                intent.putExtra("stime",stime);
                intent.putStringArrayListExtra("select",select);
                intent.putExtra("totalseat",total.getText().toString());
                intent.putExtra("price",price.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }

    public void totalSeat(int n)
    {
        for (int i = 1; i <= n; ++i)
        {
            gridArray.add(new Seat(seatIcon, "seat " + i));
        }
    }

    public void seatSelected(int pos)
    {
        String s = gridArray.get(pos).getTitle();
        String r = "seat ";
        s = s.replace(r,"");
        select.add(s);
        gridArray.remove(pos);
        c=Integer.parseInt(total.getText().toString())+1;
        total.setText(c+"");
        pricep(s);
        gridArray.add(pos, new Seat(seatSelect, "select"));
        sadp.notifyDataSetChanged();

    }

    public void seatDeselcted(int pos)
    {
        int p=0;
        if(c>0) {
            gridArray.remove(pos);
            int i = pos + 1;
            c=Integer.parseInt(total.getText().toString())-1;
            total.setText(c+"");
            gridArray.add(pos, new Seat(seatIcon, "seat " + i));
            sadp.notifyDataSetChanged();
            String s = gridArray.get(pos).getTitle();
            String r = "seat ";
            s = s.replace(r,"");
            p = select.indexOf(s);
            select.remove(p);
            pricem(s);
            //Toast.makeText(getApplicationContext(),p+"",Toast.LENGTH_LONG).show();
        }
    }

    public void remove()
    {
        t = price.getText().toString();
        rs = "Rs. ";
        t = t.replace(rs,"");
    }
    public void pricep(String s)
    {
        if(Integer.parseInt(s)>=1 && Integer.parseInt(s)<=20)
        {
            remove();
            tp = Integer.parseInt(t) + 80;
            price.setText("Rs. "+tp+"");
        }
        if(Integer.parseInt(s)>=21 && Integer.parseInt(s)<=40)
        {
            remove();
            tp = Integer.parseInt(t) + 100;
            price.setText("Rs. "+tp+"");
        }
        if (Integer.parseInt(s)>=41 && Integer.parseInt(s)<=50)
        {
            remove();
            tp = Integer.parseInt(t) + 150;
            price.setText("Rs. "+tp+"");
        }
    }

    public void pricem(String s)
    {
        if(Integer.parseInt(t) > 0) {
            if (Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 20) {
                remove();
                tp = Integer.parseInt(t) - 80;
                price.setText("Rs. " + tp + "");
            }
            if (Integer.parseInt(s) >= 21 && Integer.parseInt(s) <= 40) {
                remove();
                tp = Integer.parseInt(t) - 100;
                price.setText("Rs. " + tp + "");
            }
            if (Integer.parseInt(s) >= 41 && Integer.parseInt(s) <= 50) {
                remove();
                tp = Integer.parseInt(t) - 150;
                price.setText("Rs. " + tp + "");
            }
        }
    }
}