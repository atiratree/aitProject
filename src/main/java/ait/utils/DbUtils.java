package ait.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suomiy on 4/27/16.
 */
public class DbUtils {

    public static List<String> getObjectsColumns(Object object) {
        List<String> fields = new ArrayList<>();

        for (Field field : object.getClass().getFields()) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                fields.add(field.getName());
            }
        }

        return fields;
    }
}
