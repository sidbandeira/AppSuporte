package com.bandeira.sidnei.appleitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import google.zxing.integration.android.IntentIntegrator;
import google.zxing.integration.android.IntentResult;

public class CadastraColetaActivity extends AppCompatActivity {
    public static String delimitador = "";
    private EditText edtDescricao;
    private Button btConfirma;
    private Button btCancela;
    private Spinner spDelimitador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_coleta);

        edtDescricao = (EditText)findViewById(R.id.edtDescricao);
        btConfirma = (Button)findViewById(R.id.btConfirma);
        btCancela = (Button)findViewById(R.id.btCancela);
        spDelimitador = (Spinner) findViewById(R.id.spDelimitador);
    }

    public void btCofirma(View view) {
        delimitador = (String)spDelimitador.getSelectedItem();
        Toast.makeText(this,delimitador,Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, ColetaActivity.class);
        startActivity(it);
    }
}
