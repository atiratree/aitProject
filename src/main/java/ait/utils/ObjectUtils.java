package ait.utils;

/**
 * Created by suomiy on 4/27/16.
 */
public final class ObjectUtils {
    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }
}
