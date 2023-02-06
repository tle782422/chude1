package com.truyentranh.webtruyen.hr.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truyentranh.webtruyen.hr.models.EmployeeStatus;

@Repository
public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus, Integer> {
}
