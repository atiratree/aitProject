package ait.db;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        return fields.stream().collect(LinkedHashMap::new, (m, v) -> {
            try {
                m.put(convertToDbColumn(v.getName()), v.get(object));
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
                        field.set(object, rs.getObject(convertToDbColumn(field.getName())));
                    } catch (SQLException | IllegalAccessException e) {
                        log.severe(e.toString());
                        throw new IllegalStateException(e);
                    }
                }
        );
    }

    private static String convertToDbColumn(String field) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < field.length(); i++) {
            char c = field.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_').append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
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

   /* /**
     * @param object
     * @return map of DB data, LinkedHashMap remembers order of added entries
     */
    /*public static void setColumnsData(Object object, List<?> values) throws DbException {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        for (Field field : getAllFields(object.getClass())) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                Object value = null;

                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                try {
                    value = field.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                result.put(field.getName(), value);
            }
        }

        return result;
    }*/