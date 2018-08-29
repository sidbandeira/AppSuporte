package com.bandeira.sidnei.SuporteMobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bandeira.sidnei.SuporteMobile.Classes.Configuracao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfiguracaoActivity extends Activity {
    private EditText edtUrlAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        InicializaListeners();

    }

    public void InicializaListeners()
    {
        edtUrlAPI = (EditText) findViewById(R.id.edtUrlAPI);

        carregar();
    }


    public void btSalvar(View view) {

        Configuracao conf = new Configuracao();
        conf.setUrl(edtUrlAPI.getText().toString());
//        conf.setUsuario(edtUsuario.getText().toString());
//        conf.setSenha(edtSenha.getText().toString());
//        conf.setDiretorio(edtDiretorio.getText().toString());
        Properties properties = new Properties();
//        properties.setProperty("conf.Usuario", conf.getUsuario());
//        properties.setProperty("conf.Senha", conf.getSenha());
        properties.setProperty("conf.Url", conf.getUrl());
//        properties.setProperty("conf.Diretorio", conf.getDiretorio());
//
        try
        {
            FileOutputStream fos = new FileOutputStream("/mnt/sdcard/configuracao.properties");
            properties.store(fos, "CONFIGURACAO FTP:");
            fos.close();
            Toast.makeText(this, "Dados Salvos com sucesso!!", Toast.LENGTH_SHORT).show();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void carregar()
    {
        Properties properties = new Properties();
        try
        {
            FileInputStream fis = new  FileInputStream("/mnt/sdcard/configuracao.properties");
            properties.load(fis);

            String Url = properties.getProperty("conf.Url");
            //String Usuario = properties.getProperty("conf.Usuario");
            //String Senha = properties.getProperty("conf.Senha");
            //String Diretorio = properties.getProperty("conf.Diretorio");

            edtUrlAPI.setText(Url);
            //edtUsuario.setText(Usuario);
            //edtSenha.setText(Senha);
            //edtDiretorio.setText(Diretorio);
            Toast.makeText(this, "Dados Carregados com sucesso!!", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
