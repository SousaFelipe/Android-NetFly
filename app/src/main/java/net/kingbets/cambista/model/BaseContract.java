package net.kingbets.cambista.model;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;


public abstract class BaseContract {


    private Context context;
    private Helper dbHelper;



    protected SQLiteDatabase connection;



    protected BaseContract(@NonNull Context context) {
        this.context = context;
    }



    protected boolean isEmpty(@NonNull String table) {
        connect();
        boolean empty = false;

        Cursor cursor = connection.rawQuery("SELECT * FROM " + table, null);

        if (cursor.getCount() <= 0) {
            empty = true;
        }

        cursor.close();
        disconnect();
        return empty;
    }



    protected void connect() {
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



    protected void disconnect() {
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
