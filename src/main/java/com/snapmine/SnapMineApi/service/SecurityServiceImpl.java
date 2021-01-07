package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.cryptor.AESCryptor;
import com.snapmine.SnapMineApi.dao.ClientDao;
import com.snapmine.SnapMineApi.dao.ClientDataAccessServicePostgres;
import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.util.Base64;
import java.util.Optional;
import java.util.function.Function;

@Service
public class SecurityServiceImpl
	implements SecurityService{

	private final BASE64Decoder base64Decoder = new BASE64Decoder();
	private final BASE64Encoder base64Encoder = new BASE64Encoder();
	private final ClientDao DB;
	private AESCryptor aesCryptor;
	private Charset CHARSET = StandardCharsets.UTF_8; // ISO-8859-1 vs. UTF-8
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

		String mess = "message";
		String costam = base64Encoder.encode(aesCryptor.encrypt(mess.getBytes(CHARSET)));
		System.out.println(costam);
		byte[] decodedBytes = Base64.getDecoder().decode(costam);
		decodedBytes = aesCryptor.decrypt(decodedBytes);
		System.out.println(new String(decodedBytes));
		//		Optional<List<Role>> maybeRoles = this.DB.getRolesById(2);
//		List<Role> roleList = maybeRoles.orElse(new ArrayList<>());
//		SessionToken token = new SessionToken(roleList);
//		aesCryptor.encrypt(token.getToken().getBytes(StandardCharsets.UTF_8));
//		(new String(aesCryptor.decrypt(.getBytes(StandardCharsets.UTF_8))));
		return Optional.of("XD");

	}


}
