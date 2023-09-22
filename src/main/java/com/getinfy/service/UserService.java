package com.getinfy.service;

import com.getinfy.dto.Loginform;
import com.getinfy.dto.SignUpform;
import com.getinfy.dto.Unlockform;

public interface UserService {
	
	
	public boolean UserSignup(SignUpform user);

	 public boolean Unlock(Unlockform unlock);

	public String login(Loginform login);

	public boolean forgot(String email);

}
