package com.example.matheus.mesada;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import dao.FilhoDAO;
import model.Filho;
import utils.Mensagem;

public class AddFilho extends AppCompatActivity {
    public static final int IMAGEM_INTERNA = 1;
    private static final int CAPTURAR_IMAGEM = 1;
    private android.app.AlertDialog alertDialogOpcaoImagem;
    private EditText editNome, editSaldo;
    private TextInputLayout inputLayoutNome, inputLayoutSaldo;
    private FilhoDAO filhoDAO;
    private Filho filho;
    private int idfilho;
    private Activity activity;


    private Button buttonSalvar;
    private Bitmap bitmap;
    private String nome;
    private ImageView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfilho);


        filhoDAO = new FilhoDAO(this);
        inputLayoutNome = (TextInputLayout) findViewById(R.id.inputLayoutNome);
        inputLayoutSaldo = (TextInputLayout) findViewById(R.id.inputLayoutSaldo);
        editNome = (EditText) findViewById(R.id.edtNome);
        editSaldo = (EditText) findViewById(R.id.edtSaldo);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvarFilho);

        editNome.addTextChangedListener(new MyTextWatcher(editNome));
        editSaldo.addTextChangedListener(new MyTextWatcher(editSaldo));

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();


            }
        });
        resultView = (ImageView) findViewById(R.id.result_image);
        resultView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarImagem();
            }
        });


    }

    private void buscarImagem() {
        Crop.pickImage(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {//result/data
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop(result.getData());





        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        }
    }
    private void salvarImagemSD(Bitmap bitmap){
        try {
             File file = new File (Environment.getExternalStorageDirectory()+ "/imgsMesada");
            file.mkdir();

            File iFile = new File (Environment.getExternalStorageDirectory()+ "/imgsMesada",nome+".JPEG");
            FileOutputStream outputStream = new FileOutputStream(iFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();

        }catch (Exception e){
            Log.e("Cloud not save", e.toString());
        }

    }


    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {

            try {
               // Uri uri = Crop.getOutput(result);
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),Crop.getOutput(result));

                //circle
                Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
                BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                Paint paint = new Paint();
                paint.setShader(shader);

                Canvas c = new Canvas(circleBitmap);
                c.drawCircle(bitmap.getWidth()/2,bitmap.getHeight()/2,bitmap.getWidth()/2, paint);
                resultView.setImageBitmap(circleBitmap);
            }catch (IOException e){
                e.printStackTrace();
            }


        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


    private void beginCrop(Uri source) { //source/data
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);






    }
    @Override
    protected void onDestroy() {
        filhoDAO.fechar();
        super.onDestroy();
    }


    private boolean verificaCampoNome() {
        if (editNome.getText().toString().trim().isEmpty()) {
            inputLayoutNome.setError(getString(R.string.campo_obrigatorio));
            requestFocus(editNome);
            return false;

        } else {
            inputLayoutNome.setErrorEnabled(false);
        }
        return true;
    }

    private boolean verificaCampoSaldo() {
        if (editSaldo.getText().toString().trim().isEmpty()) {
            inputLayoutSaldo.setError(getString(R.string.campo_obrigatorio));
            requestFocus(editSaldo);
            return false;
        } else {
            inputLayoutSaldo.setErrorEnabled(false);
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
        if (!verificaCampoSaldo()) {
            return;
        }
        nome = editNome.getText().toString();
        String saldo = editSaldo.getText().toString();

        filho = new Filho();
        filho.setNome(nome);
        filho.setSaldo(saldo);
        filhoDAO.salvar(filho);
        salvarImagemSD(bitmap);


        Mensagem.Msg(this, getString(R.string.cadastrado));


        finish();


    }

    //abilita edição do EditText
    private class MyTextWatcher implements TextWatcher {
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
                case R.id.edtNome:
                    verificaCampoNome();
                    break;
                case R.id.edtSaldo:
                    verificaCampoSaldo();
                    break;
            }

        }
    }


}
