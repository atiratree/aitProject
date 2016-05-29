package ait.utils;

import ait.entity.IdEntity;

/**
 * Created by suomiy on 4/27/16.
 * Object Untill class.
 */
public final class ObjectUtils {

    /**
     * To check if two objects are equals.
     * @param a: first object
     * @param b:second object
     * @return true if euqal and vice-versa.
     */
    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    /**
     * To check if the entity is not null.
     * @param entity: Checking entity.
     */
    public static <E extends IdEntity> void checkNotNull(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity is null!");
        }
    }

    /**
     * To check if the id id not null.
     * @param id: Checking id.
     */
    public static void checkNotNull(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null!");
        }
    }

    /**
     * To check if the id is null.
     * @param id: Checking id.
     */
    public static void checkIsNull(Long id) {
        if (id != null) {
            throw new IllegalArgumentException("id is already set!");
        }
    }

    /**
     * To check id the string is empty.
     * @param a: Checking string.
     * @return: true is null and vice-versa.
     */
    public static boolean isEmpty(String a) {
        return (a == null) || "".equals(a);
    }
}
