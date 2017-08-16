package com.bandeira.sidnei.appleitor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import com.bandeira.sidnei.appleitor.Adaptadores.AdapterListagemExportaColeta;
import com.bandeira.sidnei.appleitor.Classes.Coleta;
import com.bandeira.sidnei.appleitor.Repositorio.ColetaRepositorio;

import java.util.ArrayList;
import java.util.List;

public class ExportaActivity extends AppCompatActivity {
    private ListView listaColeta;
    List<Coleta> coleta = new ArrayList<Coleta>();
    private Integer posicao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exporta);

        listaColeta = (ListView)findViewById(R.id.listagemColetas);
        listarColetas();

    }

    public void btExportar(View view) {
        Adapter ad = listaColeta.getAdapter();

        for (int i = 0; i < ad.getCount(); i++) {
            Coleta col  = (Coleta) ad.getItem(i);
            if(col.isMarcado()){
                // EXECUTAR A AÇÃO DE EXPORTAR AQUI
            }
        }
    }

    public void btCancelar(View view) {
        finish();
    }

    private void listarColetas(){
        ColetaRepositorio rep = new ColetaRepositorio(this);
        coleta = rep.buscarColeta(null);
        listaColeta.setAdapter(new AdapterListagemExportaColeta(this, (ArrayList<Coleta>) coleta ));
    }
}
