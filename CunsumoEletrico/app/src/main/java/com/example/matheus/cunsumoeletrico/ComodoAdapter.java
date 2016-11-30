package com.example.matheus.cunsumoeletrico;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Comodo;

/**
 * Created by matheus on 18/11/16.
 */
public class ComodoAdapter extends ArrayAdapter<Comodo> {
    private List<Comodo> mListaComodo;
    private Context mContext;


    public ComodoAdapter(Context context, int resource, List<Comodo> listComodos) {
        super(context, resource);
        this.mContext = context;
        this.mListaComodo = listComodos;
    }

    @Override
    public int getCount() {
        return mListaComodo.size();
    }

    @Override
    public Comodo getItem(int position) {
        return mListaComodo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(18);
        textView.setText(""+ mListaComodo.get(position).getNome());
        textView.setHeight(70);
        textView.setGravity(Gravity.LEFT| Gravity.CENTER );

        return textView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(18);
        textView.setText(""+ mListaComodo.get(position).getNome());
        textView.setHeight(70);
        textView.setGravity(Gravity.LEFT | Gravity.CENTER);

        return textView;
    }
}
