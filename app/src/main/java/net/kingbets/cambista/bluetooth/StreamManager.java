package net.kingbets.cambista.bluetooth;


import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;

import net.kingbets.cambista.printer.*;


public class StreamManager {



    private OutputStream stream;
    private Context context;



    public StreamManager(@NonNull BluetoothSocket socket) {
        try {
            if (socket.isConnected()) {
                stream = socket.getOutputStream();
            }
            else {
                Log.e(getClass().getSimpleName(), "StreamManager: Socket not connected!");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void setContext(Context context) {
        this.context = context;
    }



    private void flush() {

        if (stream == null) {
            return;
        }

        try {
            stream.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void end() {

        if (stream == null) {
            return;
        }

        try {
            flush();
            stream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void printDashes(int breakLines, boolean borders) {
        printText(Setup.dashes, Commands.Alignment.CENTER, breakLines, borders);
    }



    public void printImage(int resource, int breakLines) {

        try {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resource);

            if (bitmap != null) {
                byte[] bytemap = Utils.decodeBitmap(bitmap);
                printText(bytemap, Commands.Alignment.CENTER, breakLines);
            }
            else {
                Toast.makeText(context, "Erro na impressão da imagem!", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void printText(String message, Commands.Alignment alignment, int breakLines, boolean borders) {

        if (borders) {
            printText(Setup.borders, alignment, breakLines);
        }

        printText(message.getBytes(), alignment, breakLines);
    }



    public void printText(byte[] message, Commands.Alignment alignment, int breakLines) {

        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {

            defineFontSize();
            setAlignment(alignment);

            stream.write(message);

            if (breakLines > 0) {
                breakLine(breakLines);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void breakLine(int breaks) {
        try {
            stream.write(new byte[] { 0x1B, 0x64, (byte) breaks });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void defineFontSize() {
        try {
            switch (Setup.fontSize) {
                case F12:
                    stream.write(new byte[] { 0x1B, 0x21, 0x03 });
                    break;
                case F16:
                    stream.write(new byte[] { 0x1B, 0x21, 0x08 });
                    break;
                case F20:
                    stream.write(new byte[] { 0x1B, 0x21, 0x20 });
                    break;
                case F24:
                    stream.write(new byte[] { 0x1B, 0x21, 0x10 });
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void setAlignment(@NonNull Commands.Alignment alignment) {
        try {
            switch (alignment) {
                case LEFT:
                    stream.write(Commands.ESC_ALIGN_LEFT);
                    break;
                case CENTER:
                    stream.write(Commands.ESC_ALIGN_CENTER);
                    break;
                case RIGHT:
                    stream.write(Commands.ESC_ALIGN_RIGHT);
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
