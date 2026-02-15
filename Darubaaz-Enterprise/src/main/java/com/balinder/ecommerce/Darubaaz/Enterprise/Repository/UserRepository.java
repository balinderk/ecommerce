package com.balinder.ecommerce.Darubaaz.Enterprise.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balinder.ecommerce.Darubaaz.Enterprise.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	
	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
	

}
