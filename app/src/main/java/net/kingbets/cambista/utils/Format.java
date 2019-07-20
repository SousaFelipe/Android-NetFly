package net.kingbets.cambista.utils;

public abstract class Format {



    public static class Time {

        public static String compaact(String time) {
            String[] splTime = time.split(":");
            return splTime[0] + ":" + splTime[1];
        }
    }
}
