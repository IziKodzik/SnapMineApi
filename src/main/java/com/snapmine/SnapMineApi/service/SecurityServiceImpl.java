package com.snapmine.SnapMineApi.service;

import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl
	implements SecurityService{
	@Override
	public int authenticate() {
		System.out.println("auth");
		return 200;
	}
}
