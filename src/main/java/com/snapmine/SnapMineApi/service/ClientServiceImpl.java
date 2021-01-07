package com.snapmine.SnapMineApi.service;


import com.snapmine.SnapMineApi.dao.ClientDao;
import com.snapmine.SnapMineApi.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl
	implements ClientService{

	private ClientDao db;
	@Autowired
	public ClientServiceImpl(@Qualifier("upgrade") ClientDao db) {
		this.db = db;
	}


	@Override
	public int addClient(Client client) {
		return this.db.addClient(client);
	}

	@Override
	public Optional<List<Client>> selectAllClients() {
		return this.db.selectAllClients();
	}

	@Override
	public Optional<Client> getClient(int id) {
		return null;
	}

	@Override
	public int reset() {
		return this.db.reset();
	}
}

