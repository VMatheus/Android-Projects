package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matheus.mesada.R;

import java.util.List;

import dao.FilhoDAO;
import model.Filho;

/**
 * Created by matheus on 12/12/16.
 */

public class NewAdapterFilho extends RecyclerView.Adapter<NewAdapterFilho.ViewHolder> {
    private Context mContext;
    private FilhoDAO mFilhoDAO;
    private List<Filho> mFilhoList;


    public NewAdapterFilho(Context context, List<Filho> filhoList, FilhoDAO filhoDAO) {
        this.mContext = context;
        this.mFilhoDAO = filhoDAO;
        this.mFilhoList = filhoList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_adapter_filho, null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewAdapterFilho.ViewHolder holder, int position) {
        Filho filho = mFilhoList.get(position);
        holder.textViewNome.setText(filho.getNome());
        holder.textViewSaldo.setText(filho.getSaldo());
    }

    @Override
    public int getItemCount() {
        return mFilhoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNome;
        private TextView textViewSaldo;
        private Toolbar toolbarOpções;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNome = (TextView) itemView.findViewById(R.id.nome_adapter_filho);
            textViewSaldo = (TextView) itemView.findViewById(R.id.saldo_adapter_filho);
            toolbarOpções = (Toolbar) itemView.findViewById(R.id.toolbar_adapter_filho);

        }
    }


}
