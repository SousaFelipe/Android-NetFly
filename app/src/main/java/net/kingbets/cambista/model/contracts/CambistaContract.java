package net.kingbets.cambista.model.contracts;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import net.kingbets.cambista.model.BaseContract;
import net.kingbets.cambista.model.Helper;
import net.kingbets.cambista.model.local.Cambista;


public class CambistaContract extends BaseContract {



    public CambistaContract(Context context) {
        super(context);
    }



    public boolean isEmpty() {
        return super.isEmpty(Helper.CAMBISTAS);
    }



    public boolean createFirst() {

        connect();

        try{

            ContentValues values = new ContentValues();

            values.put("clean",      Cambista.CLEAN);
            values.put("nome",       "nome");
            values.put("contato",    "contato");
            values.put("email",      "email");
            values.put("senha",      "senha");
            values.put("token",      "token");
            values.put("auto_login", Cambista.AUTO_LOGIN_OFF);
            values.put("https_on",   Cambista.HTTPS_OFF);

            return connection.insertOrThrow(Helper.CAMBISTAS, null, values) > 0;
        }
        catch (SQLiteException e) {
            return false;
        }
        finally {
            disconnect();
        }
    }



    public boolean insert(Cambista cambista) {
        connect();

        try{

            Cambista old = first(true);
            ContentValues values = new ContentValues();

            values.put("clean",     cambista.clean);
            values.put("nome",      cambista.nome);
            values.put("contato",   cambista.contato);
            values.put("email",     cambista.email);
            values.put("senha",     cambista.senha);
            values.put("token",     cambista.token);
            values.put("auto_login",old.auto_login);
            values.put("https_on",  old.https_on);

            return connection.update(Helper.CAMBISTAS, values, "_id=?", new String[] { String.valueOf( old.id ) }) > 0;
        }
        catch (SQLiteException e) {
            return false;
        }
        finally {
            disconnect();
        }
    }



    public boolean updateAutoLogin(long id, int auto_login) {

        connect();

        try {

            ContentValues values = new ContentValues();
            values.put("auto_login", auto_login);

            return connection.update(Helper.CAMBISTAS, values, "_id=?", new String[] { String.valueOf(id) }) > 0;
        }
        catch (SQLiteException e) {
            e.printStackTrace();
        }
        finally {
            disconnect();
        }

        return false;
    }



    public boolean updateHttps(long id, int https_on) {

        connect();

        try {

            ContentValues values = new ContentValues();
            values.put("https_on", https_on);

            return connection.update(Helper.CAMBISTAS, values, "_id=?", new String[] { String.valueOf(id) }) > 0;
        }
        catch (SQLiteException e) {
            return false;
        }
        finally {
            disconnect();
        }
    }



    public  Cambista first(/*  NO ARGS  */) { return first( false ); }
    private Cambista first(boolean cascata) {

        if ( ! cascata) {
            connect();
        }

        Cambista cambista = new Cambista();
        Cursor cursor = connection.rawQuery("SELECT * FROM " + Helper.CAMBISTAS, null);

        if (cursor.moveToFirst()) {
            cambista.id  = cursor.getLong(0);
            cambista.clean = cursor.getInt(1);
            cambista.nome = cursor.getString(2);
            cambista.contato = cursor.getString(3);
            cambista.email = cursor.getString(4);
            cambista.senha = cursor.getString(5);
            cambista.token = cursor.getString(6);
            cambista.auto_login = cursor.getInt(7);
            cambista.https_on = cursor.getInt(8);
        }

        cursor.close();

        if ( ! cascata) {
            disconnect();
        }

        return cambista;
    }
}
