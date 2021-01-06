package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.cryptor.AESCryptor;
import com.snapmine.SnapMineApi.dao.ClientDataAccessService;
import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;

@Service
public class SecurityServiceImpl
	implements SecurityService{


	private final ClientDataAccessService DB;
	private AESCryptor aesCryptor;

	@Autowired
	public SecurityServiceImpl(@Qualifier("postgres") ClientDataAccessService db,
							   AESCryptor aesCryptor, Function<String,String> secret) {
		DB = db;
		this.aesCryptor = aesCryptor;
		this.aesCryptor.setKey((secret.apply("tokenHash")));
	}


	public String test(String text){
		byte[] en = this.aesCryptor.encrypt(text.getBytes(StandardCharsets.UTF_8));
		System.out.println(new String(en));
		byte[] de = this.aesCryptor.decrypt(en);
		System.out.println(new String(de));
		return new String(de);

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
