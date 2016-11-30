package com.example.matheus.cunsumoeletrico;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dao.AparelhoDAO;
import dao.ComodoDAO;
import model.Aparelho;
import model.Comodo;

public class PrincipalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner comodo_spinner, aparelho_spinner;
    private ArrayAdapter adapter;


    private ComodoDAO comodoDAO;
    private AparelhoDAO aparelhoDAO;
    private AparelhoAdapter aparelhoAdapter;
    private ComodoAdapter comodoAdapter;
    private Context context;
    Integer id_comodo, id_aparelho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        comodo_spinner = (Spinner) findViewById(R.id.comodo_spinner);
        aparelho_spinner = (Spinner) findViewById(R.id.aparelho_spinner);
        TextView textView = (TextView) findViewById(R.id.textteste);

        aparelhoDAO = new AparelhoDAO(this);
        comodoDAO = new ComodoDAO(this);
        comodo_spinner.setOnItemSelectedListener(this);
        aparelho_spinner.setOnItemSelectedListener(this);


        loadComodo();


    }


    private void loadComodo() {

        List<Comodo> comodoList = comodoDAO.listaComodos();
        comodoAdapter = new ComodoAdapter(this, android.R.layout.simple_spinner_item, comodoList);
        comodoDAO = new ComodoDAO(getApplicationContext());
        comodo_spinner.setAdapter(comodoAdapter);
        comodoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    private void loadAparelhos(int idComdo) {
        List<Aparelho> aparelhos = aparelhoDAO.getAparelhosComId(idComdo);
        aparelhoAdapter = new AparelhoAdapter(this, android.R.layout.simple_spinner_item, aparelhos);
        aparelho_spinner.setAdapter(aparelhoAdapter);
        Aparelho aparelho = new Aparelho();
        aparelho.getId_comodo();

        aparelhoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Integer id = item.getItemId();
        if (id == R.id.add_comodo) {
            Intent it1 = new Intent(this, AdicionarComodoActivity.class);
            startActivity(it1);


        }
        if (id ==R.id.add_aparelho){
            Intent it2 = new Intent(new Intent(this, AdicionarAparelhoActivity.class));
            startActivity(it2);
        }
        if (id == R.id.visualizar_lista_de_aparelhos){
            Intent it3 = new Intent(new Intent(this, ListAparelhosGeraisActivity.class));
            startActivity(it3);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
        if (adapterView == findViewById(R.id.comodo_spinner)) {
            Comodo selecionado = (Comodo) adapterView.getItemAtPosition(posicao);
            id_comodo = selecionado.getId();
            Log.v("Id:", "" + id_comodo);
            TextView textView = (TextView) findViewById(R.id.textteste);
            textView.setText(id_comodo + "");
            loadAparelhos(id_comodo);


        }
        if (adapterView == findViewById(R.id.aparelho_spinner)) {
            Aparelho selecionado = (Aparelho) adapterView.getItemAtPosition(posicao);
            id_aparelho = selecionado.get_id();



        }


    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}



