package com.truyentranh.webtruyen.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.products.models.StoryMaintenance;
import com.truyentranh.webtruyen.products.repositories.StoryMaintenanceRepository;



@Service
public class StoryMaintenanceService {

	@Autowired
	private StoryMaintenanceRepository StoryMaintenanceRepository;
	
	//Get All StoryMaintenances
	public List<StoryMaintenance> findAll(){
		return StoryMaintenanceRepository.findAll();
	}	
	
	//Get StoryMaintenance By Id
	public StoryMaintenance findById(int id) {
		return StoryMaintenanceRepository.findById(id).orElse(null);
	}	
	
	//Delete StoryMaintenance
	public void delete(int id) {
		StoryMaintenanceRepository.deleteById(id);
	}
	
	//Update StoryMaintenance
	public void save(StoryMaintenance StoryMaintenance) {
		StoryMaintenanceRepository.save(StoryMaintenance);
	}
}
