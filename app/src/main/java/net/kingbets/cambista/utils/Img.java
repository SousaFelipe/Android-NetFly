package net.kingbets.cambista.utils;


import android.content.Context;


public abstract class Img {



    public static int getRoundResourceId(Context context, String filename) {
        return getResourceId(context, filename + "_round");
    }



    public static int getSquareResourceId(Context context, String filename) {
        return getResourceId(context, filename + "_square");
    }



    public static int getResourceId(Context context, String filename) {
        return context.getResources().getIdentifier(filename, "drawable", context.getPackageName());
    }
}
