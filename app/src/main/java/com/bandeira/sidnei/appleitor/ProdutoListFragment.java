package com.bandeira.sidnei.appleitor;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bandeira.sidnei.appleitor.Adaptadores.ProdutoListagemAdapter;
import com.bandeira.sidnei.appleitor.Classes.Produto;
import com.bandeira.sidnei.appleitor.Classes.ProdutoHttp;

import java.util.ArrayList;
import java.util.List;


public class ProdutoListFragment extends Fragment implements AdapterView.OnItemClickListener{
    ProdutoTask mTask;
    List<Produto> mProduto;
    ListView mListView;
    TextView mTextMensagem;
    ProgressBar mProgressBar;
    ArrayAdapter<Produto> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_produto_list, null);
        mTextMensagem = (TextView)layout.findViewById(android.R.id.empty);
        mProgressBar = (ProgressBar)layout.findViewById(R.id.progressBar);
        mListView = (ListView)layout.findViewById(R.id.list);
        mListView.setEmptyView(mTextMensagem);
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mProduto == null) {
            mProduto = new ArrayList<Produto>();
        }
        mAdapter = new ProdutoListagemAdapter(getActivity(), mProduto);
        mListView.setAdapter(mAdapter);
        if (mTask == null) {
            if (ProdutoHttp.temConexao(getActivity())) {
                iniciarDownload();
            } else {
                mTextMensagem.setText("Sem conexão");
            }
        } else if (mTask.getStatus() == AsyncTask.Status.RUNNING) {
            exibirProgress(true);
        }
        mListView.setOnItemClickListener(this);
    }

    private void exibirProgress(boolean exibir) {
        if (exibir) {
            mTextMensagem.setText("Aguarde baixando informações...");
        }
        mTextMensagem.setVisibility(exibir ? View.VISIBLE : View.GONE);
        mProgressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    public void iniciarDownload() {
        if (mTask == null || mTask.getStatus() != AsyncTask.Status.RUNNING) {
            mTask = new ProdutoTask();
            mTask.execute();
        }
    }

    // CHAMA A TELA DE DETALHE DO PRODUTO
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        /*Produto pro = (Produto) adapterView.getItemAtPosition(i);
        Intent it = new Intent(getActivity(),ProdDetalheActivity.class );
        it.putExtra( "descricao" , pro.produtoDescricao );
        it.putExtra( "codBarras" , pro.produtoCodBarras );
        it.putExtra( "precoCusto" , pro.produtoPrecoCusto.toString() );
        it.putExtra( "precoVenda" , pro.produtoPrecoVenda.toString() );
        it.putExtra( "saldo" , pro.produtoSaldo.toString() );

        startActivity(it);*/
    }

    class ProdutoTask extends AsyncTask<Void, Void, List<Produto>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exibirProgress(true);
        }
        @Override
        protected List<Produto> doInBackground(Void... strings) {

            return ProdutoHttp.carregarProdutoJson();
        }
        @Override
        protected void onPostExecute(List<Produto> produtos) {
            super.onPostExecute(produtos);
            exibirProgress(false);
            if (produtos != null) {
                mProduto.clear();
                mProduto.addAll(produtos);
                mAdapter.notifyDataSetChanged();
            } else {
                mTextMensagem.setText("Falha ao obter registros");
            }
        }
    }
}
