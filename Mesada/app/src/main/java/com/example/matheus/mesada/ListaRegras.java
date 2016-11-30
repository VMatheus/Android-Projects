package com.example.matheus.mesada;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import adapter.RegraAdapter;
import dao.RegraDAO;
import model.Regra;
import utils.Mensagem;

public class  ListaRegras extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener {
    List<Regra> regraList;
    private ListView lista;
    private RegraAdapter regraAdapter;
    private RegraDAO regraDAO;
    private android.app.AlertDialog alertDialog;
    private android.app.AlertDialog alertconfirmacao;
    private int idposition;
    //QBF8J-9RVTC-XVLBL


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_regras);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        alertDialog = Mensagem.criarAlertDialogRegras(this);
        alertconfirmacao = Mensagem.criarDialogConfirmacao(this);


        regraDAO = new RegraDAO(this);
        regraList = regraDAO.listaRegras();
        regraAdapter = new RegraAdapter(this, regraList);
        lista = (ListView) findViewById(R.id.list_regra);
        lista.setAdapter(regraAdapter);
        lista.setOnItemClickListener(this);

    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Integer id = regraList.get(idposition).get_id();
        switch (i) {
            case 0:
                Intent it = new Intent(this, AddRegra.class);
                it.putExtra("ID_REGRA", id);
                startActivity(it);


                break;
            case 1:
                alertconfirmacao.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                regraList.remove(idposition);
                regraDAO.removeRegra(id);
                lista.invalidateViews();
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                alertDialog.dismiss();
                break;


        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        idposition = i;
        alertDialog.show();

    }

}
