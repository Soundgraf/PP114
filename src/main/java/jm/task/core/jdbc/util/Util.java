package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Dump";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootroot";
    private static final String DIALECT = "org.hibernate.dialect.MySQL5Dialect";

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, DRIVER);
            properties.put(Environment.URL, URL);
            properties.put(Environment.USER, USERNAME);
            properties.put(Environment.PASS, PASSWORD);
            properties.put(Environment.DIALECT, DIALECT);

//            properties.put(Environment.SHOW_SQL, "true");
//            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//            properties.put(Environment.HBM2DDL_AUTO,"create-drop");

            Configuration configuration = new Configuration()
                    .setProperties(properties)
                    .addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("Connection");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}