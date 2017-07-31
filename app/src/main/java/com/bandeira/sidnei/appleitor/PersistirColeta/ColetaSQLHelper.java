package com.bandeira.sidnei.appleitor.PersistirColeta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sidnei on 27/04/2017.
 */

public class ColetaSQLHelper extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "dbcoleta";
    private static final int VERSAO_BANCO = 1;

    // VARIAVEIS DA TABELA COLETA
    public static final String TABELA_COLETA = "coleta";
    public static final String COLUNA_COLETAID = "_id";
    public static final String COLUNA_COLETADESCRICAO = "coletadescricao";


    // VARIAVEIS DA TABELA COLETA ITEM
    public static final String TABELA_COLETAITEM = "coletaitem";
    public static final String COLUNA_COLETAITEMID = "_id";
    public static final String COLUNA_CODBARRA = "codbarra";
    public static final String COLUNA_QUANTIDADE = "quantidade";
    public static final String COLUNA_IDCOLETA = "idcoleta";

    public ColetaSQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //CRIA A TABELA COLETA
        sqLiteDatabase.execSQL(
                "CREATE TABLE "+ TABELA_COLETA +" (" +
                        COLUNA_COLETAID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        COLUNA_COLETADESCRICAO +" TEXT)");

        //CRIA A TABELA COLETAITEM
        sqLiteDatabase.execSQL(
                "CREATE TABLE "+ TABELA_COLETAITEM +" (" +
                        COLUNA_COLETAITEMID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        COLUNA_CODBARRA +" TEXT NOT NULL,"+
                        COLUNA_QUANTIDADE +" REAL,"+
                        COLUNA_IDCOLETA +" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}


