package net.kingbets.cambista.model.local;


import android.content.Context;
import android.support.annotation.NonNull;

import net.kingbets.cambista.model.BaseModel;
import net.kingbets.cambista.model.contracts.CambistaContract;


public class Cambista extends BaseModel {



    public static final int CLEAN = 1;

    public static final int AUTO_LOGIN_OFF  = 0;
    private static final int AUTO_LOGIN_ON  = 1;

    public static final int HTTPS_OFF   = 0;
    private static final int HTTPS_ON   = 1;



    public int clean;
    public String nome;
    public String contato;
    public String email;
    public String senha;
    public String token;
    public int auto_login;
    public int https_on;




    public String firstName() {
        String[] names = this.nome.split(" ");
        return  names[0];
    }



    public String getWebToken() {
        return token.replace("/", ";barra;");
    }



    public boolean isClean() {
        return this.clean == CLEAN;
    }



    public boolean isAutoLogin() {
        return this.auto_login == AUTO_LOGIN_ON;
    }



    public boolean isHttps() {
        return this.https_on == HTTPS_ON;
    }



    public boolean enableHttps(@NonNull Context context) {
        CambistaContract contract = new CambistaContract(context);
        return contract.updateHttps(this.id, HTTPS_ON);
    }



    public boolean disableHttps(@NonNull Context context) {
        CambistaContract contract = new CambistaContract(context);
        return contract.updateHttps(this.id, HTTPS_OFF);
    }



    public boolean enableAutoLogin(@NonNull Context context) {
        CambistaContract contract = new CambistaContract(context);
        return contract.updateAutoLogin(this.id, AUTO_LOGIN_ON);
    }



    public boolean disableAutoLogin(@NonNull Context context) {
        CambistaContract contract = new CambistaContract(context);
        return contract.updateAutoLogin(this.id, AUTO_LOGIN_OFF);
    }
}
