package ru.shakurov.shopSocketApp.server.repositories.connection;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import ru.shakurov.shopSocketApp.server.context.Component;

public class ConnectionPoolJdbc extends MysqlConnectionPoolDataSource implements Component {

    public ConnectionPoolJdbc() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        this.setUrl("jdbc:mysql://localhost:3306/java_lab_1?serverTimezone=Europe/Moscow");
        this.setUser("root");
        this.setPassword("ifrehjdlbyfh");
    }

    @Override
    public String getName() {
        return "ConnectionJdbc";
    }
}
