package com.bandeira.sidnei.appleitor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.bandeira.sidnei.appleitor.Adaptadores.AdapterListagemColeta;
import com.bandeira.sidnei.appleitor.Classes.Coleta;
import com.bandeira.sidnei.appleitor.Repositorio.ColetaRepositorio;

import java.util.ArrayList;
import java.util.List;

public class ListagemColetasActivity extends AppCompatActivity {
    private ListView listaColeta;
    List<Coleta> coleta = new ArrayList<Coleta>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_coletas);
        listaColeta = (ListView)findViewById(R.id.listagemColetas);
        listarCOletas();
    }

    private void listarCOletas(){
        ColetaRepositorio rep = new ColetaRepositorio(this);
        coleta = rep.buscarColeta(null);

        listaColeta.setAdapter(new AdapterListagemColeta(this, (ArrayList<Coleta>) coleta ));
    }

}
