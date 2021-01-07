package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;

import java.util.Optional;

public interface SecurityService {

	Optional<String> authenticate();
	Optional<Client> login(LoginRequest request);
	String test(String text);

}
