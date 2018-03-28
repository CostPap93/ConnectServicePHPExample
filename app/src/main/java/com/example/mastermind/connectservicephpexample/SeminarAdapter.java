package com.example.mastermind.connectservicephpexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mastermind on 26/3/2018.
 */

public class SeminarAdapter extends BaseAdapter {
    Context context;
    ArrayList<Seminar> seminars = new ArrayList<>();
    TextView txtV_seminarname;

    public SeminarAdapter(Context context,ArrayList<Seminar> seminars){
        this.context = context;
        this.seminars=seminars;
    }

    @Override
    public int getCount() {
        return seminars.size();
    }

    @Override
    public Object getItem(int i) {
        return seminars.get(i);
    }

    @Override
    public long getItemId(int i) {
        return seminars.indexOf(getItem(i));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item, null);
        }

        TextView txtV_seminarname =view.findViewById(R.id.txtV_seminarname);;
        Seminar seminar = seminars.get(i);
        txtV_seminarname.setText(seminar.getName());
        return view;
    }
}
