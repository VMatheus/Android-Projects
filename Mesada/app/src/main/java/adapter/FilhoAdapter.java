package adapter;

/**
 * Created by matheus on 03/11/16.
 */

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matheus.mesada.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import model.Filho;

/**
 * Created by matheus on 21/10/16.
 */
public class FilhoAdapter extends BaseAdapter {
    private Context mContext;
    private List<Filho> mListaFilho;
    public FilhoAdapter(Context context, List<Filho> filhoList) {
        this.mContext = context;
        this.mListaFilho = filhoList;
    }



    @Override
    public int getCount() {
        return mListaFilho.size();
    }

    @Override
    public Object getItem(int position) {
        return mListaFilho.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup ) {
        Filho filho = mListaFilho.get(position);
        if (view == null ){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_filho, null);


        }
        //preencher
        ImageView imageView = (ImageView)view.findViewById(R.id.imagem_lista);
        TextView txtNome = (TextView)view.findViewById(R.id.filho_lista_nome);
        txtNome.setText(filho.getNome());

        TextView txtSaldo = (TextView)view.findViewById(R.id.filho_lista_saldo);
        txtSaldo.setText("R$ "+filho.getSaldo());
        File ifile = new File(Environment.getExternalStorageDirectory() + "/imgsApp/", filho.getNome()+".png");

        imageView.setImageURI(Uri.fromFile(ifile));
        //Picasso.with(imageView.getContext()).load(ifile).into(imageView);

        return view;
    }
}
