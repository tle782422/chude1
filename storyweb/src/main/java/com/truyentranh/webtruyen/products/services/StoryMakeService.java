package com.truyentranh.webtruyen.products.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.products.models.StoryMake;
import com.truyentranh.webtruyen.products.repositories.StoryMakeRepository;



@Service
public class StoryMakeService {
	
	@Autowired
	private StoryMakeRepository StoryMakeRepository;
	
	//Get All StoryMakes
	public List<StoryMake> findAll(){
		return StoryMakeRepository.findAll();
	}	
	
	//Get StoryMake By Id
	public Optional<StoryMake> findById(int id) {
		return StoryMakeRepository.findById(id);
	}	
	
	//Delete StoryMake
	public void delete(int id) {
		StoryMakeRepository.deleteById(id);
	}
	
	//Update StoryMake
	public void save(StoryMake StoryMake) {
		StoryMakeRepository.save(StoryMake);
	}

}

