package net.kingbets.cambista.alerts;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;


public abstract class PermissionAlert {



    public static void show(@NonNull Context context) {

        final Context fnContext = context;

        AlertDialog dialog = new AlertDialog.Builder(fnContext).create();
        dialog.setTitle("Erro de Permissão!");
        dialog.setMessage("É necessário conceder todas as permissões para que o APP funcione corretamente!");

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "CONFIGURAÇÕES", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_SETTINGS);
                fnContext.startActivity(intent);
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCELAR", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
