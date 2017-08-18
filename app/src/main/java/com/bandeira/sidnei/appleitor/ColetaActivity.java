package com.bandeira.sidnei.appleitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bandeira.sidnei.appleitor.Classes.ColetaItem;
import com.bandeira.sidnei.appleitor.Repositorio.ColetaItemRepositorio;

import google.zxing.integration.android.IntentIntegrator;
import google.zxing.integration.android.IntentResult;

public class ColetaActivity extends AppCompatActivity {
    private Button btBarras;
    private EditText edtBarras;
    private EditText edtQtde;
    private long codColeta;
    private String codigoBarras;
    private Double qtde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coleta);
        Intent it = getIntent();
        codColeta = Long.parseLong(it.getStringExtra("codigoColeta"));
        btBarras = (Button)findViewById(R.id.btBarras);
        edtBarras = (EditText) findViewById(R.id.edtBarras);
        edtQtde = (EditText) findViewById(R.id.edtQtde);
    }

    public void btBarras(View view) {
        if(view.getId()==R.id.btBarras){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            //formatTxt.setText("FORMAT: " + scanFormat);
            //contentTxt.setText("CONTENT: " + scanContent);
            edtBarras.setText(scanContent);
            edtQtde.requestFocus();
        }
        else{
            Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT).show();
            edtBarras.requestFocus();
        }
    }

    public void btConfirma(View view) {
        codigoBarras = edtBarras.getText().toString();
        qtde = Double.parseDouble(edtQtde.getText().toString());

        if(codigoBarras.isEmpty()){
            Toast.makeText(this,"Informe um código de barras!",Toast.LENGTH_SHORT).show();
            edtBarras.hasFocus();
        }

        if(qtde.equals(0)){
            Toast.makeText(this,"Informe a quantidade coletada!",Toast.LENGTH_SHORT).show();
            edtQtde.hasFocus();
        }

        gravarColetaItem();

    }

    private void gravarColetaItem(){
        ColetaItemRepositorio rep = new ColetaItemRepositorio(this);
        ColetaItem novoItem = new ColetaItem();
        novoItem.idcoleta = codColeta;
        novoItem.codbarras = codigoBarras;
        novoItem.quantidade = qtde;

        rep.salvar(novoItem);
        if (novoItem.get_id() <= 0) {
            Toast.makeText(getApplicationContext(), "Erro ao gravar item coletado!", Toast.LENGTH_SHORT).show();
            edtBarras.hasFocus();
        }else{
            Toast.makeText(getApplicationContext(),"Produto gravado com sucesso!" , Toast.LENGTH_SHORT).show();
            novo();
        }
    }

    private void novo(){
        edtBarras.setText("");
        edtQtde.setText("");
        edtBarras.requestFocus();
    }

}
