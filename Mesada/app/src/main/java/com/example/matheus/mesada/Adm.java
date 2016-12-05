package com.example.matheus.mesada;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import adapter.AdministrarAdapter;
import dao.FilhoDAO;
import dao.RegraDAO;
import model.Filho;
import model.Regra;

public class Adm extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Regra> regraList;
    private RegraDAO regraDAO;
    private FilhoDAO filhoDAO;
    private Integer idfilho;
    private AdministrarAdapter mAdapter;
    private Context context;
    private String nome, saldo;


    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        filhoDAO = new FilhoDAO(this);

        idfilho = getIntent().getIntExtra("FILHO_ID", 0);
        if (idfilho > 0) {
            Filho model = filhoDAO.buscarPorId(idfilho);
            nome = model.getNome();
            saldo = model.getSaldo();

        }


        regraDAO = new RegraDAO(this);
        regraList = regraDAO.listaRegras();

        mRecyclerView = (RecyclerView) findViewById(R.id.adm_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        context = getApplicationContext();


        mAdapter = new AdministrarAdapter(context, regraList, idfilho, nome, saldo, filhoDAO);

        mRecyclerView.setAdapter(mAdapter);


    }


}
