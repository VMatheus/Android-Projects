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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dao.ComodoDAO;
import model.Comodo;

public class AdicionarComodoActivity extends AppCompatActivity {
    private EditText editNomeComodo;
    private TextInputLayout inputLayoutNomeComodo;
    private ComodoDAO comodoDAO;
    private Comodo comodo;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_comodo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buttonSalvar = (Button)findViewById(R.id.buttonSalvar);
        comodoDAO = new ComodoDAO(this);
        inputLayoutNomeComodo = (TextInputLayout) findViewById(R.id.inputLayoutNomeComodo);
        editNomeComodo = (EditText) findViewById(R.id.editNomeComodo);

        editNomeComodo.addTextChangedListener(new MyTextWatcher(editNomeComodo));
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });


    }

    private boolean verificaCampos() {
        if (editNomeComodo.getText().toString().trim().isEmpty()) {

            inputLayoutNomeComodo.setError(getString(R.string.campo_vazio));
            requestFocus(editNomeComodo);
        } else {
            inputLayoutNomeComodo.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        }
    }


    //salva no banco

    private void cadastrar() {
        if (!verificaCampos()) {
            return;
        }

        String nome = editNomeComodo.getText().toString();

        comodo = new Comodo();
        comodo.setNome(nome);
        comodoDAO.salvarComodo(comodo);

        finish();

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
                case R.id.editNomeComodo:
                    verificaCampos();
                    break;
            }

        }
    }


}
