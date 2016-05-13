package ait;

import ait.db.ConditionBuilder;
import ait.db.Database;
import ait.db.Manager;
import ait.db.Managers;
import ait.entity.ShoppingCart;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.logging.Logger;

/**
 * Created by suomiy on 4/23/16.
 */
public class InitializerListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(Manager.class.getName());

    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("init");
            liquibase.database.Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(Database.getConnection()));
            Liquibase liquibase = new Liquibase("META-INF/db_changelog.xml", new ClassLoaderResourceAccessor(), database);

            liquibase.update(new Contexts(), new LabelExpression());

            Manager<ShoppingCart> manager = Managers.getShoppingCartManager();
            for (ShoppingCart cart : manager.find(new ConditionBuilder())) {
                manager.delete(cart);
            }
            ShoppingCart c = new ShoppingCart();
            c.setStatus(ShoppingCart.Status.MAX);
            OffsetDateTime now = OffsetDateTime.now(ZoneId.of("UTC"));
            c.setDate(now);
            manager.create(c);

            for (ShoppingCart cart : manager.findAll()) {
                System.out.println(cart.getId());
                System.out.println(cart.getStatus());
                System.out.println(cart.getDate());
            }
        } catch (Exception e) {
            log.severe(e.toString());
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}


