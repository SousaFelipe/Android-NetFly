package net.kingbets.cambista.printer;


import android.content.Context;

import net.kingbets.cambista.bluetooth.StreamManager;


public class Printer {



    private Context context;
    private StreamManager streamManager;



    public Printer(Context context, StreamManager streamManager) {
        this.context = context;
        this.streamManager = streamManager;
    }
}
