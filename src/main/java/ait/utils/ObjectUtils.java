package ait.utils;

import ait.entity.IdEntity;

/**
 * Created by suomiy on 4/27/16.
 */
public final class ObjectUtils {
    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    public static <E extends IdEntity> void checkNotNull(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity is null!");
        }
    }

    public static void checkNotNull(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null!");
        }
    }

    public static void checkIsNull(Long id) {
        if (id != null) {
            throw new IllegalArgumentException("id is already set!");
        }
    }

    public static boolean isEmpty(String a) {
        return (a == null) || "".equals(a);
    }
}
