package com.pallav.Enotes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pallav.Enotes.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>  {


	public boolean existsByEmail(String email);

	public User findByEmail(String email);
}
