package com.truyentranh.webtruyen.parameters.repositories;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.truyentranh.webtruyen.parameters.models.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

}
