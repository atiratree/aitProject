package ait.db;

import ait.entity.IdEntity;
import com.rits.cloning.Cloner;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static ait.db.Tables.IdEntity.ID_COLUMN;
import static ait.utils.ObjectUtils.checkIsNull;
import static ait.utils.ObjectUtils.checkNotNull;


/**
 * Created by suomiy on 4/27/16.
 */
public class Manager<E extends IdEntity> {
    private static final Logger log = Logger.getLogger(Manager.class.getName());

    private Class<E> clazz;

    private final String TABLE;
    private final String CLASS_NAME;

    public Manager(Class<E> clazz) {
        this.clazz = clazz;
        CLASS_NAME = clazz.getSimpleName();
        TABLE = Utils.convertToDbString(CLASS_NAME);
    }

    public E create(E entity) throws DbException {
        checkNotNull(entity);
        checkIsNull(entity.getId());

        E result = (new Cloner()).deepClone(entity);

        LinkedHashMap<String, Object> columnsData = Utils.getColumnsData(result);
        String createQuery = buildCreateQuery(columnsData.keySet());
        List<Object> columnValues = new ArrayList<>(columnsData.entrySet()).stream()
                .filter(e -> !e.getKey().equals(ID_COLUMN))
                .map(e -> e.getValue())
                .collect(Collectors.toList());

        try (Connection con = Database.getConnection();
             PreparedStatement statement = con.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            setStatementValues(statement, columnValues);
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                result.setId(rs.getLong(ID_COLUMN));
            } else {
                throw new DbException("Creating object" + CLASS_NAME + " failed, no generated key obtained.");
            }
        } catch (SQLException ex) {
            throw new DbException(ex);
        }

        return result;
    }


    public void update(E entity) throws DbException {
        //TODO:
        throw new NotImplementedException();
    }

    public void delete(E entity) throws DbException {
        checkNotNull(entity);
        checkNotNull(entity.getId());

        ConditionBuilder conditionBuilder = new ConditionBuilder().id(entity.getId());
        String deleteQuery = buildDeleteQuery(conditionBuilder);

        try (Connection con = Database.getConnection();
             PreparedStatement statement = con.prepareStatement(deleteQuery)) {
            setStatementValues(statement, conditionBuilder.getConditionArgs());

            if (statement.executeUpdate() > 1) {
                log.warning("Deleted more entities than one in " + CLASS_NAME);
            }
        } catch (SQLException ex) {
            throw new DbException(ex);
        }
    }


    public E findOne(E entity) {
        checkNotNull(entity);
        return findOne(entity.getId());
    }

    public E findOne(Long id) {
        checkNotNull(id);

        ConditionBuilder conditionBuilder = new ConditionBuilder().id(id);
        return findOne(conditionBuilder);
    }

    public E findOne(ConditionBuilder conditionBuilder) {
        ConditionBuilder cb = (new Cloner()).deepClone(conditionBuilder);
        return find(cb.limit(1)).stream().findFirst().orElse(null);
    }

    public List<E> findAll() {
        return find(null);
    }

    public List<E> find(ConditionBuilder conditionBuilder) {
        String findQuery = buildSelectQuery(conditionBuilder);
        List<E> result = new ArrayList<>();

        try (Connection con = Database.getConnection();
             PreparedStatement statement = con.prepareStatement(findQuery)) {
            setStatementValues(statement, conditionBuilder == null ? null : conditionBuilder.getConditionArgs());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                try {
                    E newObject = clazz.newInstance();
                    Utils.setObjectFromResultSet(rs, newObject);
                    result.add(newObject);
                } catch (Exception e) {
                    log.severe("Could not instantiate " + CLASS_NAME);
                }
            }
        } catch (Exception ex) {
            log.severe(ex.toString());
        }

        return result;
    }

    private void setCreateStatementValues(PreparedStatement statement, List<Map.Entry<String, Object>> entryList) throws SQLException {
        int valueCounter = 1;

        for (int i = 0; i < entryList.size(); i++) {
            Map.Entry<String, Object> entry = entryList.get(i);
            if (!entry.getKey().equals(ID_COLUMN)) {
                statement.setObject(valueCounter++, entry.getValue());
            }
        }
    }

    private void setStatementValues(PreparedStatement statement, List<?> entryList) throws SQLException {
        if(entryList == null){
            return;
        }

        for (int i = 0; i < entryList.size(); i++) {
            statement.setObject(i + 1, entryList.get(i));
        }
    }


    private String buildDeleteQuery(ConditionBuilder conditionBuilder) {
        return String.format("DELETE FROM %s.%s WHERE %s", Tables.SCHEMA_NAME, TABLE, conditionBuilder.getCondition());
    }

    private String buildSelectQuery(ConditionBuilder conditionBuilder) {
        String condition = "";
        if (conditionBuilder != null && conditionBuilder.getConditionArgs().size() > 0) {
            condition = String.format(" WHERE %s", conditionBuilder.getCondition());
        }
        return String.format("SELECT * FROM %s.%s%s", Tables.SCHEMA_NAME, TABLE, condition);
    }


    /**
     * Creates sql statement similar to this "INSERT INTO public.user(id, name) VALUES (nextval(sequence) ,? )";
     *
     * @param columnNames
     * @return
     */
    private String buildCreateQuery(Set<String> columnNames) {
        StringBuilder querySb = new StringBuilder();
        StringBuilder valuesSb = new StringBuilder();

        if (columnNames.size() == 0) {
            throw new IllegalArgumentException("empty object");
        }

        querySb.append(String.format("INSERT INTO %s.%s(", Tables.SCHEMA_NAME, TABLE));
        valuesSb.append("VALUES(");

        Iterator<String> cnIterator = columnNames.iterator();

        boolean cnHasNext = cnIterator.hasNext();
        while (cnHasNext) {
            String columnName = cnIterator.next();

            querySb.append(columnName);
            String preparedValue = columnName.equals(ID_COLUMN) ? String.format("nextval('%s')", Tables.SEQUENCE_NAME) : "?";
            valuesSb.append(preparedValue);


            cnHasNext = cnIterator.hasNext();
            if (cnHasNext) {
                querySb.append(", ");
                valuesSb.append(", ");
            } else {
                querySb.append(") ");
                valuesSb.append(")");
                querySb.append(valuesSb);
            }
        }

        return querySb.toString();
    }
}


