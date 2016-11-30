package com.example.matheus.cunsumoeletrico;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import dao.AparelhoDAO;
import model.Aparelho;

public class ListAparelhosGeraisActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<Aparelho> aparelhosList;
    private ListView listView;
    private ListAparelhoAdapter adapter;
    private AparelhoDAO aparelhoDAO;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_aparelhos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.listAparelhos);
        context = getApplicationContext();

        aparelhoDAO = new AparelhoDAO(this);
        aparelhosList = aparelhoDAO.getAllAparelhos();
        adapter = new ListAparelhoAdapter(this,aparelhosList);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
