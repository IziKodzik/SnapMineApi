package com.snapmine.SnapMineApi.service;

import com.snapmine.SnapMineApi.model.dtos.response.AuthResponse;
import com.snapmine.SnapMineApi.model.dtos.response.LoginResponse;
import com.snapmine.SnapMineApi.model.dtos.response.Response;
import com.snapmine.SnapMineApi.model.entity.SessionToken;
import com.snapmine.SnapMineApi.model.dtos.request.AuthRequest;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;

public interface SecurityService {

    public void test();

    LoginResponse login(LoginRequest request);
}
