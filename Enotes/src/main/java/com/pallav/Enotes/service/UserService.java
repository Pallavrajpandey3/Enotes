package com.pallav.Enotes.service;

import com.pallav.Enotes.entity.User;

public interface UserService {
 
    public User saveUser(User user);

	public boolean existEmailCheck(String email);
    
    

	
	
}
