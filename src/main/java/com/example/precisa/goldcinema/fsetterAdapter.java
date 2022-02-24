package com.example.precisa.goldcinema;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Precisa on 24-May-18.
 */

public class fsetterAdapter extends BaseAdapter {

    public ArrayList<fsetter> list;
    public Context context;
    public fsetterAdapter(Context context,ArrayList<fsetter> list)
    {
        this.context = context;
        this.list = list;
    }

    public void add(fsetter object) {
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        final fsetterAdapter.SetHolder setHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.frow_layout,parent,false);
            setHolder = new fsetterAdapter.SetHolder();
            setHolder.tvfn = (TextView)row.findViewById(R.id.fname);
            setHolder.tvp = (TextView)row.findViewById(R.id.fprice);
            setHolder.iv = (ImageView)row.findViewById(R.id.fimg);
            setHolder.qn = (EditText)row.findViewById(R.id.quantity);
            setHolder.plus = (Button)row.findViewById(R.id.btnplus);
            setHolder.minus = (Button)row.findViewById(R.id.btnminus);
            row.setTag(setHolder);
        }
        else
        {
            row = convertView;
            setHolder = (fsetterAdapter.SetHolder)row.getTag();
        }
        final fsetter st = (fsetter)this.getItem(position);
        setHolder.tvfn.setText(st.getFname());
        setHolder.tvp.setText("Price: Rs."+st.getPrice()+".00");

        String url = "http://kidnapped-drifts.000webhostapp.com";
        String img = st.getImage();
        StringBuffer i = new StringBuffer(img);
        i.deleteCharAt(0);
        img = i.toString();
        url = url + img;
        Picasso.get().load(url).into(setHolder.iv);

        setHolder.qn.setText(st.fquantity+"");

        setHolder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuantity(position,setHolder.qn,1);
            }
        });

        setHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuantity(position,setHolder.qn,-1);
            }
        });

        return row;
    }

    private void updateQuantity(int position, EditText edTextQuantity, int value) {

        fsetter st = (fsetter) getItem(position);
        if(value > 0)
        {
            st.fquantity = st.fquantity + 1;
        }
        else
        {
            if(st.fquantity > 0)
            {
                st.fquantity = st.fquantity - 1;
            }
        }
        edTextQuantity.setText(st.fquantity+"");
    }
    static class SetHolder
    {
        TextView tvfn,tvp;
        EditText qn;
        Button plus,minus;
        ImageView iv;
    }
}