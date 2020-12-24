package com.snapmine.SnapMineApi.api;


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

	@CrossOrigin
	@PostMapping
	public int addClient(String client){
		System.out.println(client);
//		return this.clientService.addClient(client);
		return 0;
	}

	@CrossOrigin
	@GetMapping
	public List<Client> selectAllClients(){
		return this.clientService.selectAllClients();
	}

}
