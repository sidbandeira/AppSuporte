package com.bandeira.sidnei.appleitor.Repositorio;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bandeira.sidnei.appleitor.Classes.ColetaItem;
import com.bandeira.sidnei.appleitor.PersistirColeta.ColetaSQLHelper;

import java.util.ArrayList;
import java.util.List;

public class ColetaItemRepositorio {
    private ColetaSQLHelper helper;

    public ColetaItemRepositorio(Context ctx) {
        helper = new ColetaSQLHelper(ctx);
    }

    private long inserir(ColetaItem item) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ColetaSQLHelper.COLUNA_CODBARRA, item.codbarras);
        cv.put(ColetaSQLHelper.COLUNA_QUANTIDADE, item.quantidade);
        cv.put(ColetaSQLHelper.COLUNA_ITEMIDCOLETA, item.idcoleta);

        long id = db.insert(ColetaSQLHelper.TABELA_COLETAITEM, null, cv);
        if (id != -1) {
            item._id = id;
        }
        db.close();
        return id;
    }

    private int atualizar(ColetaItem item) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ColetaSQLHelper.COLUNA_COLETAITEMID, item._id);
        cv.put(ColetaSQLHelper.COLUNA_CODBARRA, item.codbarras);
        cv.put(ColetaSQLHelper.COLUNA_QUANTIDADE, item.quantidade);
        cv.put(ColetaSQLHelper.COLUNA_ITEMIDCOLETA, item.idcoleta);
        int linhasAfetadas = db.update(
                ColetaSQLHelper.TABELA_COLETAITEM,
                cv,
                ColetaSQLHelper.COLUNA_COLETAITEMID +" = ?",
                new String[]{ String.valueOf(item._id)});
        db.close();
        return linhasAfetadas;
    }

    public void salvar(ColetaItem item) {
        if (item._id == 0) {
            inserir(item);
        } else {
            atualizar(item);
        }
    }

    public int excluir(ColetaItem item) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int linhasAfetadas = db.delete(
                ColetaSQLHelper.TABELA_COLETAITEM,
                ColetaSQLHelper.COLUNA_COLETAITEMID +" = ?",
                new String[]{ String.valueOf(item._id)});
        db.close();
        return linhasAfetadas;
    }

    // EXCLUI TODOS OS ITENS REFERENTES A UMA COLETA
    public int excluirTodosItens(Integer CodColeta) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int linhasAfetadas = db.delete(
                ColetaSQLHelper.TABELA_COLETAITEM,
                ColetaSQLHelper.COLUNA_ITEMIDCOLETA +" = ?",
                new String[]{ String.valueOf(CodColeta)});
        db.close();
        return linhasAfetadas;
    }

    public List<ColetaItem> buscarColeta(String filtro) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM "+ ColetaSQLHelper.TABELA_COLETAITEM;
        String[] argumentos = null;
        if (filtro != null) {
            sql += " WHERE "+ ColetaSQLHelper.COLUNA_ITEMIDCOLETA + " = ?";
            argumentos = new String[]{ filtro };
        }
        sql += " ORDER BY "+ ColetaSQLHelper.COLUNA_COLETAITEMID;
        Cursor cursor = db.rawQuery(sql, argumentos);
        List<ColetaItem> itens = new ArrayList<ColetaItem>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(
                    cursor.getColumnIndex(
                            ColetaSQLHelper.COLUNA_COLETAITEMID));
            Long itemCodigo = Long.parseLong(cursor.getString(cursor.getColumnIndex(ColetaSQLHelper.COLUNA_COLETAITEMID)));
            String itemBarras = cursor.getString(cursor.getColumnIndex(ColetaSQLHelper.COLUNA_CODBARRA));
            Double itemQtde = Double.parseDouble(cursor.getString(cursor.getColumnIndex(ColetaSQLHelper.COLUNA_QUANTIDADE)));
            Long itemIDColeta = Long.parseLong(cursor.getString(cursor.getColumnIndex(ColetaSQLHelper.COLUNA_COLETAID)));
            ColetaItem item = new ColetaItem(itemCodigo,itemBarras,itemQtde,itemIDColeta);
            itens.add(item);
        }
        cursor.close();
        db.close();
        return itens;
    }
}
