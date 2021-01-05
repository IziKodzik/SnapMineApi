package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;

public interface SecurityService {

	int authenticate();
	Client login(LoginRequest request);
	String test(String text);

}
