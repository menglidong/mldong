package com.mldong.common.dauth;

public class DataScopeHelper {
    private static final ThreadLocal<String> LOCAL_DATA_AUTH_SQL = new ThreadLocal();
    public static void setLocalDataAuthSql(String page) {
        LOCAL_DATA_AUTH_SQL.set(page);
    }

    public static String getLocalDataAuthSql() {
        return LOCAL_DATA_AUTH_SQL.get();
    }

    public static void clearDataAuthSql() {
        LOCAL_DATA_AUTH_SQL.remove();
    }
}
