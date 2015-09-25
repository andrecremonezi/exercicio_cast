package br.com.cast.turmaformacao.exercicio.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.com.cast.turmaformacao.exercicio.util.ApplicationUtil;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "productsdb";
    private static final int DATABASE_VERSION = 1;


    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHelper getIstance(){
        return new DataBaseHelper(ApplicationUtil.getApplicationContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("Task Manager", "Criando Tabela Task");
        db.execSQL(ProductContract.getCreateTableScript());
;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
