package com.snapmine.SnapMineApi.dao;

import com.snapmine.SnapMineApi.model.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface ClientDao {

	int addClient(Client client);
	List<Client> selectAllClients();
	Optional<Client> getClient(int id);
	int reset();
}
