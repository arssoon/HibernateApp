package factory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    private static Configuration getHibernateConfig() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:sqlserver://ARSON\\MSSQLEXPRESS:54233; " +
                "databaseName=work");
        configuration.setProperty("hibernate.connection.username", "arson");
        configuration.setProperty("hibernate.connection.password", "marker25");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        configuration.setProperty("connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.connection.autocommit", "true");
        configuration.addAnnotatedClass(entity.Employee.class);
        return configuration;
    }

    public static SessionFactory getSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
                applySettings(getHibernateConfig().getProperties()).build();

        return getHibernateConfig().buildSessionFactory(registry);
    }

    public static void closeRegistry(StandardServiceRegistry registry) {
        registry.close();
    }
}