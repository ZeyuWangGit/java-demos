package com.exp.context;

import com.exp.pojo.User;

import java.util.Map;

public class TokenContext {
    private static final ThreadLocal<Map<String, Object>> CLAIMS_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(Map<String, Object> claims) {
        CLAIMS_THREAD_LOCAL.set(claims);
    }

    public static Map<String, Object> get() {
        return CLAIMS_THREAD_LOCAL.get();
    }

    public static void remove() {
        CLAIMS_THREAD_LOCAL.remove();
    }

    // Optional helper method to get specific fields
    public static Object get(String key) {
        Map<String, Object> claims = get();
        return claims != null ? claims.get(key) : null;
    }
}
