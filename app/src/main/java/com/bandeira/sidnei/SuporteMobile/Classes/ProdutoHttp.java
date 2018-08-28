package com.bandeira.sidnei.SuporteMobile.Classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bandeira.sidnei.SuporteMobile.PesquisarProdutosActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProdutoHttp {
    //public static final String PRODUTO_URL_JSON = "http://10.0.2.2/arquivos/arquivoglobonovo.json"; CONEXAO LOCAL HOST
    //public static final String PRODUTO_URL_JSON = "http://suporte-cloud.azurewebsites.net/api/produtoglobo"; CONEXAO EXTERNA
    public static final String PRODUTO_URL_JSON = "http://192.168.200.23:8080/api/produto"; //CONEXAO INTERNA

    private static HttpURLConnection connectar(String urlArquivo) throws IOException {
        final int SEGUNDOS = 1000;
        URL url = new URL(urlArquivo);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setReadTimeout(10 * SEGUNDOS);
        conexao.setConnectTimeout(15 * SEGUNDOS);
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true);
        conexao.setDoOutput(false);
        conexao.connect();
        return conexao;
    }

    public static boolean temConexao(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    //FUNCAO VAI RECEBER UMA VARIAVEL INTEIRA COM O CODIGO DA EMPRESA PARA RECARREGAR AS UNIDADES
    // DE NEGOCIO SEMPRE QUE O USUARIO EFETUAR O LOGIN
    public static List<Produto> carregarProdutoJson() {
        try {

            //HttpURLConnection conexao = connectar(PRODUTO_URL_JSON + empresa);
            HttpURLConnection conexao = connectar(PRODUTO_URL_JSON + "/" + PesquisarProdutosActivity.codproduto);
            int resposta = conexao.getResponseCode();
            if (resposta == HttpURLConnection.HTTP_OK || resposta == HttpURLConnection.HTTP_CREATED) {
                InputStream is = conexao.getInputStream();
                JSONObject json = new JSONObject(bytesParaString(is));
                return lerJsonProd(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Produto> lerJsonProd(JSONObject json1) throws JSONException {
        List<Produto> listadeProduto = new ArrayList<Produto>();
        //OBJETO PAI DO JSON
        //JSONArray jsonProd = json.getJSONArray("produtos");

        Produto prod = new Produto(
                json1.getInt("procodigo"),
                json1.getString("probarras"),
                json1.getString("pronome"),
                "",
                "",
                0.00,
                0.00,
                0.00,
                0.00,
                "");
        listadeProduto.add(prod);

//        for (int i = 0; i < jsonProd.length(); i++) {
//            JSONObject jsonProduto = jsonProd.getJSONObject(i);
//            Produto prod = new Produto(
//                    jsonProduto.getInt("ProCodigo"),
//                    jsonProduto.getString("ProBarras"),
//                    jsonProduto.getString("ProNome"),
//                    jsonProduto.getString("ClasseFiscal"),
//                    jsonProduto.getString("Unidade"),
//                    jsonProduto.getDouble("SaldoTotal"),
//                    jsonProduto.getDouble("UltimoCusto"),
//                    jsonProduto.getDouble("PrecoVenda"),
//                    jsonProduto.getDouble("PrecoVendaLista"),
//                    jsonProduto.getString("LinkImagem"));
//            listadeProduto.add(prod);
//        }
        return listadeProduto;
    }

    private static String bytesParaString(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        // O bufferzao vai armazenar todos os bytes lidos
        ByteArrayOutputStream bufferzao = new ByteArrayOutputStream();
        // precisamos saber quantos bytes foram lidos
        int bytesLidos;
        // Vamos lendo de 1KB por vez...
        while ((bytesLidos = is.read(buffer)) != -1) {
            // copiando a quantidade de bytes lidos do buffer para o bufferzão
            bufferzao.write(buffer, 0, bytesLidos);
        }
        return new String(bufferzao.toByteArray(), "UTF-8");
    }
}
