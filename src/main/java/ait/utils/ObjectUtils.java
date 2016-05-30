package ait.utils;

import ait.entity.IdEntity;

/**
 * Created by suomiy on 4/27/16.
 */
public final class ObjectUtils {

    /**
     * @param a first object
     * @param b second object
     * @return true if a equals b
     */
    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    /**
     * @param entity entity
     * @throws IllegalArgumentException if entity is null
     */
    public static <E extends IdEntity> void checkNotNull(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity is null!");
        }
    }

    /**
     * @param id: Checking id.
     * @throws IllegalArgumentException if id is null
     */
    public static void checkNotNull(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null!");
        }
    }

    /**
     * @param id: Checking id.
     * @throws IllegalArgumentException if id is not null
     */
    public static void checkIsNull(Long id) {
        if (id != null) {
            throw new IllegalArgumentException("id is already set!");
        }
    }

    /**
     * @param a string
     * @return true if string is empty
     */
    public static boolean isEmpty(String a) {
        return (a == null) || "".equals(a);
    }
}
