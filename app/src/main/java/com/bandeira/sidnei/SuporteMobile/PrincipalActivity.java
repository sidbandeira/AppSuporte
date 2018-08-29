package com.bandeira.sidnei.SuporteMobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void imgLogo(View view) {
        Intent it = new Intent(this, MenuPrincipalActivity.class);
        startActivity(it);

    }

    //chamada para o menu de configuração
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.configurar:
                Intent it = new Intent(this, ConfiguracaoActivity.class);
                startActivity(it);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
