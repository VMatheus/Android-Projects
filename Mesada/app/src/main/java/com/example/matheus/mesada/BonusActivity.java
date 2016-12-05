package com.example.matheus.mesada;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;

import dao.FilhoDAO;
import model.Filho;

public class BonusActivity extends AppCompatActivity {

    private FilhoDAO filhoDAO;
    private Filho filho;

    public RadioGroup radioGroup;
    public double valor;
    private Button btnSalvar;
    private int idRadio;
    private int idfilho;
    String nome, saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        radioGroup = (RadioGroup) findViewById(R.id.radioValores);

        //pegando dados pelo Id, passado por Intent
        filhoDAO = new FilhoDAO(this);
        idfilho = getIntent().getIntExtra("FILHO_ID", 0);
        if (idfilho > 0) {
            Filho model = filhoDAO.buscarPorId(idfilho);
            nome = model.getNome();
            saldo = model.getSaldo();
        }


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturadados();
                finish();

            }
        });


    }

    private void capturadados() {
        idRadio = radioGroup.getCheckedRadioButtonId();
        if (idRadio == R.id.button1) {
            valor = 1.00;

        }
        if (idRadio == R.id.button2) {

            valor = 2.00;
        }
        if (idRadio == R.id.button3) {
            valor = 3.00;

        }
        if (idRadio == R.id.button4) {
            valor = 4.00;

        }
        if (idRadio == R.id.button5) {
            valor = 5.00;
        }
        if (idRadio == R.id.button6) {
            valor = 6.00;
        }
        if (idRadio == R.id.button7) {
            valor = 7.00;

        }
        if (idRadio == R.id.button8) {
            valor = 8.00;

        }
        if (idRadio == R.id.button9) {
            valor = 9.00;

        }
        if (idRadio == R.id.button10) {
            valor = 10.00;
        }

        Double saldoaux = Double.parseDouble(saldo);
        filho= new Filho();
        filho.setNome(nome);
        filho.setSaldo(valor+saldoaux+"" );
        if (idfilho > 0){
            filho.set_id(idfilho);
             long res = filhoDAO.salvar(filho);
        }

        Intent it = new Intent(this, ListaFilhos.class);
        startActivity(it);

    }


}
