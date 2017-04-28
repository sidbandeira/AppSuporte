package com.bandeira.sidnei.appleitor.PersistirColeta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sidnei on 27/04/2017.
 */

public class ColetaItemSQLHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "dbcoletor";
    private static final int VERSAO_BANCO = 1;
    public static final String TABELA_COLETAITEM = "coletaitem";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_CODBARRA = "codbarra";
    public static final String COLUNA_QUANTIDADE = "quantidade";
    public static final String COLUNA_IDCOLETA = "idcoleta";

    public ColetaItemSQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE "+ TABELA_COLETAITEM +" (" +
                        COLUNA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        COLUNA_CODBARRA     +" TEXT NOT NULL,"+
                        COLUNA_QUANTIDADE +" REAL, +" +
                        COLUNA_IDCOLETA +" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
