package com.example.matheus.mesada;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import dao.RegraDAO;
import model.Regra;
import utils.Mensagem;

public class AddRegra extends AppCompatActivity {
    private EditText editDescricao, editValor;
    private RegraDAO regraDAO;
    private Regra regra;
    private int idregra;
    private Button buttonSalvarRegra;
    private TextInputLayout inputLayoutDescricao, inputLayoutValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addregra);


        regraDAO = new RegraDAO(this);
        editDescricao = (EditText) findViewById(R.id.edtDescricao);
        editValor = (EditText) findViewById(R.id.edtValor);
        inputLayoutDescricao = (TextInputLayout) findViewById(R.id.inputLayoutDescricao);
        inputLayoutValor = (TextInputLayout) findViewById(R.id.inputLayoutValor);
        buttonSalvarRegra = (Button) findViewById(R.id.buttonSalvarRegra);


        editDescricao.addTextChangedListener(new MyTextWatcher(editDescricao));
        editValor.addTextChangedListener(new MyTextWatcher(editValor));

        //Carregar Regra Para ser Alterada
        idregra = getIntent().getIntExtra("ID_REGRA", 0);
        if (idregra > 0) {
            Regra model = regraDAO.buscaPorId(idregra);
            editDescricao.setText(model.getDescricao());
            ;
            editValor.setText(model.getValor());
            setTitle(getString(R.string.atualizar));
        }


        buttonSalvarRegra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();

            }
        });


    }


    @Override
    protected void onDestroy() {
        regraDAO.fechar();
        super.onDestroy();

    }

    private boolean verificaCampoDescricao() {
        if (editDescricao.getText().toString().trim().isEmpty()) {
            inputLayoutDescricao.setError(getString(R.string.campo_obrigatorio));
            requesFocus(editDescricao);
            return false;

        } else {
            inputLayoutDescricao.setErrorEnabled(false);
        }


        return true;

    }

    private boolean verificaCampoValor() {
        if (editValor.getText().toString().trim().isEmpty()) {
            inputLayoutValor.setError(getString(R.string.campo_obrigatorio));
            requesFocus(editValor);
            return false;
        } else {
            inputLayoutValor.setErrorEnabled(false);
        }
        return true;
    }

    private void requesFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }


    }

    private void cadastrar() {
        if (!verificaCampoDescricao()) {
            return;
        }
        if (!verificaCampoValor()) {
            return;
        }

        String descricao = editDescricao.getText().toString();
        String valor = editValor.getText().toString();


        regra = new Regra();
        regra.setDescricao(descricao);
        regra.setValor(valor);


        if (idregra > 0) {
            regra.set_id(idregra);
        }
        long res = regraDAO.salvar(regra);
        if (res != -1) {
            if (idregra > 0) {
                Mensagem.Msg(this, getString(R.string.atualizado));
            } else {
                Mensagem.Msg(this, getString(R.string.cadastrado));

            }
            finish();
            startActivity(new Intent(this, ListaRegras.class));

        }else{
            Mensagem.Msg(this, getString(R.string.erro));
        }
    }

    public class MyTextWatcher implements TextWatcher {
        private View view;


        private MyTextWatcher(View view) {
            this.view = view;


        }


        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }


        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }


        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.edtDescricao:
                    verificaCampoDescricao();
                    break;
                case R.id.edtValor:
                    verificaCampoValor();
                    break;
            }


        }
    }

}
