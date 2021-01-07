package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientService {

	 int addClient(Client client);
	 Optional<List<Client>> selectAllClients();
	 Optional<Client> getClient(int id);
	 int reset();

}
