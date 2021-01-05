package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.cryptor.AESCryptor;
import com.snapmine.SnapMineApi.dao.ClientDataAccessService;
import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SecurityServiceImpl
	implements SecurityService{


	private final ClientDataAccessService DB;
	private AESCryptor aesCryptor;
	private final String key;

	@Autowired
	public SecurityServiceImpl(@Qualifier("postgres") ClientDataAccessService db,
							   AESCryptor aesCryptor, Function<String,String> secret) {
		DB = db;
		this.aesCryptor = aesCryptor;
		this.key = (secret.apply("tokenHash"));
	}


	public String test(String text){
		String s = aesCryptor.encrypt(text,key);
		System.out.println(s + " endcrypt");
		s = aesCryptor.decrypt(s,key);
		System.out.println(s);
		return "DOne";

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
