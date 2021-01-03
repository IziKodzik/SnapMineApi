package com.snapmine.SnapMineApi.api;


import com.snapmine.SnapMineApi.annotation.Secured;
import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;
import com.snapmine.SnapMineApi.service.ClientService;
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

	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	@PostMapping("/add")
	public ResponseEntity<?> addClient(@RequestBody Client client){
		return this.clientService.addClient(client) >= 0 ?
				ResponseEntity.badRequest().body("XD") : ResponseEntity.badRequest().body("XDD");
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
		System.out.println(loginRequest.getLogin());
		return ResponseEntity.badRequest()
				.body("Ty kurwa cpunie jebany");
	}


}
