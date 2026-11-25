package com.sharath.devops.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Simple JDBC connection helper. Configure via environment variables. */
public final class DBUtil {

    private static final String URL = env("DB_URL",
            "jdbc:mysql://localhost:3306/sharath_institute?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
    private static final String USER = env("DB_USER", "root");
    private static final String PASSWORD = env("DB_PASSWORD", "root");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private DBUtil() { }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static String env(String key, String def) {
        String v = System.getenv(key);
        return (v == null || v.isEmpty()) ? def : v;
    }
}
