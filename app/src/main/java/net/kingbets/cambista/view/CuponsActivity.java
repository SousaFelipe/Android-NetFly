package net.kingbets.cambista.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.apostas.Cupom;
import net.kingbets.cambista.http.responses.CupomResponse;
import net.kingbets.cambista.utils.Connection;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.adapters.CupomAdapter;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CuponsActivity extends BaseActivity {




    private static String OPERATION = "";



    private Callback callback = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            alertByCode(CuponsActivity.this, 404);
            setLoaderVisibility(false);
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessResponse(response);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupons);
        setLoader(R.id.content_loader);
        inflateSpinner();
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
                    OPERATION = item.toString();
                    request();
                }
            }
        });

        spinner.setSelection(0);
    }

    private void inflateToolbar(int count) {

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Apostas ("+ count +")" );
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void inflateRecyclerView(List<Cupom> cupons) {

        CupomAdapter adapter = new CupomAdapter(cupons);
        adapter.setContext(CuponsActivity.this);
        adapter.setFragmentManager( getSupportFragmentManager() );

        RecyclerView recycler = findViewById(R.id.recycler_cupons);

        if (cupons.size() > 0) {
            recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recycler.setAdapter(adapter);
        }
        else {
            adapter.clear();
        }
    }



    private void request() {
        setLoaderVisibility(true);

        OkHttpClient client = Connection.getClientWithAuthHeader(this);

        Request request = new Request.Builder()
                .url(URL.CUPONS + "listar/" + getPeriodo())
                .build();

        client.newCall(request).enqueue(callback);
    }

    private void proccessResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            if (response.body() != null) {

                final List<Cupom> fnCupons = CupomResponse.receiveMany(response.body().string());

                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        inflateToolbar(fnCupons.size());
                        inflateRecyclerView(fnCupons);
                        setInfoVisibility(fnCupons.size() <= 0);
                    }
                });
            }
            else {
                alert(CuponsActivity.this, R.string.alert_http_response_empty);
            }
        }
        else {
            alertByCode(CuponsActivity.this, response.code());
        }

        setLoaderVisibility(false);
    }



    private String getPeriodo() {
        if (OPERATION.equals("ESTE MÊS")) {
            return "mensal";
        }
        else if(OPERATION.equals("ESTA SEMANA")) {
            return "semanal";
        }
        else {
            return "diario";
        }
    }



    private void setInfoVisibility(boolean visible) {
        findViewById(R.id.content_info_empty).setVisibility( visible ? View.VISIBLE : View.GONE );
    }
}
