package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.cryptor.AESCryptor;
import com.snapmine.SnapMineApi.dao.ClientDao;
import com.snapmine.SnapMineApi.dao.ClientDataAccessServicePostgres;
import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.Role;
import com.snapmine.SnapMineApi.model.SessionToken;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class SecurityServiceImpl
	implements SecurityService{


	private final ClientDao DB;
	private AESCryptor aesCryptor;

	@Autowired
	public SecurityServiceImpl(ClientDataAccessServicePostgres db,
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
	public Optional<Client> login(LoginRequest request) {
		return null;
	}

	@Override
	public Optional<String> authenticate() {

		Optional<List<Role>> maybeRoles = this.DB.getRolesById(2);
		List<Role> roleList = maybeRoles.orElse(new ArrayList<>());
		SessionToken token = new SessionToken(roleList);
		String test = new String(aesCryptor.encrypt(token.getToken().getBytes(StandardCharsets.UTF_8)));
		System.out.println(new String(aesCryptor.decrypt(test.getBytes(StandardCharsets.UTF_8))));
		return Optional.of("XD");

	}


}
