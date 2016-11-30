package com.example.matheus.cunsumoeletrico;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import model.Aparelho;

/**
 * Created by matheus on 23/11/16.
 */
public class ListAparelhoAdapter extends BaseAdapter{
    private Context context ;
    private List<Aparelho>mAparelhoList;

    public ListAparelhoAdapter(Context context1, List<Aparelho> aparelhosList) {
    this.mAparelhoList = aparelhosList;
        this.context= context1;

    }


    @Override
    public int getCount() {
        return mAparelhoList.size();
    }

    @Override
    public Object getItem(int i) {
        return mAparelhoList.get(i);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Aparelho aparelho = mAparelhoList.get(position);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_aparelhos, null);

        }

        // preencher lista


        TextView nome = (TextView)view.findViewById(R.id.nome_aparelho);
        TextView potencia = (TextView)view.findViewById(R.id.potencia_aparelho);
        TextView id_comodo = (TextView)view.findViewById(R.id.id_comodo);
        nome.setText(aparelho.getNome());
        potencia.setText(aparelho.getPotencia().toString());
        id_comodo.setText(aparelho.getId_comodo().toString());
        return view;

    }
}
