package com.truyentranh.webtruyen.products.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.products.models.StoryStatus;
import com.truyentranh.webtruyen.products.repositories.StoryStatusRepository;

@Service
public class StoryStatusService {
	
	@Autowired
	private StoryStatusRepository StoryStatusRepository;
	
	//Get All StoryStatuss
	public List<StoryStatus> findAll(){
		return StoryStatusRepository.findAll();
	}	
	
	//Get StoryStatus By Id
	public Optional<StoryStatus> findById(int id) {
		return StoryStatusRepository.findById(id);
	}	
	
	//Delete StoryStatus
	public void delete(int id) {
		StoryStatusRepository.deleteById(id);
	}
	
	//Update StoryStatus
	public void save(StoryStatus StoryStatus) {
		StoryStatusRepository.save(StoryStatus);
	}

}

