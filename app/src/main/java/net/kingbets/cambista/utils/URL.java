package net.kingbets.cambista.utils;



public abstract class URL {

    private static final String BASE        = "https://www.kingbets.net/api/";
    private static final String FUTEBOL     = BASE        + "futebol/";
    private static final String SISTEMA     = BASE        + "sistema/";

    public static final String  APOSTA      = BASE        + "aposta/";
    public static final String  CUPONS      = APOSTA      + "cupons/";


    public static final String  CAMPEONATOS = FUTEBOL     + "campeonatos/";
    public static final String  PARTIDAS    = CAMPEONATOS + "partidas/";


    public static final String  CAMBISTAS   = SISTEMA     + "cambistas/";
    public static final String  FINANCEIRO  = SISTEMA     + "financeiro/";
}
