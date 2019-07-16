package net.kingbets.cambista.printer;


import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class Utils {



    private static final String[] binaryArray = {
        "0000", "0001", "0010", "0011",
        "0100", "0101", "0110", "0111",
        "1000", "1001", "1010", "1011",
        "1100", "1101", "1110", "1111"
    };



    private static final String HEX_STR = "0123456789ABCDEF";



    static byte[] getSpaceCount(@NonNull String left, @NonNull String right) {

        int currLen = Setup.contentLength - (left.length() + right.length());
        byte[] spaces = new byte[currLen];

        for (int i = 0; i < currLen; i++) {
            spaces[i] = 32;
        }

        return spaces;
    }



    @Nullable
    public static byte[] decodeBitmap(@NonNull Bitmap bmp){

        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        int zeroCount = bmpWidth % 8;
        List<String> list = new ArrayList<>();
        StringBuffer sb;
        String zeroStr = "";

        if (zeroCount > 0) {
            for (int i = 0; i < (8 - zeroCount); i++) {
                zeroStr = zeroStr.concat("0");
            }
        }

        for (int i = 0; i < bmpHeight; i++) {

            sb = new StringBuffer();

            for (int j = 0; j < bmpWidth; j++) {

                int color = bmp.getPixel(j, i);

                int r = (color >> 16) & 0xff;
                int g = (color >> 8) & 0xff;
                int b = color & 0xff;

                if (r > 160 && g > 160 && b > 160) sb.append("0");
                else sb.append("1");
            }

            if (zeroCount > 0) {
                sb.append(zeroStr);
            }

            list.add(sb.toString());
        }

        List<String> bmpHexList = binaryListToHexStringList(list);
        String commandHexString = "1D763000";
        String widthHexString = Integer.toHexString(bmpWidth % 8 == 0 ? bmpWidth / 8 : (bmpWidth / 8 + 1));

        if (widthHexString.length() > 2) {
            Log.e("decodeBitmap error", " width is too large");
            return null;
        }
        else if (widthHexString.length() == 1) {
            widthHexString = "0" + widthHexString;
        }

        widthHexString = widthHexString + "00";

        String heightHexString = Integer.toHexString(bmpHeight);

        if (heightHexString.length() > 2) {
            Log.e("decodeBitmap error", " height is too large");
            return null;
        }
        else if (heightHexString.length() == 1) {
            heightHexString = "0" + heightHexString;
        }

        heightHexString = heightHexString + "00";

        List<String> commandList = new ArrayList<>();
        commandList.add(commandHexString+widthHexString+heightHexString);
        commandList.addAll(bmpHexList);

        return hexList2Byte(commandList);
    }



    private static List<String> binaryListToHexStringList(@NonNull List<String> list) {

        List<String> hexList = new ArrayList<>();

        for (String binaryStr : list) {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < binaryStr.length(); i += 8) {
                String str = binaryStr.substring(i, i + 8);
                String hexString = myBinaryStrToHexString(str);
                sb.append(hexString);
            }

            hexList.add(sb.toString());
        }

        return hexList;
    }



    private static String myBinaryStrToHexString(@NonNull String binaryStr) {

        String hex = "";
        String f4 = binaryStr.substring(0, 4);
        String b4 = binaryStr.substring(4, 8);

        for (int i = 0; i < binaryArray.length; i++) {
            if (f4.equals(binaryArray[i])) hex = hex.concat(HEX_STR.substring(i, i + 1));
        }

        for (int i = 0; i < binaryArray.length; i++) {
            if (b4.equals(binaryArray[i])) hex = hex.concat(HEX_STR.substring(i, i + 1));
        }

        return hex;
    }



    private static byte[] hexList2Byte(@NonNull List<String> list) {

        List<byte[]> commandList = new ArrayList<>();

        for (String hexStr : list) {
            commandList.add(hexStringToBytes(hexStr));
        }

        return sysCopy(commandList);
    }



    private static byte[] hexStringToBytes(String hexString) {

        if (hexString == null || hexString.equals("")) {
            return null;
        }

        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];

        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }

        return d;
    }



    private static byte[] sysCopy(@NonNull List<byte[]> srcArrays) {

        int len = 0;

        for (byte[] srcArray : srcArrays) {
            len += srcArray.length;
        }

        byte[] destArray = new byte[len];
        int destLen = 0;

        for (byte[] srcArray : srcArrays) {
            System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
            destLen += srcArray.length;
        }

        return destArray;
    }



    private static byte charToByte(char c) {
        return (byte) HEX_STR.indexOf(c);
    }
}
