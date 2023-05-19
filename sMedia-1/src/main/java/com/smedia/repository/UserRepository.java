package com.smedia.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smedia.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	
	Optional<Users> findByEmail(String email);
	
	Optional<Users> findByUserName(String userName);

	Optional<Users> findByUserNameOrEmail(String userName,String email);
	
	Boolean existsByUserName(String UserName);
	
	Boolean existsByEmail(String Email);

}
