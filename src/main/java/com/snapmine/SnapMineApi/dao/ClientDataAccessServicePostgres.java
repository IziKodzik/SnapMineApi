package com.snapmine.SnapMineApi.dao;

import com.snapmine.SnapMineApi.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
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
            (@Qualifier("secret") Function<String,String> secret) {
        this.secret = secret;
        this.connectionString = secret.apply("url");;
        this.password = secret.apply("password");
        this.user = secret.apply("user");
    }

    @Override
    public int addClient(Client client) {
        return 0;
    }

    @Override
    public List<Client> selectAllClients() {

        this.query("SELECT * FROM client",);
        return null;
    }

    @Override
    public Optional<Client> getClient(int id) {
        return Optional.empty();
    }

    @Override
    public int reset() {
        return 0;
    }

    private <T> Optional<List<T>> query(String query,Function<ResultSet,T> mapper){

        List<T> result = new ArrayList<>();
        try(Connection connection =
                    DriverManager.getConnection(this.connectionString,
                            this.user,this.password)){

            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM client");
            while (set.next())
                result.add( mapper.apply(set));

        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.of(result);
    }






}
