package br.com.alexcosta.labimagem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by acostas on 13/07/16.
 */
public class DataBaseBroadCReceiver  extends SQLiteOpenHelper {

    public DataBaseBroadCReceiver(Context context) {
        super(context, "LABIMAGEM", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( ScriptSQL.criaTabelaLogin());
        db.execSQL( ScriptSQL.criaTabelaPacientes());
        db.execSQL( ScriptSQL.criaTabelaLaudos());
        db.execSQL( ScriptSQL.criaTabelaTpExames());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
