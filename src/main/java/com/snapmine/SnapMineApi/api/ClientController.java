package com.snapmine.SnapMineApi.api;


import com.snapmine.SnapMineApi.annotation.Secured;
import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;
import com.snapmine.SnapMineApi.service.ClientService;
import com.snapmine.SnapMineApi.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/client")
@RestController
public class ClientController {

	private final ClientService clientService;


	private final SecurityService securityService;

	@Autowired
	public ClientController(ClientService clientService
							,SecurityService securityService) {
		this.clientService = clientService;
		this.securityService = securityService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> addClient(@RequestBody Client client){
		return this.clientService.addClient(client) >= 0 ?
				ResponseEntity.ok().body("Account created.") : ResponseEntity.badRequest().body("nah :(");
	}
	@Secured
	@GetMapping("/all")
	public ResponseEntity<?> selectAllClients(){
		return ResponseEntity.ok(this.clientService.selectAllClients());
	}

	@GetMapping
	public ResponseEntity<?> client(){
		return ResponseEntity.ok( "Hello to clients");
	}

	@Secured
	@GetMapping("/{id}")
	public ResponseEntity<?> getClient(@PathVariable("id") int id){
		return ResponseEntity.ok(id);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		String token = this.securityService.login(loginRequest).orElse(null);
		return  token != null ? ResponseEntity.ok(token) :
				ResponseEntity.badRequest()
						.body("Login or password incorrect.");
	}
	@Secured
	@GetMapping("/reset")
	public int reset(){
		return this.clientService.reset();
	}



}
