package net.kingbets.cambista.model.contracts;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;

import net.kingbets.cambista.model.Helper;


public abstract class BaseContract {


    private Context context;
    private Helper dbHelper;



    protected SQLiteDatabase connection;



    BaseContract(@NonNull Context context) {
        this.context = context;
    }



    boolean isNotEmpty(@NonNull String table) {

        connect();
        Cursor cursor = connection.rawQuery("SELECT * FROM " + table, null);

        boolean notEmpty = (cursor.getCount() > 0);

        cursor.close();
        disconnect();

        return notEmpty;
    }



    void connect() {
        try {
            if (dbHelper == null) {
                dbHelper = new Helper(context);
            }

            if (connection == null || !connection.isOpen()) {
                connection = dbHelper.getWritableDatabase();
            }
        }
        catch (SQLiteException e) {
            e.printStackTrace();
        }
    }



    void disconnect() {
        try {
            if (connection != null && connection.isOpen()) {
                connection.close();
                connection = null;
            }

            if (dbHelper != null) dbHelper.close();
        }
        catch (SQLiteException e) {
            e.printStackTrace();
        }
    }
}
