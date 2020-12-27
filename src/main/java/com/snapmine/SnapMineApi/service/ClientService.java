package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.model.Client;

import java.util.List;

public interface ClientService {

	 int addClient(Client client);
	 List<Client> selectAllClients();
	 Client getClient(int id);

}
