package com.snapmine.SnapMineApi.dao;

import com.snapmine.SnapMineApi.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres")
public class SnapMineDataAccessService
    implements SnapMineDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SnapMineDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int addClient(Client client) {
        return 0;
    }

    @Override
    public List<Client> selectAllClients() {
        final String sql = "SELECT * FROM client";
        return jdbcTemplate.query(sql,((resultSet, i) -> {
            return new Client(Integer.parseInt(resultSet.getString("id")),
                    resultSet.getString("name"),
                    resultSet.getString("password"));
        }));
    }

    @Override
    public Optional<Client> getClient(int id) {
        return null;
    }
}
