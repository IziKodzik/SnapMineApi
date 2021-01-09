package com.snapmine.SnapMineApi.service;

import com.google.gson.Gson;
import com.snapmine.SnapMineApi.cryptor.AESCryptor;
import com.snapmine.SnapMineApi.dao.ClientDao;
import com.snapmine.SnapMineApi.dao.ClientDataAccessServicePostgres;
import com.snapmine.SnapMineApi.model.dtos.request.AuthRequest;
import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.Role;
import com.snapmine.SnapMineApi.model.SessionToken;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class SecurityServiceImpl
	implements SecurityService{

	private final Gson gson;
	private final ClientDao DB;
	private AESCryptor aesCryptor;
	private Charset CHARSET = StandardCharsets.UTF_8; // ISO-8859-1 vs. UTF-8
	@Autowired
	public SecurityServiceImpl(ClientDataAccessServicePostgres db,
							   AESCryptor aesCryptor, Function<String,String> secret,
							   Gson gson) {
		DB = db;
		this.gson = gson;
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
	public Optional<String> login( LoginRequest request) {

		Optional<List<Client>> maybeClient = this.DB.getClientByLoginRequest(request);
		if(!maybeClient.isPresent() || maybeClient.get().size() != 1)
			return Optional.of("Error: Password or login is incorrect.");

		int id =  maybeClient.get().get(0).getId();
		Optional<List<Role>> roles = this.DB.getRolesById(id);
		if(!roles.isPresent() || roles.get().size() <= 0)
			return Optional.of("Error: You can not log in due to database problem.");
		SessionToken token = new SessionToken(roles.get());
		System.out.println(gson.toJson(token));
		this.DB.addToken(token);
		return Optional.of(aesCryptor.encrypt(gson.toJson(token)));

	}

	@Override
	public Optional<String> authenticate(AuthRequest hashedToken) {

		return this.authenticate(gson.fromJson
				(this.aesCryptor.decrypt(hashedToken.getHashedToken())
						,SessionToken.class));
	}

	public Optional<String> authenticate(SessionToken token){
		return Optional.of(gson.toJson(token));
	}



}
