package com.bandeira.sidnei.appleitor.PersistirColeta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sidnei on 27/04/2017.
 */

public class ColetaSQLHelper extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "dbcoletor";
    private static final int VERSAO_BANCO = 1;
    public static final String TABELA_COLETA = "coleta";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_COLETADESCRICAO = "coletadescricao";


    public ColetaSQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE "+ TABELA_COLETA +" (" +
                        COLUNA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        COLUNA_COLETADESCRICAO +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}


