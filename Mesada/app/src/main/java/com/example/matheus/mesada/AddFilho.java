package com.example.matheus.mesada;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
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
    private ImageView btnImage;
    private Uri uri;
    private Button buttonSalvar;
    private Bitmap bitmapfinal;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfilho);


        filhoDAO = new FilhoDAO(this);
        inputLayoutNome = (TextInputLayout) findViewById(R.id.inputLayoutNome);
        inputLayoutSaldo = (TextInputLayout) findViewById(R.id.inputLayoutSaldo);
        editNome = (EditText) findViewById(R.id.edtNome);
        editSaldo = (EditText) findViewById(R.id.edtSaldo);
        buttonSalvar = (Button )findViewById(R.id.buttonSalvarFilho);

        editNome.addTextChangedListener(new MyTextWatcher(editNome));
        editSaldo.addTextChangedListener(new MyTextWatcher(editSaldo));
        btnImage = (ImageView) findViewById(R.id.profile_image);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();


            }
        });
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final CharSequence[] items = {
                        "Galeria",
                        "Camera"
                };

                AlertDialog.Builder alert = new AlertDialog.Builder(AddFilho.this);
                alert.setTitle("Selecionar uma Foto");
                alert.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                carregarImagemInterna(view);
                                break;

                            case 1:
                                capturaImagem(view);


                                break;
                        }
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }
        });


    }

    public void carregarImagemInterna(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGEM_INTERNA);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURAR_IMAGEM) {
            if (resultCode == RESULT_OK) {


            } else {
                Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == IMAGEM_INTERNA) {
            if (resultCode == RESULT_OK) {
                Bitmap bitmap = null;
                try {
                    Uri path_imagSD = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path_imagSD);
                    //int size= Math.min(bitmap.getWidth(), bitmap.getHeight());
                    //int x = (bitmap.getWidth() - 2);
                    //int y = (bitmap.getHeight() - 2);
                    //Bitmap resss= Bitmap.createBitmap(bitmap,x,y,size,size);

                    int width = bitmap.getWidth();

                    int height = bitmap.getHeight();

                    int newWidth = 175;
                    int newHeight = 175;
                    float scaleWidth = ((float) newWidth) / width;
                    float scaleHeight = ((float) newHeight) / height;
                    Matrix matrix = new Matrix();
                    matrix.postScale(scaleWidth, scaleHeight);
                    // rotate the Bitmap
                    //matrix.postRotate(90);
                    // recreate the new Bitmap
                    bitmapfinal = Bitmap.createBitmap(bitmap, 0, 0,
                            width, height, matrix, true);


                    btnImage.setImageBitmap(bitmapfinal);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_LONG).show();
            }


        }
    }

    public void capturaImagem(View view) {


    }

    public void saveBitmapSD(Bitmap bitmap) {

        try {

            File file = new File(Environment.getExternalStorageDirectory() + "/imgsApp");
            file.mkdir();

            File ifile = new File(Environment.getExternalStorageDirectory() + "/imgsApp/", nome + ".png");
            FileOutputStream outStream = new FileOutputStream(ifile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.close();

        } catch (Exception e) {
            Log.e("Could not save", e.toString());
        }
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

        Mensagem.Msg(this, getString(R.string.cadastrado));

        saveBitmapSD(bitmapfinal);
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
