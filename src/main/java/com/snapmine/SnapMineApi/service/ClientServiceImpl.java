package com.snapmine.SnapMineApi.service;


import com.snapmine.SnapMineApi.dao.ClientDao;
import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.cryptor.AESCryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl
	implements ClientService{

	private ClientDao db;
	private AESCryptor aesCryptor;
	@Autowired
	public ClientServiceImpl(@Qualifier("postgres") ClientDao db,
							 AESCryptor aesCryptor) {
		this.db = db;
		this.aesCryptor = aesCryptor;
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

	@Override
	public int reset() {
		return this.db.reset();
	}
}

