package ait.db;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by suomiy on 4/23/16.
 */
public class Database {
    /**
     * lookup datasource
     *
     * @return: datasource for the postgress database.
     * @throws DbException in case of a failed lookup.
     */
    public static DataSource getDataSource() throws DbException {
        DataSource ds;
        try {
            InitialContext cxt = new InitialContext();

            if (cxt == null) {
                throw new DbException("No context!");
            }

            ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");

            if (ds == null) {
                throw new DbException("Data source not found!");
            }
        } catch (NamingException e) {
            throw new DbException("Obtaining datasource failed", e);
        }

        return ds;
    }

    /**
     *
     * @return: the connection object.
     * @throws DbException: in case of db exception.
     * @throws SQLException: in case of SQLException.
     */
    public static Connection getConnection() throws DbException, SQLException {
        return getDataSource().getConnection();
    }
}
