package com.getinfy.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SignUpform {
	
	
	@NotEmpty(message = "Name is required")
	private String staffName;

	@NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
	private String staffEmail;

	@NotEmpty(message = "Phone number is required")
	private String staffPhoneNum;

}
