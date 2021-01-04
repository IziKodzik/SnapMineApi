package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.dao.ClientDataAccessService;
import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl
	implements SecurityService{


	private final ClientDataAccessService DB;

	@Autowired
	public SecurityServiceImpl(@Qualifier("postgres") ClientDataAccessService db) {
		DB = db;
	}


	@Override
	public Client login(LoginRequest request) {
		return this.DB.login(request);
	}

	@Override
	public int authenticate() {
		System.out.println("auth");
		return 200;
	}


}
