package com.snapmine.SnapMineApi.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.snapmine.SnapMineApi.cryptor.AESCryptor;
import com.snapmine.SnapMineApi.dao.ClientDao;
import com.snapmine.SnapMineApi.dao.ClientDataAccessServicePostgres;
import com.snapmine.SnapMineApi.exception.ApiRequestException;
import com.snapmine.SnapMineApi.model.dtos.request.AuthRequest;
import com.snapmine.SnapMineApi.model.dtos.response.AuthResponse;
import com.snapmine.SnapMineApi.model.dtos.response.LoginResponse;
import com.snapmine.SnapMineApi.model.dtos.response.Response;
import com.snapmine.SnapMineApi.model.entity.Client;
import com.snapmine.SnapMineApi.model.entity.Role;
import com.snapmine.SnapMineApi.model.entity.SessionToken;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
							   AESCryptor aesCryptor, Function<String,String> secret) {
		DB = db;
		this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		this.aesCryptor = aesCryptor;
		this.aesCryptor.setKey((secret.apply("tokenHash")));
	}


	@Override
	public void test() {
			DB.test();
	}

	@Override
	public LoginResponse login(LoginRequest request){
		List<Client> client =
				DB.getClientByNameAndPassword(request.getName(),request.getPassword());
		if(client.size() != 1)
			throw new ApiRequestException("Login or password is incorrect.",401);

		List<Role> roles =
				DB.getRolesById(client.get(0).getId());
		SessionToken token = new SessionToken(client.get(0).getId(),
				roles);
		String hashedToken = aesCryptor.encrypt(gson.toJson(token));
		DB.addToken(hashedToken);
		return new LoginResponse("Success.",hashedToken);
	}

	@Override
	public AuthResponse auth(AuthRequest request){
		System.out.println(gson.toJson(request));
		return new AuthResponse("S");
	}

	@Override
	public boolean validateToken(String hashedToken) {
		List<SessionToken> tokens = DB.getTokenByHash(hashedToken);
		System.out.println(tokens);
		if(tokens.size() != 1)
			throw new ApiRequestException("Token does not exists.",401);
		if(tokens.get(0).isUpToDate())
			return true;
		throw new ApiRequestException("Token is expired.",401);
	}
}
