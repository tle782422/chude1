package com.truyentranh.webtruyen.security.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.truyentranh.webtruyen.security.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	
	@Query(value="SELECT user FROM user user where user.first_name = ?1 and user.last_name = ?2",nativeQuery = true)
	User findByFirstnameAndLastname(String firstname, String lastname);

	@Query("UPDATE User u SET u.failedLoginAttempts = ?1 WHERE u.email = ?2")
    @Modifying
    public void updateFailedAttempts(int failAttempts, String email);
	
	
}
