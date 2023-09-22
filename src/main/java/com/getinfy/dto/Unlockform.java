package com.getinfy.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Unlockform {
    
	@NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
	private String staffEmail;

	@NotEmpty(message = "temprory password is required")
	private String tempPwd;

	@NotEmpty(message = "New password is required")
	private String newPwd;
	
	@NotEmpty(message = "Confirm password is required")
	private String confirmPwd;

}
