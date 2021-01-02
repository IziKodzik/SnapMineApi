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
        return 0;
    }

    @Override
    public List<Client> selectAllClients() {
        System.out.println(this.connectionString);
        List<Client> clients = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(this.connectionString,user,password)){

            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM client");
            for(;result.next();) {
                clients.add(new Client(result.getInt("id"),
                        result.getString("name"),
                        result.getString("password")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Optional<Client> getClient(int id) {
        return null;
    }
}
