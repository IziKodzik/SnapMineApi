package com.snapmine.SnapMineApi.dao;

import com.snapmine.SnapMineApi.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository("postgres")
public class ClientDataAccessService
    implements ClientDao {

    private final String connectionString,
                            password,
                            user;
    private final Function<String,String> factor;

    @Autowired
    public ClientDataAccessService(@Qualifier("factor") Function<String,String> factor) {
        this.factor = factor;
        this.connectionString = factor.apply("url");;
        this.password = factor.apply("password");
        this.user = factor.apply("user");
    }

    @Override
    public int addClient(Client client) {

        try {
            Optional<ResultSet> maybeResult =
                    Optional.ofNullable((resQuery("SELECT MAX(id) as id FROM client")));
            if(!maybeResult.isPresent())
                return -1;
            ResultSet resultSet = maybeResult.get();
            resultSet.next();
            int id = (resultSet.getInt("id"));
            client.setId(id + 1);

            voidQuery(String.format(
                    "INSERT INTO client VALUES (%d,'%s','%s','%s')",
                    client.getId(),client.getName(),client.getPassword(),
                    client.getEmail())
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client.getId();
    }

    @Override
    public List<Client> selectAllClients() {
        Optional<ResultSet> maybeResult =  Optional.ofNullable(this.resQuery("SELECT * FROM client"));
        List<Client> result = new ArrayList<>();
        if(!maybeResult.isPresent())
            return result;
        ResultSet resultSet = maybeResult.get();
        try {
            while ( resultSet.next() )
                result.add(new Client(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("password")));

            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    private ResultSet resQuery(String q){
        try(Connection con = DriverManager.getConnection(this.connectionString,user,password)){
            Statement statement = con.createStatement();
            return statement.executeQuery(q);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    private int voidQuery(String q){
        try(Connection con = DriverManager.getConnection(this.connectionString,user,password)){
            Statement statement = con.createStatement();
            statement.execute(q);
            return 1;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Optional<Client> getClient(int id) {
        return null;
    }
}
