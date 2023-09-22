package com.getinfy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="userdetails_tbl")
public class UserDetailsEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer staffId;
    
	@Column(name = "Name",nullable = false)
	private String staffName;

	
	@Column(name = "Email", unique = true,nullable = false)
	private String staffEmail;

	@Column(name = "PhoneNumber",nullable = false)
	private String staffPhoneNum;

	@Column(name = "Password",nullable = false)
	private String newPwd;

	@Column(name = "AccountStatus",nullable = false)
	private String accountStatus;

}
