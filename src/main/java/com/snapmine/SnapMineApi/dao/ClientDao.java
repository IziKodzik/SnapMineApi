package com.snapmine.SnapMineApi.dao;

import com.snapmine.SnapMineApi.model.entity.Client;
import com.snapmine.SnapMineApi.model.entity.Role;
import com.snapmine.SnapMineApi.model.entity.SessionToken;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface ClientDao {

	int addClient(Client client);
	Optional<List<Client>> selectAllClients();

	List<Client> getClientByNameAndPassword(String name, String password);

	Optional<Client> getClient(int id);
	List<Role> getRolesById(int id);
	List<Role> getRolesByClient(Client client);
	SessionToken addToken(String token);
	int reset();

    void test() ;

    Optional<List<SessionToken>> getTokenByHash(String id);
}
