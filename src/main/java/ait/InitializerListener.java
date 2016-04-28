package ait;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by suomiy on 4/23/16.
 */
public class InitializerListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("init");
            liquibase.database.Database database =  DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(Database.getConnection()));
            Liquibase liquibase = new Liquibase("META-INF/db_changelog.xml", new ClassLoaderResourceAccessor(), database);

            liquibase.update(new Contexts(), new LabelExpression());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}


