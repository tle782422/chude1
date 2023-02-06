package com.truyentranh.webtruyen.parameters.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truyentranh.webtruyen.parameters.models.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
