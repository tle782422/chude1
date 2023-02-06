package com.truyentranh.webtruyen.parameters.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.parameters.models.Client;
import com.truyentranh.webtruyen.parameters.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	//Get All Clients
	public List<Client> findAll(){
		return clientRepository.findAll();
	}

	//Get Client By Id
	public Client findById(int id) {
		return clientRepository.findById(id).orElse(null);
	}

	//Delete Client
	public void deleteById(int id) {
		clientRepository.deleteById(id);
	}

	//Update Client
	public void save( Client client) {
		clientRepository.save(client);
	}

}
