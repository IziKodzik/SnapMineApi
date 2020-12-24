package com.snapmine.SnapMineApi.api;


import com.snapmine.SnapMineApi.model.Client;
import com.snapmine.SnapMineApi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

	private ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping("/add")
	public int addClient(Client client){
		System.out.println(client);
//		return this.clientService.addClient(client);
		return 0;
	}

	@GetMapping("/all")
	public List<Client> selectAllClients(){
		return this.clientService.selectAllClients();
	}

}
