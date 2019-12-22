package net.kingbets.cambista.model.contracts;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import net.kingbets.cambista.model.Helper;
import net.kingbets.cambista.model.Perfil;


public class PerfilContract extends BaseContract {



    public PerfilContract(Context context) {
        super(context);
    }



    public boolean isNotEmpty() {
        return super.isNotEmpty(Helper.PERFIS);
    }



    public void insert(Perfil perfil) {
        try {

            ContentValues values = new ContentValues();

            values.put("gerente",        perfil.gerente);
            values.put("nome",           perfil.nome);
            values.put("limite",         perfil.limite);
            values.put("limite_apostas", perfil.limiteApostas);
            values.put("xp",             perfil.xp);
            values.put("xp_maximo",      perfil.xpMaximo);

            if ( !isNotEmpty() ) {
                connect();
                connection.insertOrThrow(Helper.PERFIS, null, values);
            }
            else {
                connect();
                connection.update(Helper.PERFIS, values, "_id = ?", new String[] { String.valueOf(first(true).id) });
            }
        }
        catch (SQLiteException e) {
            Log.e("PERFIL_CONTRACT", "insert: ", e);
        }
        finally {
            disconnect();
        }
    }



    public  Perfil first(/*  NO ARGS  */) { return first( false ); }
    private Perfil first(boolean cascata) {

        if ( ! cascata) {
            connect();
        }

        Perfil cambista = new Perfil();
        Cursor cursor = connection.rawQuery("SELECT * FROM " + Helper.PERFIS, null);

        if (cursor.moveToFirst()) {
            cambista.id  = cursor.getLong(0);
            cambista.gerente = cursor.getString(1);
            cambista.nome = cursor.getString(2);
            cambista.limite = cursor.getDouble(3);
            cambista.limiteApostas = cursor.getInt(4);
            cambista.xp = cursor.getDouble(5);
            cambista.xpMaximo = cursor.getDouble(6);
        }

        cursor.close();

        if ( ! cascata) {
            disconnect();
        }

        return cambista;
    }
}
