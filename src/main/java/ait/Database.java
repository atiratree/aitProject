package ait;


import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by suomiy on 4/23/16.
 */
public class Database {
    /**
     * lookup datasource
     *
     * @return DataSource
     * @throws Exception in case of a failed lookup
     */
    public static DataSource getDataSource() throws Exception {
        InitialContext cxt = new InitialContext();
        if (cxt == null) {
            throw new Exception("No context!");
        }

        DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");

        if (ds == null) {
            throw new Exception("Data source not found!");
        }

        return ds;
    }

    public static Connection getConnection() throws Exception {
        return getDataSource().getConnection();
    }
}

//import org.apache.tomcat.jdbc.pool.DataSource;
//import org.apache.tomcat.jdbc.pool.PoolProperties;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
///**
// * Created by suomiy on 4/23/16.
// */
//public class DatabaseBasic {
//    private static DataSource dataSource;
//    private static final String URL = "jdbc:postgresql://localhost:5432/AIT";
//    private static final String DRIVER = "org.postgresql.Driver";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "";
//
//    static {
//        PoolProperties p = new PoolProperties();
//        p.setUrl(URL);
//        p.setDriverClassName(DRIVER);
//        p.setUsername(USERNAME);
//        p.setPassword(PASSWORD);
//
//        dataSource = new DataSource(p);
//        dataSource.setPoolProperties(p);
//    }
//
//    public static DataSource getDataSource() throws SQLException {
//        return dataSource;
//    }
//
//    public static Connection getConnection() throws Exception {
//        return dataSource.getConnection();
//    }
//
//}