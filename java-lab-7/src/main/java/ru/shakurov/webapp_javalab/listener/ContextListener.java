package ru.shakurov.webapp_javalab.listener;

import ru.shakurov.context.ApplicationContext;
import ru.shakurov.context.ApplicationContextReflectionBased;
import ru.shakurov.webapp_javalab.connection.ConnectionPoolJdbc;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ApplicationContext applicationContext = new ApplicationContextReflectionBased();
        ConnectionPoolJdbc connectionPoolJdbc = applicationContext.getComponent(ConnectionPoolJdbc.class, "ConnectionJdbc");

        String path = sce.getServletContext().getRealPath("/WEB-INF/classes/mydb.properties");
        try {
            FileInputStream in = new FileInputStream(path);
            Properties properties = new Properties();
            properties.load(in);
            connectionPoolJdbc.set(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password")
            );

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        System.out.println(applicationContext.toString());
        servletContext.setAttribute("applicationContext", applicationContext);
        System.out.println("iiiiiiiiiiiii");
    }
}
