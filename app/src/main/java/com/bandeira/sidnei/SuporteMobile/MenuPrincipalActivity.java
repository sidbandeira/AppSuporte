package com.bandeira.sidnei.SuporteMobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bandeira.sidnei.SuporteMobile.Menu.MenuActivity;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void btnColetor(View view) {
        Intent it = new Intent(this, MenuActivity.class);
        startActivity(it);

    }

    public void btnProdutos(View view) {
        Intent it = new Intent(this, PesquisarProdutosActivity.class);
        startActivity(it);

    }
}
