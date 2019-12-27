package ru.shakurov.shopSocketApp.server.repositories;

import ru.shakurov.shopSocketApp.server.context.Component;
import ru.shakurov.shopSocketApp.server.model.User;
import ru.shakurov.shopSocketApp.server.repositories.connection.ConnectionPoolJdbc;
import ru.shakurov.shopSocketApp.server.utils.RowMapper;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository, Component {
    ConnectionPoolJdbc connectionPool;


    private String FIND_BY_LOGIN = "SELECT * FROM user WHERE login = ? ";
    private String ADD_NEW_USER = "INSERT INTO user(login, password, role) VALUES (?,?,?)";

    public boolean addUser(User user) {
        try (PreparedStatement ps = connectionPool.getPooledConnection().getConnection().prepareStatement(ADD_NEW_USER)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            int i = ps.executeUpdate();
            return i == 1;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> fineByLogin(String login) {
        try (PreparedStatement ps = connectionPool.getPooledConnection().getConnection().prepareStatement(FIND_BY_LOGIN)) {
            ps.setString(1, login);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    return Optional.ofNullable(rowMapper.rowMap(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public String getName() {
        return "UsersRepository";
    }

    private RowMapper<User> rowMapper = resultSet -> {
        User user = new User();
        user.setLogin(resultSet.getString("login"))
                .setId(resultSet.getLong("id"))
                .setPassword(resultSet.getString("password"))
                .setRole(resultSet.getString("role"));
        return user;
    };
}
