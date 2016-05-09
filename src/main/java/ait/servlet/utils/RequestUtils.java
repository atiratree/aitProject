package ait.servlet.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by suomiy on 5/7/16.
 */
public class RequestUtils {

    public static boolean getBooleanParam(HttpServletRequest request, String name) {
        Object object = request.getAttribute(name);
        return object != null && (Boolean) object;
    }

    public static boolean getBooleanParam(HttpServletRequest request, String name, boolean defautValue) {
        Object object = request.getAttribute(name);
        return object == null ? defautValue : (Boolean) object;
    }

    public static String getStringParam(HttpServletRequest request, String name) {
        Object object = request.getAttribute(name);
        return object == null ? "" : (String) object;
    }
}
