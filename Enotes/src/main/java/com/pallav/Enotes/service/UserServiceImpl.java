package com.pallav.Enotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pallav.Enotes.entity.User;
import com.pallav.Enotes.repo.UserRepo;

import jakarta.servlet.http.HttpSession;

@Component
public class UserServiceImpl implements UserService {
     @Autowired
     private UserRepo userRepo;
	  @Autowired
	  private BCryptPasswordEncoder passwordEncoder;
     
     @Override
 	public User saveUser(User user) {
 		user.setRole("ROLE_USER");
 		user.setPassword(passwordEncoder.encode(user.getPassword()));
 		User newUser = userRepo.save(user);
 		return newUser;
 	}

	@Override
	public boolean existEmailCheck(String email) {
		return userRepo.existsByEmail(email);
		
	}
	
	public void removeSession()
	{

		 HttpSession session= ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).
		  getRequest().getSession();
	 session.removeAttribute("msg");
	}

   

}
