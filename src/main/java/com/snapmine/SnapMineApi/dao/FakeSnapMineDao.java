//package com.snapmine.SnapMineApi.dao;
//
//
//import com.snapmine.SnapMineApi.model.Client;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Repository("fake")
//public class FakeSnapMineDao
//	implements SnapMineDao{
//
//	private List<Client> DB = new ArrayList<>();
//
//	@Override
//	public int addClient(Client client) {
//		client.setId(DB.size());
//		DB.add(client);
//		return client.getId();
//	}
//
//	@Override
//	public List<Client> selectAllClients() {
//		return this.DB;
//	}
//
//	@Override
//	public Optional<Client> getClient(int id) {
//		return DB.get(id);
//	}
//}
