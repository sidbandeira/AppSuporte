package com.bandeira.sidnei.appleitor.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bandeira.sidnei.appleitor.CadastraColetaActivity;
import com.bandeira.sidnei.appleitor.ExcluirColetasActivity;
import com.bandeira.sidnei.appleitor.ExportaActivity;
import com.bandeira.sidnei.appleitor.ListagemColetasActivity;
import com.bandeira.sidnei.appleitor.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    //CADASTRA UMA NOVA COLETA
    public void btnNova(View view) {
        Intent it = new Intent(this, CadastraColetaActivity.class);
        startActivityForResult(it,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            //Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
        }
    }

    //LISTAGEM DE COLETAS GRAVADAS.
    public void btnContinua(View view) {
        Intent it = new Intent(this, ListagemColetasActivity.class);
        startActivityForResult(it,2);
    }

    public void btExporta(View view) {
        Intent it = new Intent(this, ExportaActivity.class);
        startActivityForResult(it,3);
    }

    public void btExclui(View view) {
        Intent it = new Intent(this, ExcluirColetasActivity.class);
        startActivityForResult(it,4);
    }

}
