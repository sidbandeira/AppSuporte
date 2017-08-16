package com.bandeira.sidnei.appleitor.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bandeira.sidnei.appleitor.Classes.Coleta;
import com.bandeira.sidnei.appleitor.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterListagemExportaColeta extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Coleta> coletas;
    private final List<Integer> idSelecionados = new ArrayList<Integer>(); // Para armazenar os itens selecionados
    private Context context;

    public AdapterListagemExportaColeta(Context context, ArrayList<Coleta> coletas)
    {
        this.context = context;
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
    public View getView(int position,  View convertView, ViewGroup parent) {
        View view;

        //Pega o item de acordo com a posção.
        Coleta col = coletas.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.item_listagem_exporta_coleta, null);

        TextView txtCodColeta = (TextView) view.findViewById(R.id.txtCodColeta);
        TextView txtDescricao = (TextView) view.findViewById(R.id.txtDescricao);
        CheckBox checMarcado = (CheckBox) view.findViewById(R.id.chkMarcado);


        txtCodColeta.setText(String.valueOf(col.get_id()));
        txtDescricao.setText(col.getcoletadescricao());
        checMarcado.setChecked(col.isMarcado());
        //Define uma tag para o checBox, uma recuperarmos os dados quando for clicado no checkBox
        checMarcado.setTag(col);

        checMarcado.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                CheckBox check = (CheckBox) v;
                Coleta coleta = (Coleta) v.getTag();
                coleta.setMarcado(((CheckBox) v).isChecked());


                if (check.isChecked()) {

                    //Toast.makeText(context, String.valueOf(coleta.get_id()), Toast.LENGTH_SHORT).show();
                    //Faz uma checagem se existe o mesmo valor na lista de inteiros
                    if(!idSelecionados.contains(String.valueOf(coleta.get_id()))){
                        //Adiciona em uma lista para poder manipular os dados depois
                        idSelecionados.add((int) coleta.get_id());
                    }

                } else {
                    //Toast.makeText(context,String.valueOf(coleta.get_id()),Toast.LENGTH_SHORT).show();

                    //Faz uma checagem se existe o mesmo valor na lista de inteiros
                    if(idSelecionados.contains(coleta.get_id())){
                        //Remove da lista se existir na lista
                        idSelecionados.add((int) coleta.get_id());
                    }

                }
            }
        });


        return view;
    }
}
