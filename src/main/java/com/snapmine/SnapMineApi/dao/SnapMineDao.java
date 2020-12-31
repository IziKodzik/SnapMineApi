package com.snapmine.SnapMineApi.dao;

import com.snapmine.SnapMineApi.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface SnapMineDao {

	int addClient(Client client);
	List<Client> selectAllClients();
	Optional<Client> getClient(int id);
}
