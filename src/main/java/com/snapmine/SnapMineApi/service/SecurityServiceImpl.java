package com.snapmine.SnapMineApi.service;

import com.google.gson.Gson;
import com.snapmine.SnapMineApi.cryptor.AESCryptor;
import com.snapmine.SnapMineApi.dao.ClientDao;
import com.snapmine.SnapMineApi.dao.ClientDataAccessServicePostgres;
import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.SessionToken;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

		int id = this.DB.getClientByLoginRequest(request).get().get(0).getId();
		SessionToken token =
				new SessionToken(this.DB.getRolesById(id).get());
		return Optional.of(aesCryptor.encrypt(gson.toJson(token)));
	}

	@Override
	public Optional<String> authenticate() {

		SessionToken token = new SessionToken(this.DB.getRolesById(2).orElse(new ArrayList<>()));
		return Optional.of(aesCryptor.encrypt(gson.toJson(token)));
	}



}
