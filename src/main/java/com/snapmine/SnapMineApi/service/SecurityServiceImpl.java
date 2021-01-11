package com.snapmine.SnapMineApi.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.snapmine.SnapMineApi.cryptor.AESCryptor;
import com.snapmine.SnapMineApi.dao.ClientDao;
import com.snapmine.SnapMineApi.dao.ClientDataAccessServicePostgres;
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


	public String test(String text){
		byte[] en = this.aesCryptor.encrypt(text.getBytes(StandardCharsets.UTF_8));

		byte[] de = this.aesCryptor.decrypt(en);

		return new String(de);

	}



	@Override
	public Response login(LoginRequest request) {

		Optional<List<Client>> maybeClient = this.DB.getClientByLoginRequest(request);
		if (!maybeClient.isPresent() || maybeClient.get().size() != 1)
			return new LoginResponse(403, "Error: Password or login is incorrect.");

		int id = maybeClient.get().get(0).getId();
		Optional<List<Role>> roles = this.DB.getRolesById(id);
		if (!roles.isPresent() || roles.get().size() <= 0)
			return new LoginResponse(500, "Error: You can not log in due to database problem.");
		SessionToken token = new SessionToken(roles.get());
		String hash = this.aesCryptor.encrypt(gson.toJson(token));
		this.DB.addToken(hash);
		LoginResponse response = new LoginResponse(200, "Success: Token returned.");
		response.setToken(hash);


		return response;
	}
	@Override
	public AuthResponse authenticate(AuthRequest hashedToken) {

		return null;
	}

	@Override
	public AuthResponse validateToken(String hashedToken,String[] roles) {

		String decodedTokenString = this.aesCryptor.decrypt(hashedToken);
		SessionToken token = gson.fromJson(decodedTokenString,SessionToken.class);
		if(!token.isUpToDate())
			return new AuthResponse(403,"Token is expired.");

		Optional<List<SessionToken>> maybeToken
				= this.DB.getTokenByHash(hashedToken);
		if(!maybeToken.isPresent() || maybeToken.get().size() != 1)
			return new AuthResponse(403,"Token does not exist.");

		System.out.println(isAuthorized(token.getRoles(),roles));


		return new AuthResponse(200,"Token is valid.");

	}


	public boolean isAuthorized(List<Role> clientRoles,String[] roleNames){

		return
				clientRoles.stream()
						.anyMatch(r -> Arrays.asList(roleNames)
								.contains(r.getName()));

	}

	@Override
	public AuthResponse validateToken(SessionToken token) {

		return null;
	}

	public AuthResponse authenticate(SessionToken token){
		return null;
	}



}
