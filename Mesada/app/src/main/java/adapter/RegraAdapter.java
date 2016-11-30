package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.matheus.mesada.R;

import java.util.List;

import model.Regra;

/**
 * Created by matheus on 23/10/16.
 */

public class RegraAdapter extends BaseAdapter {

    private Context mContext;
    private List<Regra> mListaRegra;


    public RegraAdapter(Context context, List<Regra> regraList) {
        this.mListaRegra = regraList;
        this.mContext = context;


    }

    @Override
    public int getCount() {
        return mListaRegra.size();
    }

    @Override
    public Object getItem(int position) {
        return mListaRegra.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Regra regra = mListaRegra.get(position);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_regra, null);
        }

        TextView textViewDescricao = (TextView) view.findViewById(R.id.regra_lista_descricao);
        textViewDescricao.setText(regra.getDescricao());


        TextView textViewValor = (TextView) view.findViewById(R.id.regra_lista_valor);
        textViewValor.setText(regra.getValor());

        return view;
    }
}
