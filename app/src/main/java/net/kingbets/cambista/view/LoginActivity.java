package net.kingbets.cambista.view;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.local.Cambista;
import net.kingbets.cambista.utils.Connection;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.model.responses.CambistaResponse;

import java.io.IOException;


public class LoginActivity extends BaseActivity {



    private EditText inputEmail;
    private EditText inputSenha;



    private Callback callback = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            alertByCode(LoginActivity.this, 404);
            setLoaderVisibility(false);
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessResponse(response);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setLoader(R.id.content_loader);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputEmail = findViewById(R.id.input_email);
        inputSenha = findViewById(R.id.input_senha);
    }


    @Override
    protected void onStart() {
        super.onStart();

        Connection.check(this);
        CambistaContract contract = new CambistaContract(this);

        if (contract.isEmpty()) {
            String alert = contract.createFirst() ? "Criando banco de dados..." : "Erro no banco de dados!";
            alert(this, alert);
        }

        URL.build(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        Cambista local = new CambistaContract(this).first();

        if (local.isAutoLogin()) {
            login(local.email, local.senha);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_https:
                switchHttps(this);
                break;

            case R.id.action_fechar:
                realyCloseApp(this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    private void proccessResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                login(CambistaResponse.receive(response.body().string()));
            }
            else {
                setLoaderVisibility(false);
                alert(LoginActivity.this, R.string.alert_http_response_empty);
            }
        }
        else {
            setLoaderVisibility(false);
            alertByCode(LoginActivity.this, response.code());
        }
    }



    public void entrar(@NonNull View view) {
        if (validateInputs()) {

            String email = inputEmail.getText().toString().toLowerCase().trim();
            String senha = inputSenha.getText().toString().toLowerCase().trim();

            login(email, senha);
        }
    }



    private void login(@NonNull String email, @NonNull String senha) {

        setLoaderVisibility(true);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url( URL.CAMBISTAS + "login/" + email + "/" + senha)
                .build();

        client.newCall(request).enqueue(callback);
    }



    private void login(CambistaResponse response) {

        if (response.code == 200) {

            CambistaContract contract = new CambistaContract(this);
            Cambista local = contract.first();

            if (local.isClean()) {

                if (contract.insert( response.body )) {
                    check();
                }
                else {
                    setLoaderVisibility(false);
                    alert(this, R.string.alert_auth_error);
                }

            }
            else {
                check();
            }
        }
        else {
            setLoaderVisibility(false);
            alertByCode(this, response.code);
        }
    }



    private void check() {
        startActivity(new Intent(LoginActivity.this, CheckActivity.class));
        finish();
    }



    private boolean validateInputs() {

        if (inputEmail == null || inputEmail.getText() == null || inputEmail.getText().toString().length() <= 0) {
            alert(this, R.string.alert_email_empty);
            return false;
        }

        if (inputSenha == null || inputSenha.getText() == null || inputSenha.getText().toString().length() <= 0) {
            alert(this, R.string.alert_password_empty);
            return false;
        }

        return true;
    }
}
