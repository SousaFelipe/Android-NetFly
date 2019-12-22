package net.kingbets.cambista.model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Helper extends SQLiteOpenHelper {



    private static final String DB_NAME     = "kingbets.cambista";
    private static final int    DB_VERSION  = 1;


    public static final String CAMBISTAS    = "cambistas";
    public static final String PERFIS       = "perfis";
    public static final String IMPRESSORAS  = "impressoras";



    public Helper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + CAMBISTAS + " ("
                        +"_id           INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                        +"clean         INTEGER NOT NULL, "
                        +"nome          INTEGER NOT NULL, "
                        +"contato       TEXT    NOT NULL, "
                        +"email         TEXT    NOT NULL, "
                        +"senha         TEXT    NOT NULL, "
                        +"token         TEXT    NOT NULL, "
                        +"auto_login    INTEGER NOT NULL, "
                        +"https_on      INTEGER NOT NULL);"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + PERFIS + " ("
                        +"_id               INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                        +"gerente           TEXT, "
                        +"nome              TEXT    NOT NULL, "
                        +"limite            DOUBLE  NOT NULL, "
                        +"limite_apostas    INTEGER NOT NULL, "
                        +"xp                DOUBLE, "
                        +"xp_maximo         DOUBLE );"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + IMPRESSORAS + " (" +
                        "_id        INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "nome       TEXT    NOT NULL, " +
                        "address    TEXT    NOT NULL);"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CAMBISTAS      + ";");
        db.execSQL("DROP TABLE IF EXISTS " + PERFIS         + ";");
        db.execSQL("DROP TABLE IF EXISTS " + IMPRESSORAS    + ";");

        onCreate(db);
    }
}
