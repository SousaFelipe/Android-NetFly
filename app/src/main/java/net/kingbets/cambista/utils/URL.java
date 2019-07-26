package net.kingbets.cambista.utils;


import android.content.Context;
import android.support.annotation.NonNull;


public abstract class URL {



    private static String BASE          = "";
    private static String FUTEBOL       = BASE;
    private static String SISTEMA       = BASE;

    public static String APOSTA         = BASE;
    public static String CUPONS         = APOSTA;


    public static String CAMPEONATOS    = FUTEBOL;
    public static String PARTIDAS       = FUTEBOL;
    public static String ODDS           = FUTEBOL;


    public static String CAMBISTAS      = SISTEMA;



    public static void build(@NonNull Context context) {

        //Cambista local = new CambistaContract(context).first();
        //boolean https = (local != null && local.isHttps());

        //BASE = (https ? "https://" : "remote://") + context.getResources().getString(R.string.dominio) + "/api/";
        BASE = "http://192.168.1.102/api/";

        load();
    }



    private static void load() {

        FUTEBOL     = BASE          + "futebol/";
        SISTEMA     = BASE          + "sistema/";
        APOSTA      = BASE          + "aposta/";

        CUPONS      = APOSTA        + "cupons/";

        CAMPEONATOS = FUTEBOL       + "campeonatos/";
        PARTIDAS    = CAMPEONATOS   + "partidas/";
        ODDS        = PARTIDAS      + "odds/";

        CAMBISTAS   = SISTEMA       + "cambistas/";
    }
}
