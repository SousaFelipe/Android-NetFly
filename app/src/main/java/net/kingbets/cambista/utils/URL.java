package net.kingbets.cambista.utils;



public abstract class URL {

    private static final String BASE        = "https://www.kingbets.net/api/";
    private static final String BASEV2      = "https://www.kingbets.net/api/v2/";

    private static final String FUTEBOL     = BASEV2      + "futebol/";
    private static final String SISTEMA     = BASE        + "sistema/";

    public static final String  APOSTA      = BASEV2      + "aposta/";
    public static final String  CUPONS      = APOSTA      + "cupons/";


    public static final String  CAMPEONATOS = FUTEBOL     + "campeonatos/";
    public static final String  PARTIDAS    = CAMPEONATOS + "partidas/";


    public static final String  CAMBISTAS   = SISTEMA     + "cambistas/";
    public static final String  CLIENTES    = SISTEMA     + "clientes/";
    public static final String  CARTOES     = CLIENTES    + "cartoes/";
    public static final String  FINANCEIRO  = SISTEMA     + "financeiro/";
}
