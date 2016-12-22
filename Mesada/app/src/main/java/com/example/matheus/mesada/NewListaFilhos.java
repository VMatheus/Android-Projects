package com.example.matheus.mesada;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import adapter.NewAdapterFilho;
import dao.FilhoDAO;
import model.Filho;

public class NewListaFilhos extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Filho> filhoList;
    private FilhoDAO filhoDAO;
    private NewAdapterFilho mAdapter;
    private Context context;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_list_filhos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        filhoDAO = new FilhoDAO(this);
        filhoList = filhoDAO.listaFilhos();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_lista_filhos);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        context= getApplicationContext();
        mAdapter = new NewAdapterFilho(context, filhoList, filhoDAO);
        mRecyclerView.setAdapter(mAdapter);




    }

}
