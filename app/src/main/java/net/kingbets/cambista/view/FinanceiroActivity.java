package net.kingbets.cambista.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.Cambista;
import net.kingbets.cambista.http.responses.CaixaResponse;
import net.kingbets.cambista.utils.Config;
import net.kingbets.cambista.utils.Str;
import net.kingbets.cambista.utils.URL;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class FinanceiroActivity extends BaseActivity {



    private static String PERIODO = "";



    private CardView layoutApostas;
    private CardView layoutPremios;

    private TextView txvQuantApostas;

    private TextView txvTotalApurado;
    private TextView txvComissao;

    private TextView txvQuantPremios;
    private TextView txvNomeCambistaLabel;
    private TextView txvTotalPagoCambista;
    private TextView txvTotalPagoFortunna;
    private TextView txvTotalPago;

    private TextView txvDepositar;



    private Callback callback = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            alertByCode(FinanceiroActivity.this, 404);
            setLoaderVisibility(false);
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessResponse(response);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financeiro);
        setLoader(R.id.content_loader);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        inflateSpinner();
    }


    @Override
    protected void onStart() {
        super.onStart();

        layoutApostas = findViewById(R.id.layout_apostas);
        layoutPremios = findViewById(R.id.layout_premios);

        txvQuantApostas         = findViewById(R.id.txv_quant_apostas);
        txvTotalApurado         = findViewById(R.id.txv_total_apurado);
        txvComissao             = findViewById(R.id.txv_comissao);
        txvQuantPremios         = findViewById(R.id.txv_quant_premios);
        txvNomeCambistaLabel    = findViewById(R.id.txv_nome_cambista_label);
        txvTotalPagoCambista    = findViewById(R.id.txv_total_pago_cambista);
        txvTotalPagoFortunna    = findViewById(R.id.txv_total_pago_fortunna);
        txvTotalPago            = findViewById(R.id.txv_total_pago);
        txvDepositar            = findViewById(R.id.txv_depositar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_refresh:

                break;

            case R.id.action_https:
                switchHttps(this);
                break;

            case R.id.action_autologin:
                switchAutoLogin(this);
                break;

            case R.id.action_fechar:
                realyCloseApp(this);
                break;

            case R.id.action_exit:
                realyExitApp(this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    private void inflateSpinner() {

        final ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.spnn_periodo_array, android.R.layout.simple_spinner_dropdown_item
        );

        Spinner spinner = findViewById(R.id.spnn_periodo);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onNothingSelected(AdapterView<?> parent) { }
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Object item = adapter.getItem(position);

                if (item != null) {
                    PERIODO = item.toString();
                    requestResumo();
                }
            }
        });

        spinner.setSelection(0);
    }



    private void requestResumo() {

        setLoaderVisibility(true);

        Cambista cambista = new CambistaContract(this).first();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .addHeader(Config.AUTH_HEADER, cambista.token)
                .url( URL.FINANCEIRO + "resumo/" + cambista.getWebToken() + "/" + getPeriodo() )
                .build();

        client.newCall(request).enqueue(callback);

    }



    private void proccessResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            if (response.body() != null) {

                final String fnBodyString = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        mostrarResumo(CaixaResponse.receive(fnBodyString));
                    }
                });
            }
            else {
                alert(this, R.string.alert_http_response_empty);
            }
        }
        else {
            alertByCode(this, response.code());
        }
    }



    private void mostrarResumo(CaixaResponse response) {
        if (response.code == 200) {

            setLoaderVisibility(false);
            Cambista cambista = new CambistaContract(FinanceiroActivity.this).first();

            txvQuantApostas.setText( String.valueOf(response.body.quantApostas) );
            txvTotalApurado.setText( Str.getCurrency(response.body.entrada) );
            txvComissao.setText( Str.getCurrency(response.body.comissao) );
            txvQuantPremios.setText( String.valueOf(response.body.quant_premios_cambista + response.body.quant_premios_kingbets) );
            txvNomeCambistaLabel.setText( Str.nomeResumido( cambista.nome ) );
            txvTotalPagoCambista.setText( Str.getCurrency(response.body.premios_cambista) );
            txvTotalPagoFortunna.setText( Str.getCurrency(response.body.premios_kingbets) );
            txvTotalPago.setText( Str.getCurrency( response.body.premios_cambista + response.body.premios_kingbets ) );
            txvDepositar.setText( Str.getCurrency( response.body.deposito ) );

        }
        else {
            alert(this, R.string.alert_http_response_empty);
        }
    }



    private String getPeriodo() {
        if (PERIODO.equals("ESTE MÊS")) {
            return "mensal";
        }
        else if(PERIODO.equals("ESTA SEMANA")) {
            return "semanal";
        }
        else {
            return "diario";
        }
    }
}
