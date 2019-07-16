package net.kingbets.cambista.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.local.Cambista;
import net.kingbets.cambista.model.remote.responses.CampeonatoResponse;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.adapters.CampeonatoAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.annotations.EverythingIsNonNull;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {



    private Callback callback = new Callback() {

        @EverythingIsNonNull
        @Override
        public void onFailure(Call call, IOException e) {
            alertByCode(MainActivity.this, 404);
            setLoaderVisibility(false);
        }

        @EverythingIsNonNull
        @Override
        public void onResponse(Call call, Response response) throws IOException {

            if (response.isSuccessful()) {
                if (response.body() != null) {
                    onSuccessResponse( CampeonatoResponse.receive(response.body().string()) );
                }
                else {
                    alert(MainActivity.this, R.string.alert_http_response_empty);
                }
            }
            else {
                alertByCode(MainActivity.this, response.code());
            }

            setLoaderVisibility(false);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inflateActionDrawer(toolbar);
    }


    @Override
    protected void onStart() {
        super.onStart();
        URL.build(this);
        requestCampeonatos();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_home:
                break;

            case R.id.nav_gallery:
                break;

            case R.id.nav_send:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }



    private void setLoaderVisibility(boolean visible) {

        final boolean fnVisible = visible;

        runOnUiThread(new Runnable() {
            @Override public void run() {
                findViewById(R.id.content_loader).setVisibility( fnVisible ? View.VISIBLE : View.GONE );
            }
        });
    }



    public void inflateActionDrawer(Toolbar toolbar) {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }



    private void requestCampeonatos() {

        setLoaderVisibility(true);

        Cambista local = new CambistaContract(this).first();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url( URL.CAMPEONATOS + "listar/" + local.getWebToken() )
                .build();

        client.newCall(request).enqueue(callback);
    }



    public void onSuccessResponse(CampeonatoResponse response) {

        if (response.body.size() > 0) {
            CampeonatoAdapter adapter = new CampeonatoAdapter(response.body);
            RecyclerView recycler = findViewById(R.id.recycler_campeonatos);
            recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recycler.setAdapter(adapter);
        }
        else {
            alert(this, "Nenhum campeonato encontrado!");
        }
    }
}
