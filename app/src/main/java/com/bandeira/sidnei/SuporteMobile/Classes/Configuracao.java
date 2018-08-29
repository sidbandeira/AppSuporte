package com.bandeira.sidnei.SuporteMobile.Classes;

public class Configuracao {
    private String url;
    private String usuario;
    private String senha;
    private String diretorio;

    public String getUrl(){
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
