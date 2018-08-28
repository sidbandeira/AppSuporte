package com.bandeira.sidnei.SuporteMobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bandeira.sidnei.SuporteMobile.Classes.Coleta;
import com.bandeira.sidnei.SuporteMobile.Repositorio.ColetaRepositorio;

import java.io.FileNotFoundException;

public class CadastraColetaActivity extends AppCompatActivity {
    public static String nomeColeta = "";
    private Long codColeta ;
    private EditText edtDescricao;
    private Button btConfirma;
    private Button btCancela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_coleta);

        edtDescricao = (EditText)findViewById(R.id.edtDescricao);
        btConfirma = (Button)findViewById(R.id.btConfirma);
        btCancela = (Button)findViewById(R.id.btCancela);
    }

    public void btCofirma(View view) throws FileNotFoundException {
        nomeColeta = edtDescricao.getText().toString();

        if (nomeColeta.isEmpty()){
            Toast.makeText(this,"Escolha uma descrição para o arquivo de coleta.",Toast.LENGTH_SHORT).show();
            edtDescricao.hasFocus();
        }else{
            gravarColeta();
            Intent it = new Intent(this, ColetaActivity.class);
            it.putExtra("codigoColeta", String.valueOf( codColeta));
            //startActivity(it);
            startActivityForResult(it,1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            edtDescricao.setText("");
            edtDescricao.requestFocus();
        }
    }

    private void gravarColeta(){
        ColetaRepositorio rep = new ColetaRepositorio(this);
        Coleta novaColeta = new Coleta();
        novaColeta.setcoletadescricao(nomeColeta);
        rep.salvar(novaColeta);
        if (novaColeta.get_id() <= 0) {
            Toast.makeText(getApplicationContext(), "Erro ao gravar a coleta!", Toast.LENGTH_SHORT).show();
            edtDescricao.hasFocus();
        }else{
            codColeta = novaColeta.get_id();
        }
    }
}
