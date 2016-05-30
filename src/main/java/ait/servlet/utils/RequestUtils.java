package ait.servlet.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by suomiy on 5/7/16.
 */
public class RequestUtils {

    /**
     * To get the value of boolean parameter from the HttpServletRequest request.
     *
     * @param request      HttpServletRequest request
     * @param name         name of the parameter
     * @param defaultValue the default value for the parameter
     * @return value of the parameter from the request.
     */
    public static boolean getBooleanParam(HttpServletRequest request, String name, boolean defaultValue) {
        Object object = request.getAttribute(name);
        return object == null ? defaultValue : (Boolean) object;
    }

    /**
     * To get the value of boolean parameter from the HttpServletRequest request.
     *
     * @param request HttpServletRequest request
     * @param name    name of the parameter
     * @return value of the parameter from the request.
     */
    public static boolean getBooleanParam(HttpServletRequest request, String name) {
        return getBooleanParam(request, name, false);
    }

    /**
     * To get the value of string parameter from the HttpServletRequest request.
     *
     * @param request      HttpServletRequest request
     * @param name         name of the parameter
     * @param defaultValue default value for the parameter.
     * @return value of the parameter from the request.
     */
    public static String getStringParam(HttpServletRequest request, String name, String defaultValue) {
        Object object = request.getAttribute(name);
        return object == null ? defaultValue : (String) object;
    }

    /**
     * To get the value of string parameter from the HttpServletRequest request.
     *
     * @param request HttpServletRequest request
     * @param name    name of the parameter.
     * @return value of the parameter from the request.
     */
    public static String getStringParam(HttpServletRequest request, String name) {
        return getStringParam(request, name, "");
    }

    /**
     * To get the value of the int parameter from HttpServletRequest request.
     *
     * @param request      HttpServletRequest request
     * @param name         name of the parameter.
     * @param defaultValue default value for the return.
     * @return value of the parameter from the request.
     */
    public static int getIntParam(HttpServletRequest request, String name, int defaultValue) {
        Object object = request.getAttribute(name);
        return object == null ? defaultValue : (int) object;
    }

    /**
     * To get the value of the int parameter from HttpServletRequest request.
     *
     * @param request HttpServletRequest request
     * @param name    name of the parameter.
     * @return value of the parameter from the request.
     */
    public static int getIntParam(HttpServletRequest request, String name) {
        return getIntParam(request, name, 0);
    }
}
