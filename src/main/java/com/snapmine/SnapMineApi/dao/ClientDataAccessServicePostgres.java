package com.snapmine.SnapMineApi.dao;

import com.google.gson.Gson;
import com.snapmine.SnapMineApi.exception.ApiRequestException;
import com.snapmine.SnapMineApi.model.entity.Client;
import com.snapmine.SnapMineApi.model.entity.Role;
import com.snapmine.SnapMineApi.model.entity.SQLMapper;
import com.snapmine.SnapMineApi.model.entity.SessionToken;
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

    Gson gSon = new Gson();
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


    public List<Role> getRolesById(int id){
        String query =(String.format("SELECT * FROM role INNER JOIN clientRole ON roleID=id WHERE clientID = %d",
                id));
        return (this.query(query,Role.getMapper()));
    }
    public List<Role> getRolesByClient(Client client){
        return this.getRolesById(client.getId());
    }


    @Override
    public Optional<List<Client>> selectAllClients() {
        return Optional.ofNullable(this.query("SELECT * FROM client", Client.getMapper()));
    }

    @Override
    public List<Client> getClientByNameAndPassword(String name, String password) {
        String query = String.format("SELECT * FROM client WHERE name='%s' AND password='%s';",
                name,password);
        return (this.query(query,Client.getMapper()));
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

    @Override
    public String addToken(String hash,String refreshToken) {
        String query = String.format("INSERT INTO SessionToken VALUES('%s','%s')",hash,refreshToken);
        this.query(query,null);
        return this.getTokenByHash(hash).get(0).getId();
    }

    @Override
    public void test(){
        throw new ApiRequestException();
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
            throw new ApiRequestException("Problems with database connection.",503);
        }
        return (result);
    }

    @Override
    public List<String> noideaforname(String hash, String refreshToken) {
        String query =
                String.format("SELECT * FROM SessionToken WHERE hash='%s' AND refreshToken='%s'",
                        hash,refreshToken);
        return query(query,set-> "Ok");


    }

    @Override
    public List<String> deleteTokenWithHash(String hashedToken) {
        String query = String.format("DELETE FROM SessionToken WHERE hash='%s'",hashedToken);
        return query(query,null);
    }

    @Override
    public List<SessionToken> getTokenByHash(String hash) {
        String query = String.format("SELECT * FROM sessionToken WHERE hash='%s';",hash);
        return (this.query(query,SessionToken.getMapper()));

    }
}
