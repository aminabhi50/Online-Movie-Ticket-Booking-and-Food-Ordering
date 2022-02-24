package com.example.precisa.goldcinema;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Precisa on 01-Apr-18.
 */

public class setterAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public setterAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(setter object) {
        super.add(object);
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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        SetHolder setHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            setHolder = new SetHolder();
            setHolder.tvmn = (TextView)row.findViewById(R.id.mname);
            setHolder.tvl = (TextView)row.findViewById(R.id.lang);
            setHolder.tvt = (TextView)row.findViewById(R.id.type);
            setHolder.iv = (ImageView)row.findViewById(R.id.mimg);
            row.setTag(setHolder);
        }
        else
        {
            setHolder = (SetHolder)row.getTag();
        }
        setter st = (setter)this.getItem(position);
        String url = "http://kidnapped-drifts.000webhostapp.com";
        String img = st.getImage();
        StringBuffer i = new StringBuffer(img);
        i.deleteCharAt(0);
        img = i.toString();
        url = url + img;
        Picasso.get().load(url).into(setHolder.iv);

        setHolder.tvmn.setText(st.getMname());

        setHolder.tvl.setText(st.getLang());

        setHolder.tvt.setText(st.getType());

        return row;
    }
    static class SetHolder
    {
        TextView tvmn,tvl,tvt;
        ImageView iv;
    }
}