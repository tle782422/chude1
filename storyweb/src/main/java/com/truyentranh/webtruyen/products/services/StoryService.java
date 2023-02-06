package com.truyentranh.webtruyen.products.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.products.models.Story;
import com.truyentranh.webtruyen.products.repositories.StoryRepository;

@Service
public class StoryService {
	
	@Autowired
	private StoryRepository StoryRepository;
	
	//Get All Storys
	public List<Story> findAll(){
		return StoryRepository.findAll();
	}	
	
	//Get Story By Id
	public Story findById(int id) {
		return StoryRepository.findById(id).orElse(null);
	}	
	
	//Delete Story
	public void delete(int id) {
		StoryRepository.deleteById(id);
	}
	
	//Update Story
	public void save(Story Story) {
		StoryRepository.save(Story);
	}

}

