package com.bandeira.sidnei.SuporteMobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
}
