package ait.servlet.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by suomiy on 5/7/16.
 */
public class RequestUtils {

    public static boolean getBooleanParam(HttpServletRequest request, String name, boolean defaultValue) {
        Object object = request.getAttribute(name);
        return object == null ? defaultValue : (Boolean) object;
    }

    public static boolean getBooleanParam(HttpServletRequest request, String name) {
        return getBooleanParam(request, name, false);
    }

    public static String getStringParam(HttpServletRequest request, String name, String defaultValue) {
        Object object = request.getAttribute(name);
        return object == null ? defaultValue : (String) object;
    }

    public static String getStringParam(HttpServletRequest request, String name) {
        return getStringParam(request, name, "");
    }

    public static int getIntParam(HttpServletRequest request, String name, int defaultValue) {
        Object object = request.getAttribute(name);
        return object == null ? defaultValue : (int) object;
    }

    public static int getIntParam(HttpServletRequest request, String name) {
        return getIntParam(request, name, 0);
    }
}
