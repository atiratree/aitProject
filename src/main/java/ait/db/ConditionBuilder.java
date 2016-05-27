package ait.db;

import java.util.ArrayList;
import java.util.List;

import static ait.db.Tables.IdEntity.ID_COLUMN;

/**
 * Created by suomiy on 5/2/16.
 */
public class ConditionBuilder {
    public final String IS_EQUAL = " = ";
    public final String NOT_EQUAL = " <> ";
    public final String IS_LIKE = " LIKE ";
    public final String GREATER_OR_EQUAL = " >= ";
    public final String SMALLER_OR_EQUAL = " <= ";

    private StringBuilder querySb = new StringBuilder();
    private List<Object> queryArgs = new ArrayList<>();
    private String limit = "";

    private String query = "";

    public ConditionBuilder id(Long value) {
        return where(ID_COLUMN, value);
    }

    public ConditionBuilder whereLike(String columnName, Object value) {
        return where(columnName, value, IS_LIKE);
    }

    public ConditionBuilder where(String columnName, Object value) {
        return where(columnName, value, IS_EQUAL);
    }

    public ConditionBuilder whereNotEqual(String columnName, Object value) {
        return where(columnName, value, NOT_EQUAL);
    }

    public ConditionBuilder where(String columnName, Object value, String relation) {
        assert !columnName.equals("") : "columnName cannot be empty or null";

        if (querySb.length() > 0) {
            querySb.append("AND ");
        }

        querySb.append(columnName);

        if (value == null) {
            querySb.append(" IS NULL ");
        } else {
            querySb.append(relation).append("? ");
            addToArgs(value);
        }

        return this;
    }

    public ConditionBuilder in(String columnName, List<?> objects) {

        if (querySb.length() > 0) {
            querySb.append("AND ");
        }

        querySb.append(columnName);

        if (objects == null || objects.size() == 0) {
            querySb.append(" IS NULL ");
        } else {
            querySb.append(" IN (");
            for (int i = 0; i < objects.size(); i++) {
                querySb.append("?");
                querySb.append((objects.size() - 1 == i) ? ") " : ", ");

                addToArgs(objects.get(i));
            }
        }

        return this;
    }

    private void addToArgs(Object object) {
        Object result;
        if (object.getClass().isEnum()) {
            Enum<? extends Enum> anEnum = (Enum<? extends Enum>) object;
            result = anEnum.ordinal();
        } else {
            result = object;
        }

        queryArgs.add(result);

    }

    public ConditionBuilder limit(int limit) {
        this.limit = "LIMIT " + limit;
        return this;
    }

    public List<Object> getConditionArgs() {
        return queryArgs;
    }

    public String getCondition() {
        return querySb.append(limit).toString();
    }
}
