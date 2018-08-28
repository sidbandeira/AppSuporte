package com.bandeira.sidnei.SuporteMobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PesquisarProdutosActivity extends AppCompatActivity {
    public static int codproduto = 0 ;
    private EditText edtCodigoProduto;
    private Button btPesquisar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_produtos);

        edtCodigoProduto = (EditText)findViewById(R.id.edtCodigoProduto);
        btPesquisar = (Button)findViewById(R.id.btPesquisar);
    }

    public void btPesquisar(View view) {
        if (edtCodigoProduto.getText().toString().isEmpty()){

            Toast.makeText(this,"Informe um produto válido!!",Toast.LENGTH_SHORT).show();
            edtCodigoProduto.hasFocus();
        }else{
            codproduto = Integer.parseInt(edtCodigoProduto.getText().toString());
            Intent it = new Intent(this, ProdutosActivity.class);
            startActivity(it);
        }

    }
}
