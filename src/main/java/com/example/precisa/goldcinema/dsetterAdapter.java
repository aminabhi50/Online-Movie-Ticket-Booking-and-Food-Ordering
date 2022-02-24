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
 * Created by Precisa on 24-May-18.
 */

public class dsetterAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public dsetterAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(dsetter object) {
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
        dsetterAdapter.SetHolder setHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.drow_layout,parent,false);
            setHolder = new dsetterAdapter.SetHolder();
            setHolder.tvmn = (TextView)row.findViewById(R.id.mn);
            setHolder.tvc = (TextView)row.findViewById(R.id.cast);
            setHolder.tvd = (TextView)row.findViewById(R.id.director);
            setHolder.tvp = (TextView)row.findViewById(R.id.producer);
            setHolder.tvdesc = (TextView)row.findViewById(R.id.desc);
            setHolder.iv = (ImageView)row.findViewById(R.id.mimg);
            row.setTag(setHolder);
        }
        else
        {
            setHolder = (dsetterAdapter.SetHolder)row.getTag();
        }
        dsetter st = (dsetter)this.getItem(position);
        setHolder.tvmn.setText(st.getMname());
        setHolder.tvc.setText(st.getCast()+"\n");
        setHolder.tvp.setText(st.getP()+"\n");
        setHolder.tvd.setText(st.getD()+"\n");
        setHolder.tvdesc.setText(st.getDesc()+"\n");


        String url = "http://kidnapped-drifts.000webhostapp.com";
        String img = st.getImage();
        StringBuffer i = new StringBuffer(img);
        i.deleteCharAt(0);
        img = i.toString();
        url = url + img;
        Picasso.get().load(url).into(setHolder.iv);
        return row;
    }
    static class SetHolder
    {
        TextView tvmn,tvc,tvp,tvd,tvdesc;
        ImageView iv;
    }
}