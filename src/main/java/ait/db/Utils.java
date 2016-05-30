package ait.db;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by suomiy on 4/27/16.
 */
public class Utils {
    private static final Logger log = Logger.getLogger(Manager.class.getName());

    /**
     * @param object
     * @return map of DB data, LinkedHashMap remembers order of added entries
     */
    public static LinkedHashMap<String, Object> getColumnsData(Object object) {
        List<Field> fields = getAllDbFieldsAsAccessible(object.getClass());

        return fields.stream().collect(LinkedHashMap::new, (m, field) -> {
            try {
                Object entry = convertToDbEntry(field, field.get(object));
                String entryName = convertToDbString(field.getName());

                m.put(entryName, entry);
            } catch (IllegalAccessException e) {
                log.severe(e.toString());
                throw new IllegalStateException(e);
            }
        }, LinkedHashMap::putAll);
    }

    public static void setObjectFromResultSet(ResultSet rs, Object object) {
        List<Field> fields = getAllDbFieldsAsAccessible(object.getClass());

        fields.forEach(field -> {
                    try {
                        Object entry = rs.getObject(convertToDbString(field.getName()));
                        entry = convertFromDbEntry(field, entry);

                        field.set(object, entry);
                    } catch (SQLException | IllegalAccessException e) {
                        log.severe(e.toString());
                        throw new IllegalStateException(e);
                    }
                }
        );
    }

    private static Object convertFromDbEntry(Field field, Object value) {
        Object result;

        if (field.getType().isEnum()) {
            Object[] enumConstants = field.getType().getEnumConstants();
            result = enumConstants[(int) value];
        } else if (field.getType() == OffsetDateTime.class && value instanceof Timestamp) {// in db as TIMESTAMP WITH TIME ZONE
            result = OffsetDateTime.parse(((Timestamp) value).toInstant().toString());
        } else if ((field.getType() == Double.class || field.getType() == double.class) && value instanceof BigDecimal) {
            result = ((BigDecimal) value).doubleValue();
        } else if ((field.getType() == Float.class || field.getType() == float.class) && value instanceof BigDecimal) {
            result = ((BigDecimal) value).floatValue();
        } else {
            result = value;
        }

        return result;
    }

    private static Object convertToDbEntry(Field field, Object value) throws IllegalAccessException {
        Object result;

        if (field.getType().isEnum()) {
            Enum<? extends Enum> anEnum = (Enum<? extends Enum>) value;
            result = anEnum.ordinal();
        } else {
            result = value;
        }

        return result;
    }

    public static String convertToDbString(String field) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < field.length(); i++) {
            char c = field.charAt(i);
            boolean upper = Character.isUpperCase(c);
            char result;

            if (i == 0) {
                result = upper ? Character.toLowerCase(c) : c;
            } else if (upper) {
                sb.append('_');
                result = Character.toLowerCase(c);
            } else {
                result = c;
            }
            sb.append(result);
        }

        return sb.toString();
    }

    private static List<Field> getAllDbFieldsAsAccessible(Class<?> currentClass) {
        List<Field> fields = getAllFields(currentClass);

        return fields.stream().filter(field -> field.getAnnotation(Column.class) != null).map(field -> {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field;
        }).collect(Collectors.toList());
    }

    private static List<Field> getAllFields(Class<?> currentClass) {
        List<Field> fields = new ArrayList<>();
        do {
            fields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
            currentClass = currentClass.getSuperclass();
        } while (currentClass != null);

        return fields;
    }
}