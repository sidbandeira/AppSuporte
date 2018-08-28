package com.bandeira.sidnei.SuporteMobile;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bandeira.sidnei.SuporteMobile.Adaptadores.AdapterListagemColeta;
import com.bandeira.sidnei.SuporteMobile.Classes.Coleta;
import com.bandeira.sidnei.SuporteMobile.Repositorio.ColetaItemRepositorio;
import com.bandeira.sidnei.SuporteMobile.Repositorio.ColetaRepositorio;

import java.util.ArrayList;
import java.util.List;

public class ExcluirColetasActivity extends AppCompatActivity {
    private ListView listaColeta;
    List<Coleta> coleta = new ArrayList<Coleta>();
    private Integer posicao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_coletas);

        listaColeta = (ListView)findViewById(R.id.listagemColetas);
        listarColetas();


        listaColeta.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicao = position;
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ExcluirColetasActivity.this);

                // SETA UM TITULO PARA O DIALOG
                alertDialogBuilder.setTitle("Escolha a opção desejada");

                // SETA UMA MENSAGEM PARA O DIALOG
                alertDialogBuilder
                        .setMessage("O que deseja fazer?")

                        // BOTAO 1
                        .setNegativeButton("Excluir",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                listaColeta.setAdapter(null);
                                excluirColeta((int) coleta.get( posicao)._id);
                                finish();
                            }
                        })

                        // BOTAO 2
                        .setNeutralButton("Voltar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.dismiss();
                            }
                        });

                // CRIA O DIALOG
                AlertDialog alertDialog = alertDialogBuilder.create();

                // EXIBE O DIALOG
                alertDialog.show();

            }

        });
    }


    private void listarColetas(){
        ColetaRepositorio rep = new ColetaRepositorio(this);
        coleta = rep.buscarColeta(null);
        listaColeta.setAdapter(new AdapterListagemColeta(this, (ArrayList<Coleta>) coleta ));
    }

    private void excluirColeta(Integer codigo){
        // EXCLUI OS ITEMS DA COLETA
        ColetaItemRepositorio repItem = new ColetaItemRepositorio(this);
        repItem.excluirTodosItens(codigo);
        // EXCLUI A COLETA
        ColetaRepositorio rep = new ColetaRepositorio(this);
        rep.excluir(codigo);
    }

}
