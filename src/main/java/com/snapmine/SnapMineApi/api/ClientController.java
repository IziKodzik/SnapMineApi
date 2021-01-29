package com.snapmine.SnapMineApi.api;


import com.snapmine.SnapMineApi.annotation.Secured;
import com.snapmine.SnapMineApi.model.dtos.response.Response;
import com.snapmine.SnapMineApi.model.entity.Client;
import com.snapmine.SnapMineApi.model.dtos.request.LoginRequest;
import com.snapmine.SnapMineApi.service.ClientService;
import com.snapmine.SnapMineApi.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/client")
@RestController
public class ClientController {

	private final ClientService clientService;



	@Autowired
	pe.printStackTrace();ublic ClientController(ClientService clientService)
							 {
		this.clientService = clientService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> addClient(@RequestBody Client client){
		return this.clientService.addClient(client) >= 0 ?
				ResponseEntity.ok().body("Account created.") : ResponseEntity.badRequest().body("nah :(");
	}

	@Secured(roles={"admin"})
	@GetMapping("/all")
	public ResponseEntity<?> selectAllClients(){
		return ResponseEntity.ok(this.clientService.selectAllClients());
	}

	@GetMapping
	public ResponseEntity<?> client(){
		return ResponseEntity.ok( "Hello to clients");
	}


	@Secured(roles = {"admin"},idPos = 0)
	@GetMapping("/{id}")
	public ResponseEntity<?> getClient(@PathVariable("id") int id){
		return ResponseEntity.ok(id);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		return null;
	}
	@Secured
	@GetMapping("/reset")
	public int reset(){
		return this.clientService.reset();
	}
	@Secured(roles = {"admin"},idPos = 2)
	@GetMapping("/{id}/jd/dd")
	public ResponseEntity<?> jdOrka(){
		return ResponseEntity.ok("JD");
	}



}
