package com.bandeira.sidnei.SuporteMobile.Classes;

public class Produto {

    public int _id;
    public String produtoCodBarras;
    public String produtoDescricao;
    public String produtoClasseFiscal;
    public String produtoUnidade;
    public Double produtoSaldo;
    public Double produtoPrecoCusto;
    public Double produtoPrecoVenda;
    public Double produtoPrecoLista;
    public String produtoLinkImagem;

    //CONSTRUTOR VAZIO
    public Produto(){

    }

    //CONSTRUTOR COM PARAMETROS
    public Produto(int _id, String produtoCodBarras, String produtoDescricao, String produtoClasseFiscal, String produtoUnidade, Double produtoSaldo,
                   Double produtoPrecoCusto, Double produtoPrecovenda, Double produtoPrecoLista, String produtoLinkImagem ){
        this._id = _id;
        this.produtoCodBarras = produtoCodBarras;
        this.produtoDescricao = produtoDescricao;
        this.produtoClasseFiscal = produtoClasseFiscal;
        this.produtoUnidade = produtoUnidade;
        this.produtoSaldo = produtoSaldo;
        this.produtoPrecoCusto = produtoPrecoCusto;
        this.produtoPrecoVenda = produtoPrecovenda;
        this.produtoPrecoLista = produtoPrecoLista;
        this.produtoLinkImagem = produtoLinkImagem;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getProdutoCodBarras() {
        return produtoCodBarras;
    }

    public void setProdutoCodBarras(String produtoCodBarras) {
        this.produtoCodBarras = produtoCodBarras;
    }

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }

    public String getProdutoClasseFiscal() {
        return produtoClasseFiscal;
    }

    public void setProdutoClasseFiscal(String produtoClasseFiscal) {
        this.produtoClasseFiscal = produtoClasseFiscal;
    }

    public String getProdutoUnidade() {
        return produtoUnidade;
    }

    public void setProdutoUnidade(String produtoUnidade) {
        this.produtoUnidade = produtoUnidade;
    }

    public Double getProdutoSaldo() {
        return produtoSaldo;
    }

    public void setProdutoSaldo(Double produtoSaldo) {
        this.produtoSaldo = produtoSaldo;
    }

    public Double getProdutoPrecoCusto() {
        return produtoPrecoCusto;
    }

    public void setProdutoPrecoCusto(Double produtoPrecoCusto) {
        this.produtoPrecoCusto = produtoPrecoCusto;
    }

    public Double getProdutoPrecoVenda() {
        return produtoPrecoVenda;
    }

    public void setProdutoPrecoVenda(Double produtoPrecoVenda) {
        this.produtoPrecoVenda = produtoPrecoVenda;
    }

    public Double getProdutoPrecoLista() {
        return produtoPrecoLista;
    }

    public void setProdutoPrecoLista(Double produtoPrecoLista) {
        this.produtoPrecoLista = produtoPrecoLista;
    }

    public String getProdutoLinkImagem() {
        return produtoLinkImagem;
    }

    public void setProdutoLinkImagem(String produtoLinkImagem) {
        this.produtoLinkImagem = produtoLinkImagem;
    }

    public String tostrinString(){
        String temp;
        return String.valueOf(this._id);
    }

}
