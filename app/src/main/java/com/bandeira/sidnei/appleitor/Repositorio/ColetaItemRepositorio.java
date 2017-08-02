package com.bandeira.sidnei.appleitor.Repositorio;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bandeira.sidnei.appleitor.Classes.ColetaItem;
import com.bandeira.sidnei.appleitor.PersistirColeta.ColetaSQLHelper;

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
        cv.put(ColetaSQLHelper.COLUNA_IDCOLETA, item.idcoleta);

        long id = db.insert(ColetaSQLHelper.TABELA_COLETAITEM, null, cv);
        if (id != -1) {
            item._id = id;
        }
        db.close();
        return id;
    }


}
