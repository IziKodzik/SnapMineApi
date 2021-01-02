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
            ResultSet r = ((resQuery("SELECT MAX(id) as id FROM client")));
            r.next();
            int id = (r.getInt("id"));
            client.setId(id + 1);

            voidQuery(String.format(
                    "INSERT INTO client VALUES (%d,'%s','%s','%s')",
                    client.getId(),client.getName(),client.getPassword(),
                    client.getEmail())
            );

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return client.getId();
    }

    @Override
    public List<Client> selectAllClients() {
        ResultSet r= this.resQuery("SELECT * FROM client");
        List<Client> result = new ArrayList<>();
        try {
            for (; r!= null && r.next(); )
                result.add(new Client(r.getInt("id"),r.getString("name"),r.getString("password")));

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
