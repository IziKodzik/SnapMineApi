package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.model.dtos.response.AuthResponse;
import com.snapmine.SnapMineApi.model.dtos.response.Response;
import com.snapmine.SnapMineApi.model.entity.SessionToken;
import com.snapmine.SnapMineApi.model.dtos.request.AuthRequest;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;

public interface SecurityService {


	AuthResponse authenticate(AuthRequest hashedToken);
	AuthResponse authenticate(SessionToken token);
	Response login(LoginRequest request);
	String test(String text);
	AuthResponse validateToken(String hashedToken);
	AuthResponse validateToken(SessionToken token);

}
