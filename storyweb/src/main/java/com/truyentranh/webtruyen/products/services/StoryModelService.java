package com.truyentranh.webtruyen.products.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.products.models.StoryModel;
import com.truyentranh.webtruyen.products.repositories.StoryModelRepository;

@Service
public class StoryModelService {
	
	@Autowired
	private StoryModelRepository StoryModelRepository;
	
	//Get All StoryModels
	public List<StoryModel> findAll(){
		return StoryModelRepository.findAll();
	}	
	
	//Get StoryModel By Id
	public Optional<StoryModel> findById(int id) {
		return StoryModelRepository.findById(id);
	}	
	
	//Delete StoryModel
	public void delete(int id) {
		StoryModelRepository.deleteById(id);
	}
	
	//Update StoryModel
	public void save(StoryModel StoryModel) {
		StoryModelRepository.save(StoryModel);
	}

}
