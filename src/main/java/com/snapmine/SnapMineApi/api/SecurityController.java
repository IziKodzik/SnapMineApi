package com.snapmine.SnapMineApi.api;


import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.dtos.request.AuthRequest;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;
import com.snapmine.SnapMineApi.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/security")
public class SecurityController {

    final private
    SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @RequestMapping(value = "/test", method = POST)
    public String test(@RequestBody String text) {
        return this.securityService.test(text);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest hashedToken) {
        return ResponseEntity.ok(this.securityService.authenticate(hashedToken));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(this.securityService.login(request));
    }
}