package com.snapmine.SnapMineApi.dao;


import com.snapmine.SnapMineApi.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fake")
public class FakeSnapMineDao
	implements SnapMineDao{

	private List<Client> DB = new ArrayList<>();

	@Override
	public int addClient(Client client) {
		DB.add(client);
		return client.getId();
	}

	@Override
	public List<Client> selectAllClients() {
		return this.DB;
	}
}
