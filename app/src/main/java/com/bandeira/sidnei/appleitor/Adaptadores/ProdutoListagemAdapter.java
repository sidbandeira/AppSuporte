package com.bandeira.sidnei.appleitor.Adaptadores;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bandeira.sidnei.appleitor.Classes.Produto;
import com.bandeira.sidnei.appleitor.R;

import java.util.List;

public class ProdutoListagemAdapter extends ArrayAdapter<Produto> {
    public ProdutoListagemAdapter(Context context, List<Produto> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Produto produto = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_produto_list,null);

            holder = new ViewHolder();
            holder.txtId = (TextView)convertView.findViewById(R.id.txtId);
            holder.txtCodBarras =  (TextView)convertView.findViewById(R.id.txtCodBarras);
            holder.txtDescricaoProd =  (TextView)convertView.findViewById(R.id.txtDescricaoProd);
            holder.txtVendaProduto =  (TextView)convertView.findViewById(R.id.txtVendaProduto);
            holder.txtCustoProd =  (TextView)convertView.findViewById(R.id.txtCustoProd);
            holder.txtUltimaCompra =  (TextView)convertView.findViewById(R.id.txtUltimaCompra);
            holder.txtSaldo =  (TextView)convertView.findViewById(R.id.txtSaldo);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);
        holder.txtId.setText(String.valueOf(produto._id));
        holder.txtCodBarras.setText(String.valueOf(produto.produtoCodBarras));
        holder.txtDescricaoProd.setText(produto.produtoDescricao);
        holder.txtVendaProduto.setText(produto.produtoPrecoVenda.toString());
        holder.txtCustoProd.setText(produto.produtoPrecoCusto.toString());
        //holder.txtUltimaCompra.setText(produto.produtodtultimacompra);
        holder.txtSaldo.setText(produto.produtoSaldo.toString());

        //Picasso.with(getContext()).load("http://sgestao.hol.es/img/" + produto.produtoCodBarras+".png").into(holder.imgFoto);

        return convertView;
    }

    static class ViewHolder {
        ImageView imgFoto;
        TextView txtId;
        TextView txtCodBarras;
        TextView txtDescricaoProd;
        TextView txtVendaProduto;
        TextView txtCustoProd;
        TextView txtUltimaCompra;
        TextView txtSaldo;

    }
}
