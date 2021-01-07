package com.snapmine.SnapMineApi.dao;

import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.SQLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository("upgrade")
public class ClientDataAccessServicePostgres
    implements ClientDao{

    private final String connectionString,
            password,
            user;
    private final Function<String,String> secret;

    @Autowired
    public ClientDataAccessServicePostgres
            (@Qualifier("secretProvider") Function<String,String> secretProvider) {
        this.secret = secretProvider;
        this.connectionString = secretProvider.apply("url");;
        this.password = secretProvider.apply("password");
        this.user = secretProvider.apply("user");
    }

    @Override
    public int addClient(Client client) {
        int id = this.query("SELECT MAX(id) as id FROM client;",
                set->set.getInt("id")).get(0);

        String query = String.format("INSERT INTO client VALUES(%d,'%s','%s','%s');",
                (id+1),client.getName(),client.getPassword(),client.getEmail());
        this.query(query,null);
        return 0;
    }

    @Override
    public Optional<List<Client>> selectAllClients() {
        return Optional.ofNullable(this.query("SELECT * FROM client", Client.getMapper()));
    }

    @Override
    public Optional<Client> getClient(int id) {
        return Optional.empty();
    }

    @Override
    public int reset() {
        this.query("DELETE FROM client WHERE 1=1;",null);
        return 0;
    }

    private <T> List<T> query(String query, SQLMapper<T> mapper){
        List<T> result = new ArrayList<>();
        try(Connection connection =
                    DriverManager.getConnection(this.connectionString,
                            this.user,this.password)){

            try(Statement statement = connection.createStatement()) {
                if (mapper == null) {
                    statement.executeUpdate(query);
                    return result;
                }
                ResultSet set = statement.executeQuery(query);
                while (set.next())
                    result.add(mapper.map(set));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return (result);
    }

}
