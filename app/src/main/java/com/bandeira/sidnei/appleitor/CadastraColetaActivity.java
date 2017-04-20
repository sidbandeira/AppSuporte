package com.bandeira.sidnei.appleitor;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import google.zxing.integration.android.IntentIntegrator;
import google.zxing.integration.android.IntentResult;

public class CadastraColetaActivity extends AppCompatActivity {
    public static String delimitador = "";
    public static String nomeArquivo = "";
    private EditText edtDescricao;
    private Button btConfirma;
    private Button btCancela;
    private Spinner spDelimitador;
    private ArrayList<String> Arquivos = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_coleta);

        edtDescricao = (EditText)findViewById(R.id.edtDescricao);
        btConfirma = (Button)findViewById(R.id.btConfirma);
        btCancela = (Button)findViewById(R.id.btCancela);
        spDelimitador = (Spinner) findViewById(R.id.spDelimitador);
    }

    public void btCofirma(View view) throws FileNotFoundException {
        delimitador = (String)spDelimitador.getSelectedItem();
        nomeArquivo = edtDescricao.getText().toString();

        if (nomeArquivo.isEmpty()){
            Toast.makeText(this,"Escolha uma descrição para o arquivo de coleta.",Toast.LENGTH_SHORT).show();
            edtDescricao.hasFocus();
        }

        if (delimitador.contains("Selecione")){
            Toast.makeText(this,"Escolha um delimitador",Toast.LENGTH_SHORT).show();
            spDelimitador.hasFocus();
        }else{
            salvararquivo();
//            Intent it = new Intent(this, ColetaActivity.class);
//            startActivity(it);
        }
    }


    private void salvararquivo(){
        String lstrNomeArq;
        File arq;
        byte[] dados;
        try
        {
            lstrNomeArq = edtDescricao.getText().toString() + ".txt";

            arq = new File(Environment.getExternalStorageDirectory(), lstrNomeArq);
            FileOutputStream fos;
            String coleta = "";
            for(int i = 0; i < 10; i++) {
                coleta += edtDescricao.getText() + delimitador + i + " \n";
            }

            dados = coleta.toString().getBytes();

            fos = new FileOutputStream(arq);
            fos.write(dados );
            fos.flush();
            fos.close();
            Mensagem("Texto Salvo com sucesso!");
            //Listar();
        }
        catch (Exception e)
        {
            Mensagem("Erro : " + e.getMessage());
        }
    }


    private void Mensagem(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
//
//    //O próximo método nos retorna o diretório de armazenamento externo.
//
//    private String ObterDiretorio()
//    {
//        File root = android.os.Environment.getExternalStorageDirectory();
//        return root.toString();
//    }
//
//    public void Listar()
//    {
//        File diretorio = new File(ObterDiretorio());
//        File[] arquivos = diretorio.listFiles();
//        if(arquivos != null)
//        {
//            int length = arquivos.length;
//            for(int i = 0; i < length; ++i)
//            {
//                File f = arquivos[i];
//                if (f.isFile())
//                {
//                    Arquivos.add(f.getName());
//                }
//            }
//
////            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
////                    (this,android.R.layout.simple_dropdown_item_1line, Arquivos);
////            SpnListarArquivos.setAdapter(arrayAdapter);
//        }
//    }
//
//    public void click_Salvar(View v)
//    {
////        String lstrNomeArq;
////        File arq;
////        byte[] dados;
////        try
////        {
////            lstrNomeArq = txtNomeArq.getText().toString();
////
////            arq = new File(Environment.getExternalStorageDirectory(), lstrNomeArq);
////            FileOutputStream fos;
////
////            dados = txtSalvar.getText().toString().getBytes();
////
////            fos = new FileOutputStream(arq);
////            fos.write(dados);
////            fos.flush();
////            fos.close();
////            Mensagem("Texto Salvo com sucesso!");
////            Listar();
////        }
////        catch (Exception e)
////        {
////            Mensagem("Erro : " + e.getMessage());
////        }
//    }
//
//
//    public void click_Carregar(View v)
//    {
////        String lstrNomeArq;
////        File arq;
////        String lstrlinha;
////        try
////        {
////            lstrNomeArq = SpnListarArquivos.getSelectedItem().toString();
////
////            txtLer.setText("");
////
////            arq = new File(Environment.getExternalStorageDirectory(), lstrNomeArq);
////            BufferedReader br = new BufferedReader(new FileReader(arq));
////
////            while ((lstrlinha = br.readLine()) != null)
////            {
////                if (!txtLer.getText().toString().equals(""))
////                {
////                    txtLer.append("\n");
////                }
////                txtLer.append(lstrlinha);
////            }
////
////            Mensagem("Texto Carregado com sucesso!");
////
////        }
////        catch (Exception e)
////        {
////            Mensagem("Erro : " + e.getMessage());
////        }
//    }
}
