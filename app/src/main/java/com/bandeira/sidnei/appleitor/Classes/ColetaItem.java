package com.bandeira.sidnei.appleitor.Classes;

public class ColetaItem {
    public long _id;
    public String codbarras;
    public Double quantidade;
    public long idcoleta;

    public long get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCodbarras() {
        return codbarras;
    }

    public void setCodbarras(String codbarras) {
        this.codbarras = codbarras;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public long getIdcoleta() {
        return idcoleta;
    }

    public void setIdcoleta(int idcoleta) {
        this.idcoleta = idcoleta;
    }

    public ColetaItem(){

    }

    public ColetaItem(long _id, String codbarras, Double quantidade, long idcoleta){
        this._id = _id;
        this.codbarras = codbarras;
        this.quantidade = quantidade;
        this.idcoleta = idcoleta;

    }
}
