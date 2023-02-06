package com.truyentranh.webtruyen.parameters.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truyentranh.webtruyen.parameters.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
