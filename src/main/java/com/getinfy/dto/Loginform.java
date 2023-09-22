package com.getinfy.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Loginform {
	
	@NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
	private String staffEmail;

	@NotEmpty(message = "password is required")
	private String newPwd;

}
