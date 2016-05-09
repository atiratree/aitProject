package ait.db;

import java.util.ArrayList;
import java.util.List;

import static ait.db.Tables.IdEntity.ID_COLUMN;

/**
 * Created by suomiy on 5/2/16.
 */
public class ConditionBuilder {
    private final String IS_EQUAL = " = ";
    private final String NOT_EQUAL = " <> ";
    private final String IS_LIKE = " LIKE ";

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
            queryArgs.add(value);
        }

        return this;
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
