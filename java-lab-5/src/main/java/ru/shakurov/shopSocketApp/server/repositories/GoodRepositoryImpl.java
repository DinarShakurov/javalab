package ru.shakurov.shopSocketApp.server.repositories;

import ru.shakurov.shopSocketApp.server.context.Component;
import ru.shakurov.shopSocketApp.server.model.Good;
import ru.shakurov.shopSocketApp.server.repositories.connection.ConnectionPoolJdbc;
import ru.shakurov.shopSocketApp.server.utils.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GoodRepositoryImpl implements GoodRepository, Component {
    ConnectionPoolJdbc connectionPool;

    private String FIND_BY_ID = "SELECT * FROM good WHERE id = ?";
    private String FIND_ALL = "SELECT * FROM good";
    private String BUY_GOOD = "INSERT INTO order_history(id_user,id_good, date) VALUES (?,?,NOW())";
    private String ADD_GOOD = "INSERT INTO good (name,price) VALUES(?,?)";

    @Override
    public Optional<Good> findById(Integer integer) {
        try (PreparedStatement ps = connectionPool.getPooledConnection().getConnection().prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, integer);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return Optional.ofNullable(rowMapper.rowMap(rs));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);

        }
        return Optional.empty();
    }

    @Override
    public List<Good> findAll() {
        List<Good> goods = new ArrayList<>();
        try (PreparedStatement ps = connectionPool.getPooledConnection().getConnection().prepareStatement(FIND_ALL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    goods.add(rowMapper.rowMap(rs));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return goods;
    }

    @Override
    public boolean buyGood(long userId, long goodId) {
        try (PreparedStatement ps = connectionPool.getPooledConnection().getConnection().prepareStatement(BUY_GOOD)) {
            ps.setLong(1, userId);
            ps.setLong(2, goodId);
            int i = ps.executeUpdate();
            return i == 1;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean addGood(String name, Integer price) {
        try (PreparedStatement ps = connectionPool.getPooledConnection().getConnection().prepareStatement(ADD_GOOD)) {
            ps.setString(1, name);
            ps.setInt(2, price);
            int i = ps.executeUpdate();
            return i == 1;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public String getName() {
        return "GoodRepository";
    }

    private RowMapper<Good> rowMapper = resultSet -> {
        Good good = new Good();
        good.setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setPrice(resultSet.getInt("price"));
        return good;

    };
}
