package com.snapmine.SnapMineApi.dao;

import com.snapmine.SnapMineApi.model.Client;

import java.util.ArrayList;
import java.util.List;


public interface SnapMineDao {

	int addClient(Client client);
	List<Client> selectAllClients();
	Client getClient(int id);
}
