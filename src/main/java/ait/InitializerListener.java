package ait;

import ait.db.Database;
import ait.db.Manager;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

/**
 * Created by suomiy on 4/23/16.
 */
public class InitializerListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(Manager.class.getName());

    public void contextInitialized(ServletContextEvent sce) {
        try {
            // run liquibase changesets
            liquibase.database.Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(Database.getConnection()));
            Liquibase liquibase = new Liquibase("META-INF/db_changelog.xml", new ClassLoaderResourceAccessor(), database);

            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e) {
            log.severe(e.toString());
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}


