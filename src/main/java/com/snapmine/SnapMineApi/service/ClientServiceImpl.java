package com.snapmine.SnapMineApi.service;


import com.snapmine.SnapMineApi.dao.SnapMineDao;
import com.snapmine.SnapMineApi.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl
	implements ClientService{

	private SnapMineDao db;

	@Autowired
	public ClientServiceImpl(@Qualifier("fake") SnapMineDao db) {
		this.db = db;
	}


	@Override
	public int addClient(Client client) {
		return this.db.addClient(client);
	}

	@Override
	public List<Client> selectAllClients() {
		return this.db.selectAllClients();
	}

	@Override
	public Client getClient(int id) {
		return null;
	}
}

