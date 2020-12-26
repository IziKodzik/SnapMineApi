package com.snapmine.SnapMineApi.api;


import com.snapmine.SnapMineApi.annotation.Secured;
import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/client")
@RestController
public class ClientController {

	private ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	@PostMapping("/add")
	public int addClient(@RequestBody Client client){
		return this.clientService.addClient(client);
	}
	@Secured
	@GetMapping("/all")
	public List<Client> selectAllClients(){
		return this.clientService.selectAllClients();
	}

	@GetMapping
	public String client(){
		return "Hello to clients";
	}

}
