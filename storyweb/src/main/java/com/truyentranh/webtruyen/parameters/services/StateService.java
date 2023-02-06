package com.truyentranh.webtruyen.parameters.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.parameters.models.State;
import com.truyentranh.webtruyen.parameters.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	//Get All States
	public List<State> findAll(){
		return stateRepository.findAll();
	}	
	
	//Get State By Id
	public State findById(int id) {
		return stateRepository.findById(id).orElse(null);
	}	
	
	//Delete State
	public void delete(int id) {
		stateRepository.deleteById(id);
	}
	
	//Update State
	public void save( State state) {
		stateRepository.save(state);
	}
}
