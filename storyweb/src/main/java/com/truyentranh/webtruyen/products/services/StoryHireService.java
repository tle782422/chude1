package com.truyentranh.webtruyen.products.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.products.models.StoryHire;
import com.truyentranh.webtruyen.products.repositories.StoryHireRepository;



@Service
public class StoryHireService {
	
	@Autowired
	private StoryHireRepository StoryHireRepository;
	
	//Get All StoryHires
	public List<StoryHire> findAll(){
		return StoryHireRepository.findAll();
	}	
	
	//Get StoryHire By Id
	public StoryHire findById(int id) {
		return StoryHireRepository.findById(id).orElse(null);
	}	
	
	//Delete StoryHire
	public void delete(int id) {
		StoryHireRepository.deleteById(id);
	}
	
	//Update StoryHire
	public void save(StoryHire StoryHire) {
		StoryHireRepository.save(StoryHire);
	}

}

