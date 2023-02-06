package com.truyentranh.webtruyen.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truyentranh.webtruyen.products.models.StoryType;

@Repository
public interface StoryTypeRepository extends JpaRepository<StoryType, Integer> {

}
