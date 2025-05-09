package com.exp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for generating and verifying MD5 hashes.
 */
public class Md5Util {

    // Hexadecimal characters used in MD5 output
    protected static final char[] hexDigits = "0123456789abcdef".toCharArray();

    protected static MessageDigest messageDigest = null;

    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(Md5Util.class.getName() + ": Initialization failed, MD5 not supported.");
            e.printStackTrace();
        }
    }

    /**
     * Generate MD5 hash of a string.
     *
     * @param s the input string
     * @return 32-character MD5 hex string
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * Generate MD5 hash from a byte array.
     *
     * @param bytes input byte array
     * @return MD5 hash string
     */
    public static String getMD5String(byte[] bytes) {
        messageDigest.update(bytes);
        byte[] digest = messageDigest.digest();
        return byteArrayToHexString(digest);
    }

    /**
     * Compare a raw password with a known MD5 hash.
     *
     * @param password the raw password
     * @param md5Str   the expected MD5 string
     * @return true if they match, false otherwise
     */
    public static boolean checkPassword(String password, String md5Str) {
        String md5 = getMD5String(password);
        return md5.equalsIgnoreCase(md5Str);
    }

    /**
     * Convert a byte array to a hexadecimal string.
     *
     * @param bytes input byte array
     * @return hex string
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int val = b & 0xff;
            sb.append(hexDigits[val >>> 4]);
            sb.append(hexDigits[val & 0x0f]);
        }
        return sb.toString();
    }
}