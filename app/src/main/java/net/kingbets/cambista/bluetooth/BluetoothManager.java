package net.kingbets.cambista.bluetooth;


import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.io.IOException;
import java.util.Set;


public class BluetoothManager {



    private BluetoothAdapter adapter;
    private BluetoothSocket socket;



    public BluetoothManager() {
        adapter = BluetoothAdapter.getDefaultAdapter();
    }



    public boolean deviceIsPaired(BluetoothDevice targetDevice) {

        Set<BluetoothDevice> pairedDevices = getPairedDevices();

        if (pairedDevices != null && pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices)
                if (device.equals(targetDevice)) return true;
        }

        return false;
    }



    public void disable() {

        if (adapter == null)
            return;

        if (isEnabled()) {
            adapter.disable();
        }
    }



    public void disconnect() {

        if (isConnected()) {

            try {
                socket.close();
                socket = null;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public boolean isEnabled() {

        if (adapter == null)
            return false;

        return adapter.isEnabled();
    }



    public boolean isConnected() {

        if (socket == null)
            return false;

        return socket.isConnected();
    }



    public BluetoothAdapter getAdapter() {
        return adapter;
    }



    public BluetoothSocket getSocket() {
        return socket;
    }



    public void setSocket(BluetoothSocket socket) {
        this.socket = socket;
    }



    public boolean hasPermission(Context context) {
        return (
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        );
    }



    private Set<BluetoothDevice> getPairedDevices() {

        if ( ! isEnabled()) {
            return null;
        }

        return adapter.getBondedDevices();
    }
}
