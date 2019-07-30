package net.kingbets.cambista.printer;


import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import net.kingbets.cambista.R;


public abstract class Setup {



    public enum Config { NORMAL, LARGE }



    private static final String[] DEVICES = new String[] {
            "Leopardo A8",
            "PT200",
            "RP80-A",
            "BlueTooth Printer"
    };



    static int contentLength;


    public static Commands.FontSize fontSize;
    public static String dashes;
    public static byte[] borders;



    static void set(@NonNull Context context, Config config) {
        Resources res = context.getResources();

        if (config == Config.NORMAL) {
            contentLength = 41;
            fontSize = Commands.FontSize.F12;
            dashes = res.getString(R.string.cupom_header_dashes);
            borders = new byte[] { 32 };
        }
        else {
            contentLength = 44;
            fontSize = Commands.FontSize.F16;
            dashes = res.getString(R.string.cupom_header_dashes_large);
            borders = new byte[] { 32, 32 };
        }
    }



    public static boolean isIn(String deviceOut) {

        for (String deviceIn : DEVICES) {
            if (deviceIn.equals( deviceOut )) {
                return true;
            }
        }

        return false;
    }
}
