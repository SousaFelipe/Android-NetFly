package net.kingbets.cambista.model.remote.apostas;


import net.kingbets.cambista.model.BaseModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Aposta extends BaseModel {



    public static final String GANHOU = "G";
    public static final String PERDEU = "P";



    public String   hash;
    public String   tipo;
    public long     partida;
    public long     cupom;
    public double   cotacao;
    public String   status;



    public Aposta(String tipo) {
        this.tipo = tipo;
    }



    public Aposta withPartida(long partida) {
        this.partida  = partida;
        return this;
    }



    public String getHash(int layout, int title, int odd) {

        String plain = layout + ";" + title + ";" + odd;

        try {

            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(plain.getBytes());
            byte[] message = digest.digest();

            StringBuilder builder = new StringBuilder();

            for (byte m: message) {
                String h = Integer.toHexString(0xFF & m);

                while (h.length() < 2) {
                    h = "0" + h;
                }

                builder.append(h);
            }

            return builder.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }
}
