package com.example.matheus.cunsumoeletrico;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import dao.AparelhoDAO;
import dao.ComodoDAO;
import model.Aparelho;
import model.Comodo;

public class AdicionarAparelhoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText editNome, editPotencia;
    private Spinner spinerComodo;

    private Button buttonSalvar;
    private Aparelho aparelho;
    private AparelhoDAO aparelhoDAO;
    private Integer id_comodo;
    private ComodoDAO comodoDAO;
    private TextInputLayout inputLayoutNome, inputLayoutPotencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_aparelho);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvarAparelho);
        inputLayoutNome = (TextInputLayout) findViewById(R.id.inputLayoutNomeAparelho);
        inputLayoutPotencia = (TextInputLayout) findViewById(R.id.inputLayoutPotenciaAparelho);
        editNome = (EditText) findViewById(R.id.editNomeaparelho);
        editPotencia = (EditText) findViewById(R.id.editPotencia);
        aparelhoDAO = new AparelhoDAO(this);
        spinerComodo = (Spinner)findViewById(R.id.comodo_spinner2);
        ComodoAdapter comodoAdapter;
        comodoDAO = new ComodoDAO(this);
        editNome.addTextChangedListener(new MyTextWatcher(editNome));
        editPotencia.addTextChangedListener(new MyTextWatcher(editPotencia));


        spinerComodo.setOnItemSelectedListener(this);

        List<Comodo> comodoList = comodoDAO.listaComodos();
        comodoAdapter = new ComodoAdapter(this, android.R.layout.simple_spinner_item, comodoList);
        comodoDAO = new ComodoDAO(getApplicationContext());
        spinerComodo.setAdapter(comodoAdapter);
        comodoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cadastrar();
            }
        });


    }

    private boolean verificaCampoNome() {
        if (editNome.getText().toString().trim().isEmpty()) {
            inputLayoutNome.setError(getString(R.string.campo_vazio));
            requestFocus(editNome);
        } else {
            inputLayoutNome.setErrorEnabled(false);
        }
        return true;
    }

    private boolean verificaCampoPotencia() {
        if (editPotencia.getText().toString().trim().isEmpty()) {
            inputLayoutPotencia.setError(getString(R.string.campo_vazio));
            requestFocus(editPotencia);

        } else {
            inputLayoutPotencia.setErrorEnabled(false);

        }
        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        }
    }

    private void cadastrar() {
        if (!verificaCampoNome()) {
            return;
        }
        if (!verificaCampoPotencia()) {
            return;
        }

        String nome = editNome.getText().toString();
        String potencia = editPotencia.getText().toString();

        aparelho = new Aparelho();
        aparelho.setNome(nome);
        aparelho.setPotencia(Double.parseDouble(potencia));
        aparelho.setId_comodo(id_comodo);
        aparelhoDAO.salva(aparelho);
        finish();

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView == findViewById(R.id.comodo_spinner2)) {
            Comodo selecionado = (Comodo) adapterView.getItemAtPosition(i);
            id_comodo = selecionado.getId();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private class MyTextWatcher implements TextWatcher {
        private View view;


        public MyTextWatcher(View view) {
            this.view = view;

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.editNomeaparelho:
                    verificaCampoNome();
                    break;

                case R.id.editPotencia:
                    verificaCampoPotencia();
                    break;
            }
        }
    }
}
