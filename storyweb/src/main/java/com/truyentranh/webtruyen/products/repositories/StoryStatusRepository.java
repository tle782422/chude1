package com.truyentranh.webtruyen.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truyentranh.webtruyen.products.models.StoryStatus;

@Repository
public interface StoryStatusRepository extends JpaRepository<StoryStatus, Integer> {

}
