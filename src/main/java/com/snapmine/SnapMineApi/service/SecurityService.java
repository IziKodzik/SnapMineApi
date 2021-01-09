package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.SessionToken;
import com.snapmine.SnapMineApi.model.dtos.request.AuthRequest;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;

import java.util.Optional;

public interface SecurityService {


	Optional<String> authenticate(AuthRequest hashedToken);
	Optional<String> authenticate(SessionToken token);
	Optional<String> login(LoginRequest request);
	String test(String text);

}
