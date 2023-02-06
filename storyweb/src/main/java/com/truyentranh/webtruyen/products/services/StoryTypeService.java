package com.truyentranh.webtruyen.products.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.products.models.StoryType;
import com.truyentranh.webtruyen.products.repositories.StoryTypeRepository;

@Service
public class StoryTypeService {
	
	@Autowired
	private StoryTypeRepository StoryTypeRepository;
	
	//Get All StoryTypes
	public List<StoryType> findAll(){
		return StoryTypeRepository.findAll();
	}	
	
	//Get StoryType By Id
	public Optional<StoryType> findById(int id) {
		return StoryTypeRepository.findById(id);
	}	
	
	//Delete StoryType
	public void delete(int id) {
		StoryTypeRepository.deleteById(id);
	}
	
	//Update StoryType
	public void save(StoryType StoryType) {
		StoryTypeRepository.save(StoryType);
	}

}
