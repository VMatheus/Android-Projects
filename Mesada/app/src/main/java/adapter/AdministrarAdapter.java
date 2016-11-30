package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.matheus.mesada.ListaFilhos;
import com.example.matheus.mesada.R;

import java.util.List;

import dao.FilhoDAO;
import model.Filho;
import model.Regra;

/**
 * Created by matheus on 03/11/16.
 */

public class AdministrarAdapter extends RecyclerView.Adapter<AdministrarAdapter.ViewHolder> {
    private double x, y;
    private Context mcontext;
    private FilhoDAO filhoDAO;
    private Filho filho;
    private Integer mIdFilho;
    private List<Regra> mListaRegra;
    String mnome, msaldo;
    private FilhoDAO mFilhoDAO;





    public AdministrarAdapter(Context context, List<Regra> regraList, Integer idfilho, String nome, String saldo, FilhoDAO filhoDAO) {
        this.mIdFilho = idfilho;
        this.msaldo = saldo;
        this.mnome = nome;
        this.mFilhoDAO = filhoDAO;


        this.mcontext = context;
        this.mListaRegra = regraList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Filho model = filhoDAO.buscarPorId(mIdFilho);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_adm, null);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Regra regra = mListaRegra.get(position);
        holder.textDescricao.setText(regra.getDescricao());
        holder.butnValor.setText("-R$ "+regra.getValor());
        final String mvalor = regra.getValor();


        holder.butnValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Altera(msaldo, mnome, mvalor);
                Intent it = new Intent(mcontext, ListaFilhos.class);
                mcontext.startActivity(it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });

    }

    @Override
    public int getItemCount() {
        return mListaRegra.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textDescricao;
        private Button butnValor;

        public ViewHolder(View itemView) {
            super(itemView);
            textDescricao = (TextView) itemView.findViewById(R.id.item_adm);
            butnValor = (Button) itemView.findViewById(R.id.item_button);


        }
    }


    private void Altera(String msaldo, String mnome, String mvalor) {

        double saldo = Double.parseDouble(msaldo), valor = Double.parseDouble(mvalor);
        double calc = saldo - valor;
        String nome = mnome;
        filho = new Filho();
        filho.setNome(nome);
        filho.setSaldo(calc + "");
        if (mIdFilho > 0) {
            filho.set_id(mIdFilho);
            long res = mFilhoDAO.salvar(filho);

        }
    }
}
