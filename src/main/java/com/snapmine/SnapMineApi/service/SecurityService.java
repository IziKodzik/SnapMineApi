package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.model.dtos.response.AuthResponse;
import com.snapmine.SnapMineApi.model.entity.SessionToken;
import com.snapmine.SnapMineApi.model.dtos.request.AuthRequest;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;

import java.util.Optional;

public interface SecurityService {


	AuthResponse authenticate(AuthRequest hashedToken);
	AuthResponse authenticate(SessionToken token);
	Optional<String> login(LoginRequest request);
	String test(String text);
	AuthResponse validateToken(String hashedToken);
	AuthResponse validateToken(SessionToken token);

}
