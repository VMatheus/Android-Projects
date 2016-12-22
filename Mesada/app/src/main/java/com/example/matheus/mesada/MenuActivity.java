package com.example.matheus.mesada;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import adapter.MenuAdapter;


public class MenuActivity extends AppCompatActivity {
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;

    GridView gridView;

    public static String[] items = {
            "Adicionar Filho", "Adicionar Regra",
            "Administrar", "Lista de Regras"

    };


    public static int[] image = {
            R.mipmap.ic_02, R.mipmap.ic_03,
            R.mipmap.ic_04, R.mipmap.ic_05,
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        gridView = (GridView) findViewById(R.id.grid_view_menu);
        gridView.setAdapter(new MenuAdapter(this, items, image));
        iniciarInstancias();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                switch (i) {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), AddFilho.class);
                        startActivity(intent);
                        break;


                    case 1:
                        Intent intent1 = new Intent(getApplicationContext(), AddRegra.class);
                        startActivity(intent1);
                        break;

                    case 2:

                        Intent intent2 = new Intent(getApplicationContext(), ListaFilhos.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(getApplicationContext(), ListaRegras.class);
                        startActivity(intent3);
                        break;

                }

            }
        });

    }

    private void iniciarInstancias() {
        rootLayoutAndroid = (CoordinatorLayout) findViewById(R.id.android_coordinator_layout);
        collapsingToolbarLayoutAndroid = (CollapsingToolbarLayout) findViewById(R.id.android_collapsingtoolbarLayout);
        collapsingToolbarLayoutAndroid.setTitle("Mesada");
    }
 /* public static AlertDialog alertSobre(Activity activity){

       final int[] tecLogo = {
               R.drawable.logotecnoia
       };
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
       builder.setTitle("Sobre");
      LayoutInflater factory = LayoutInflater.from(activity);

      final View  view =factory.inflate(R.layout.sobre)
      builder.setView()
       return builder.create();
   }
*/
}