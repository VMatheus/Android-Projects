package com.example.matheus.cunsumoeletrico;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Aparelho;

/**
 * Created by matheus on 19/11/16.
 */
public class AparelhoAdapter extends ArrayAdapter<Aparelho> {
    private List<Aparelho> mListaAparelho;
    private Context mContext;

    public AparelhoAdapter(Context context, int resource, List<Aparelho> listAparelhos) {
        super(context, resource);
        this.mContext = context;
        this.mListaAparelho = listAparelhos;

    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Aparelho getItem(int position) {
        return mListaAparelho.get(position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(18);
        textView.setText(""+ mListaAparelho.get(position).getNome());
        textView.setHeight(70);
        textView.setGravity(Gravity.LEFT| Gravity.CENTER );

        return textView;
    }



    @Override
    public int getCount() {
        return mListaAparelho.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(18);
        textView.setText(""+ mListaAparelho.get(position).getNome());
        textView.setHeight(70);
        textView.setGravity(Gravity.LEFT | Gravity.CENTER);

        return textView;
    }
}
