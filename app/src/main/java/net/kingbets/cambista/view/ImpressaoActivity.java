package net.kingbets.cambista.view;


import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelUuid;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;

import net.kingbets.cambista.R;
import net.kingbets.cambista.bluetooth.BluetoothManager;
import net.kingbets.cambista.bluetooth.StreamManager;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.local.Cambista;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.apostas.Cupom;
import net.kingbets.cambista.model.responses.CupomResponse;
import net.kingbets.cambista.printer.Printer;
import net.kingbets.cambista.printer.Setup;
import net.kingbets.cambista.utils.DateTime;
import net.kingbets.cambista.utils.Str;
import net.kingbets.cambista.utils.URL;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ImpressaoActivity extends BaseActivity {



    private static final int        REQUEST_ENABLE_BT           = 1;
    private static final int        REQUEST_COARSE_LOCATION     = 2;


    private BluetoothManager bluetoothManager;
    private StreamManager bluetoothStreamManager;
    private boolean printFinalized;



    private String BILHETE;
    private Cupom CUPOM;



    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override public void onReceive(Context context, @NonNull Intent intent) {
            if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction())) {

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();

                if ( name != null && Setup.isIn( name ) ) {
                    bluetoothManager.getAdapter().cancelDiscovery();
                    startConnection(device);
                }
            }
        }
    };



    private Callback callback = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.e(getClass().getSimpleName(), "onFailure: " + e.getMessage());
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessCupom(response);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impressao);
        setLoader(R.id.content_loader);

        Intent intent = getIntent();
        Bundle args = intent.getExtras();

        if (args != null) {
            BILHETE = args.getString("BILHETE");
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        bluetoothManager = new BluetoothManager();
        printFinalized = false;

        buscarCupom();
    }


    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            startFound();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void finish() {

        if (bluetoothManager.isConnected()) {
            bluetoothStreamManager.end();
            bluetoothManager.disconnect();
        }

        if (receiver.isOrderedBroadcast()) {
            unregisterReceiver(receiver);
        }

        super.finish();
    }



    private void buscarCupom() {

        setLoaderVisibility(true);

        Cambista cambista = new CambistaContract(this).first();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url( URL.CUPONS + "buscar/" + cambista.getWebToken() + "/" + BILHETE)
                .build();

        client.newCall(request).enqueue(callback);
    }



    private void proccessCupom(Response response) throws IOException {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                CupomResponse cupomResponse = CupomResponse.receive(response.body().string());
                CUPOM = cupomResponse.body;
                setLoaderVisibility(false);
                print();
            }
            else {
                alert(this, "Erro ao buscar cupom.");
            }
        }
        else {
            alert(this, "Erro ao buscar cupom.");
        }
    }



    private void print() {
        if (bluetoothManager.isEnabled()) {
            startFound();
        }
        else {
            requestEnableBluetooth();
        }
    }



    public void startFound() {

        if ( ! printFinalized) {

            if ( ! bluetoothManager.hasPermission(this)) {
                requestBluetoothPermission();
                if ( bluetoothManager.hasPermission(this)) startFound();
            }
            else {
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(receiver, filter);
                bluetoothManager.getAdapter().startDiscovery();
            }
        }
    }



    private void startConnection(BluetoothDevice device) {

        if (bluetoothManager.getAdapter().isDiscovering()) {
            bluetoothManager.getAdapter().cancelDiscovery();
        }

        if ( ! bluetoothManager.deviceIsPaired(device)) {
            requestPairedBluetooth();
            if (bluetoothManager.deviceIsPaired(device)) startConnection(device);
        }
        else {
            try {

                ParcelUuid uuid0 = device.getUuids()[0];

                bluetoothManager.setSocket(device.createRfcommSocketToServiceRecord(uuid0.getUuid()));
                bluetoothManager.getSocket().connect();

                if (bluetoothManager.isConnected()) {
                    bluetoothStreamManager = new StreamManager(bluetoothManager.getSocket());
                    bluetoothStreamManager.setContext(ImpressaoActivity.this);
                    doActionPrint(device.getName());
                }
                else {
                    showConnectionErrorDialog();
                }
            }
            catch (NullPointerException e) {
                showConnectionErrorDialog();
            }
            catch (IOException e) {
                showConnectionErrorDialog();
            }
        }
    }



    private void doActionPrint(String deviceName) {

        Printer printer = new Printer(ImpressaoActivity.this, bluetoothStreamManager);
        printer.setupDevice(deviceName);

        printer.printHeader();

        Cambista cambista = new CambistaContract(ImpressaoActivity.this).first();

        String dataCupom = DateTime.getInlineDate(DateTime.getDateFromString( CUPOM.data ), "dd/MM/yyyy");
        String horaCupom = DateTime.compact( CUPOM.hora );
        String dataHora = dataCupom + " às " + horaCupom;

        printer.printCodigo( CUPOM.codigo );
        printer.printDataHoraAposta( Str.removeAcentos(dataHora) );

        printer.printCambistaInfo( Str.nomeResumido(cambista.nome), Str.mask(cambista.contato, "(##) # ####-####") );
        printer.printApostador( Str.removeAcentos(CUPOM.apostador) );

        for (Aposta aposta: CUPOM.apostas) {

            String data = DateTime.getInlineDate(DateTime.getDateFromString(aposta.partida.data), "dd/MM/yyyy");
            String inicio = DateTime.compact(aposta.partida.inicio);
            String futebol = "Futebol - " + data + " às " + inicio;
            String equipes = aposta.partida.casa + " x " + aposta.partida.fora;

            printer.printAposta(
                    aposta, Str.removeAcentos(futebol), Str.removeAcentos(" "+aposta.partida.campeonato), Str.removeAcentos(" "+equipes)
            );
        }

        printer.printQuantJogos( String.valueOf(CUPOM.quantApostas) );
        printer.printCotacao( Str.getCurrency(CUPOM.cotacao)  );
        printer.printTotalApostado( Str.getCurrency(CUPOM.valorApostado) );
        printer.printPossivelRetorno( Str.getCurrency(CUPOM.possivelRetorno) );
        printer.printRodape();

        startActivity(new Intent(ImpressaoActivity.this, MainActivity.class));
        finish();
    }



    private void requestEnableBluetooth() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_ENABLE_BT);
        }
        else {
            AlertDialog dialog = new AlertDialog.Builder(this).create();
            dialog.setTitle("Erro de conexão!");
            dialog.setMessage("O bluetooth está desativado!");

            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "ATIVAR", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
                    startActivity(intent);
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



    private void requestBluetoothPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_COARSE_LOCATION
            );
        }
        else {
            AlertDialog dialog = new AlertDialog.Builder(this).create();
            dialog.setTitle("Erro de permissão!");
            dialog.setMessage("É neccessário fornecer as permissões de acesso à sua localização!");

            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "CONFIGURAÇÕES", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
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



    private void requestPairedBluetooth() {

        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("Erro de conexão!");
        dialog.setMessage("Você deve parear o dispositivo com a impressora antes de imprimir!");

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "CONFIGURAÇÕES", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(intent);
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCELAR", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    private void showConnectionErrorDialog() {

        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("Erro de conexão!");
        dialog.setMessage(
                "Verifique se a impressora está ligada.\n"+
                "Cheque as permissões de acesso ao bluetooth.\n" +
                "Verifique se a impressora está pareada com o seu dispositivo."
        );

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "TENTAR NOVAMENTE", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                startFound();
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
