package com.bandeira.sidnei.appleitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
