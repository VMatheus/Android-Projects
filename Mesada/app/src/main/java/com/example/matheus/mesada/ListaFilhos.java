package com.example.matheus.mesada;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import adapter.FilhoAdapter;
import dao.FilhoDAO;
import model.Filho;
import utils.Mensagem;

public class ListaFilhos extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener {
    private ListView lista;
    private List<Filho> filhoList;
    private FilhoAdapter filhoAdapter;
    private FilhoDAO filhoDAO;
    private android.app.AlertDialog alertDialog;
    private android.app.AlertDialog alertConfirmacao;
    private int idposition;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filhos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        alertDialog = Mensagem.criarAlertDialog(this);
        alertConfirmacao = Mensagem.criarDialogConfirmacao(this);

        filhoDAO = new FilhoDAO(this);
        filhoList = filhoDAO.listaFilhos();
        filhoAdapter = new FilhoAdapter(this, filhoList);


        lista = (ListView) findViewById(R.id.list_filho);
        lista.setAdapter(filhoAdapter);
        lista.setOnItemClickListener(this);


    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        Integer id = filhoList.get(idposition).get_id();
        switch (which) {
            case 0:
                Intent intent = new Intent(this, Adm.class);
                intent.putExtra("FILHO_ID", id);
                startActivity(intent);
                break;
            case 1:
                alertConfirmacao.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                filhoList.remove(idposition);
                filhoDAO.removeFilho(id);
                lista.invalidateViews();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                alertConfirmacao.dismiss();
                break;

            case 2:
                Intent it = new Intent(this, BonusActivity.class);
                it.putExtra("FILHO_ID", id);
                startActivity(it);
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        idposition = position;
        alertDialog.show();

    }

}
