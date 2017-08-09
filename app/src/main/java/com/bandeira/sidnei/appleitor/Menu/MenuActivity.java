package com.bandeira.sidnei.appleitor.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bandeira.sidnei.appleitor.CadastraColetaActivity;
import com.bandeira.sidnei.appleitor.ExcluirColetasActivity;
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
        startActivityForResult(it,20);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 20) {
            Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
        }
    }

    //LISTAGEM DE COLETAS GRAVADAS.
    public void btnContinua(View view) {
        Intent it = new Intent(this, ListagemColetasActivity.class);
        startActivityForResult(it,21);
    }

    public void btExclui(View view) {
        Intent it = new Intent(this, ExcluirColetasActivity.class);
        startActivityForResult(it,22);
    }
}
