package com.bandeira.sidnei.appleitor.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bandeira.sidnei.appleitor.Classes.Coleta;
import com.bandeira.sidnei.appleitor.R;

import java.util.ArrayList;

public class AdapterListagemExportaColeta extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Coleta> coletas;

    public AdapterListagemExportaColeta(Context context, ArrayList<Coleta> coletas)
    {
        //Itens que preencheram o listview
        this.coletas = coletas;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return coletas.size();
    }

    @Override
    public Object getItem(int position) {
        return coletas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Pega o item de acordo com a posção.
        Coleta col = coletas.get(position);
        //infla o layout para podermos preencher os dados
        convertView = mInflater.inflate(R.layout.item_listagem_exporta_coleta, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) convertView.findViewById(R.id.txtCodColeta)).setText(String.valueOf(col.get_id()));
        ((TextView) convertView.findViewById(R.id.txtDescricao)).setText(col.coletadescricao);

        return convertView;
    }
}
