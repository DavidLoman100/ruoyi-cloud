package com.ruoyi.system.common.context;

/**
 * @author DavidLoman
 * @create 2025-05-18 14:55
 */
public class PermissionContext {
    private static final ThreadLocal<String[]> currentPermissions = new ThreadLocal<>();

    public static void set(String[] perms) {
        currentPermissions.set(perms);
    }

    public static String[] get() {
        return currentPermissions.get();
    }

    public static void clear() {
        currentPermissions.remove();
    }
}
