package net.kingbets.cambista.model.contracts;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;

import net.kingbets.cambista.model.BaseContract;
import net.kingbets.cambista.model.Helper;
import net.kingbets.cambista.model.local.Impressora;

import java.util.ArrayList;
import java.util.List;


public class ImpressoraContract extends BaseContract {



    public ImpressoraContract(@NonNull Context context) {
        super(context);
    }



    public boolean isEmpty() {
        return super.isEmpty(Helper.CAMBISTAS);
    }



    public boolean insert(Impressora impressora) {

        connect();

        try{

            ContentValues values = new ContentValues();
            values.put("nome", impressora.nome);
            values.put("address", impressora.address);

            connection.insertOrThrow(Helper.IMPRESSORAS, null, values);
            return true;
        }
        catch (SQLiteException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            disconnect();
        }
    }



    private Impressora first() {

        Impressora impressora = null;

        if ( ! isEmpty()) {
            connect();

            Cursor cursor = connection.rawQuery("SELECT * FROM " + Helper.IMPRESSORAS, null);

            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                impressora = new Impressora();
                impressora.id  = cursor.getLong(0);
                impressora.nome = cursor.getString(1);
                impressora.address = cursor.getString(2);
            }

            cursor.close();
            disconnect();
        }

        return impressora;
    }



    private List<Impressora> list() {

        List<Impressora> impressoras = new ArrayList<>();

        if ( ! isEmpty()) {
            connect();

            Cursor cursor = connection.rawQuery("SELECT * FROM " + Helper.IMPRESSORAS, null);

            if (cursor.moveToFirst()) {
                do {

                    Impressora impressora = new Impressora();
                    impressora.id  = cursor.getLong(0);
                    impressora.nome = cursor.getString(1);
                    impressora.address = cursor.getString(2);

                    impressoras.add(impressora);
                }
                while (cursor.moveToNext());
            }

            cursor.close();
            disconnect();
        }

        return impressoras;
    }
}
