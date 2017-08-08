package com.bandeira.sidnei.appleitor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bandeira.sidnei.appleitor.Adaptadores.AdapterListagemColeta;
import com.bandeira.sidnei.appleitor.Classes.Coleta;
import com.bandeira.sidnei.appleitor.Repositorio.ColetaItemRepositorio;
import com.bandeira.sidnei.appleitor.Repositorio.ColetaRepositorio;

import java.util.ArrayList;
import java.util.List;

public class ListagemColetasActivity extends AppCompatActivity {
    private ListView listaColeta;
    List<Coleta> coleta = new ArrayList<Coleta>();
    private Integer posicao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_coletas);
        listaColeta = (ListView)findViewById(R.id.listagemColetas);
        listarColetas();

        listaColeta.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicao = position;
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListagemColetasActivity.this);

                // SETA UM TITULO PARA O DIALOG
                alertDialogBuilder.setTitle("Escolha a opção desejada");

                // SETA UMA MENSAGEM PARA O DIALOG
                alertDialogBuilder
                        .setMessage("O que deseja fazer?")
                        // BOTAO 1
                        .setPositiveButton("Coletar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                if ((int) coleta.get( posicao)._id <= 0) {
                                    Toast.makeText(getApplicationContext(), "Erro ao selecionar uma coleta!", Toast.LENGTH_SHORT).show();
                                }else{
                                    continuarColeta((int) coleta.get( posicao)._id);
                                }
                            }
                        })

                        // BOTAO 2
                        .setNegativeButton("Excluir",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                listaColeta.setAdapter(null);
                                excluirColeta((int) coleta.get( posicao)._id);
                                finish();
                            }
                        })

                        // BOTAO 3
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

    private void continuarColeta(Integer codigo){
        Intent it = new Intent(this, ColetaActivity.class);
        it.putExtra("codigoColeta", String.valueOf( codigo));
        startActivity(it);
    }

}
