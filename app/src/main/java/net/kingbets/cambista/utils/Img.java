package net.kingbets.cambista.utils;


import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;


public abstract class Img {



    public static Drawable getDrawable(Context context, String filename) {

        Drawable drawable;

        try {
            InputStream stream = context.getAssets().open( "img_" + filename + ".png" );
            drawable = Drawable.createFromStream(stream, null);
        }
        catch(IOException ex) {
            return null;
        }

        return drawable;
    }
}
