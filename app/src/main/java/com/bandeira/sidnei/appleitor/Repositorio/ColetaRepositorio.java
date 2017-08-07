package com.bandeira.sidnei.appleitor.Repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bandeira.sidnei.appleitor.Classes.Coleta;
import com.bandeira.sidnei.appleitor.PersistirColeta.ColetaSQLHelper;

import java.util.ArrayList;
import java.util.List;

public class ColetaRepositorio {
    private ColetaSQLHelper helper;

    public ColetaRepositorio(Context ctx) {
        helper = new ColetaSQLHelper(ctx);
    }

    private long inserir(Coleta coleta) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ColetaSQLHelper.COLUNA_COLETADESCRICAO, coleta.coletadescricao);

        long id = db.insert(ColetaSQLHelper.TABELA_COLETA, null, cv);
        if (id != -1) {
            coleta._id = id;
        }
        db.close();
        return id;
    }

    private int atualizar(Coleta coleta) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ColetaSQLHelper.COLUNA_ITEMIDCOLETA, coleta._id);
        cv.put(ColetaSQLHelper.COLUNA_COLETADESCRICAO, coleta.coletadescricao);
        int linhasAfetadas = db.update(
                ColetaSQLHelper.TABELA_COLETA,
                cv,
                ColetaSQLHelper.COLUNA_COLETAID +" = ?",
                new String[]{ String.valueOf(coleta._id)});
        db.close();
        return linhasAfetadas;
    }

    public void salvar(Coleta coleta) {
        if (coleta._id == 0) {
            inserir(coleta);
        } else {
            atualizar(coleta);
        }
    }

    public int excluir(Coleta coleta) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int linhasAfetadas = db.delete(
                ColetaSQLHelper.TABELA_COLETA,
                ColetaSQLHelper.COLUNA_ITEMIDCOLETA +" = ?",
                new String[]{ String.valueOf(coleta._id)});
        db.close();
        return linhasAfetadas;
    }

    public List<Coleta> buscarColeta(String filtro) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM "+ ColetaSQLHelper.TABELA_COLETA;
        String[] argumentos = null;
        if (filtro != null) {
            sql += " WHERE "+ ColetaSQLHelper.COLUNA_COLETADESCRICAO +" LIKE ?";
            argumentos = new String[]{ filtro };
        }
        sql += " ORDER BY "+ ColetaSQLHelper.COLUNA_COLETAID;
        Cursor cursor = db.rawQuery(sql, argumentos);
        List<Coleta> coletas = new ArrayList<Coleta>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(ColetaSQLHelper.COLUNA_COLETAID));
            String coletaDescricao = cursor.getString(cursor.getColumnIndex(ColetaSQLHelper.COLUNA_COLETADESCRICAO));
            Coleta coleta = new Coleta(id, coletaDescricao);
            coletas.add(coleta);
        }
        cursor.close();
        db.close();
        return coletas;
    }
}

