package ru.shakurov.webapp_javalab.connection;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import ru.shakurov.context.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionPoolJdbc extends MysqlConnectionPoolDataSource implements Component {

    public void set(String url, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.setUser(user);
            this.setURL(url);
            this.setPassword(password);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public String getName() {
        return "ConnectionJdbc";
    }
}
