package com.bandeira.sidnei.appleitor.Classes;

public class Coleta {
    public long _id ;
    public String coletadescricao;
    public Boolean marcado = false;


    public String getcoletadescricao() {
        return coletadescricao;
    }

    public void setcoletadescricao(String coletadescricao) {
        this.coletadescricao = coletadescricao;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Boolean isMarcado(){
        return marcado;
    }

    public void setMarcado(boolean ismarMarcado){
        this.marcado = ismarMarcado;
    }

    public Coleta(){

    }

    public Coleta(long _id, String coletadescricao){
        this._id = _id;
        this.coletadescricao = coletadescricao;
    }


}
