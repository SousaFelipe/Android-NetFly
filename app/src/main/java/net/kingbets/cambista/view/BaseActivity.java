package net.kingbets.cambista.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.local.Cambista;
import net.kingbets.cambista.utils.URL;


public abstract class BaseActivity extends AppCompatActivity {



    protected void alert(@NonNull Context context, int resource) {
        alert( context, getResources().getString(resource) );
    }



    protected void alert(@NonNull Context context, String text) {

        final Context fnContext = context;
        final String fnText = text;

        runOnUiThread(new Runnable() {
            @Override public void run() {
                Toast.makeText(fnContext, fnText, Toast.LENGTH_SHORT).show();
            }
        });
    }



    protected void alertByCode(@NonNull Context context, int code) {

        int resourse = 0;

        switch (code) {

            case 0:
                resourse = R.string.alert_http_000;
                break;

            case 404:
                resourse = R.string.alert_http_404;
                break;

            case 401:
                resourse = R.string.alert_http_401;
                break;

            case 512:
                resourse = R.string.alert_http_512;
                break;

            default:
                alert(context, "Erro: " + code);
                break;

        }

        if (resourse != 0) {
            alert(context, resourse);
        }
    }



    protected void switchHttps(@NonNull Context context) {

        final Context fnContext = context;
        final Cambista fnLocal = new CambistaContract(context).first();

        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle( fnLocal.isAutoLogin() ? "Desativação" : "Ativação" );
        dialog.setMessage("Você deseja " + (fnLocal.isHttps() ? "desativar" : "ativar" ) + " o protocolo HTTPS?");

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, fnLocal.isAutoLogin() ? "DESATIVAR" : "ATIVAR", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                https(fnContext, fnLocal);
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCELAR", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    protected void switchAutoLogin(@NonNull Context context) {

        final Context fnContext = context;
        final Cambista fnLocal = new CambistaContract(context).first();

        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle( fnLocal.isAutoLogin() ? "Desativação" : "Ativação" );
        dialog.setMessage("Você deseja " + ( fnLocal.isAutoLogin() ? "desativar" : "ativar" ) + " o login automático?");

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, fnLocal.isAutoLogin() ? "DESATIVAR" : "ATIVAR", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                autoLogin(fnContext, fnLocal);
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCELAR", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    protected void realyCloseApp(@NonNull Context context) {

        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Confirmação!");
        dialog.setMessage("Você realmente deseja encerrar o app?");

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "ENCERRAR", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                finishAndRemoveTask();
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCELAR", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    protected void realyExitApp(@NonNull Context context) {

        final Context fnContext = context;
        final Cambista fnLocal = new CambistaContract(context).first();

        AlertDialog dialog = new AlertDialog.Builder( fnContext ).create();
        dialog.setTitle("Confirmação!");
        dialog.setMessage("Você realmente deseja sair do app?");

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAIR", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                exit(fnContext, fnLocal);
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCELAR", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    private void https(Context context, Cambista cambista) {

        String alert = "Protocolo HTTPS ";

        if (cambista.isHttps()) {
            alert += cambista.disableHttps(context) ? "desativado." : "continua ativo!";
        }
        else {
            alert += cambista.enableHttps(context) ? "ativado." : "continua desativado!";
        }

        alert(context, alert);
        URL.build(context);
    }



    private void autoLogin(Context context, Cambista cambista) {

        String alert = "Login automático ";

        if (cambista.isAutoLogin()) {
            alert += cambista.disableAutoLogin(context) ? "desativado." : "continua ativo!";
        }
        else {
            alert += cambista.enableAutoLogin(context) ? "ativado." : "continua desativado!";
        }

        alert(context, alert);
    }



    private void exit(Context context, Cambista cambista) {

        if (cambista.isAutoLogin()) {
            cambista.disableAutoLogin(context);
        }

        startActivity(new Intent(context, LoginActivity.class));
        finish();
    }
}
