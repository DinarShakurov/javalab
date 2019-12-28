package ru.shakurov.webapp_javalab.utils;

public class CheckAccess {
    public static void allowUser(String role) {
        if (isUser(role))
            throw new IllegalStateException("Forbidden");
    }

    public static void allowAdmin(String role) {
        if (isAdmin(role))
            throw new IllegalStateException("Forbidden");
    }

    public static void allowUserAndAdmin(String role) {
        if (!isUser(role) && !isAdmin(role))
            throw new IllegalStateException("Forbidden");

    }

    public static boolean isUser(String role) {
        return role.equals("user");
    }

    public static boolean isAdmin(String role) {
        return role.equals("admin");
    }
}
