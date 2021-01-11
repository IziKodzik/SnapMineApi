package com.snapmine.SnapMineApi.dao;

import com.snapmine.SnapMineApi.model.entity.Client;
import com.snapmine.SnapMineApi.model.entity.Role;
import com.snapmine.SnapMineApi.model.entity.SessionToken;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;

import java.util.List;
import java.util.Optional;


public interface ClientDao {

	int addClient(Client client);
	Optional<List<Client>> selectAllClients();
	Optional<Client> getClient(int id);
	Optional<List<Role>> getRolesById(int id);
	Optional<List<Role>> getRolesByClient(Client client);
	Optional<List<Client>> getClientByLoginRequest(LoginRequest request);
	Optional<SessionToken> addToken(String token);
	int reset();

	Optional<List<SessionToken>> getTokenByHash(String id);
}
