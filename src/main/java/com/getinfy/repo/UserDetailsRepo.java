package com.getinfy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getinfy.entity.UserDetailsEntity;

public interface UserDetailsRepo extends JpaRepository<UserDetailsEntity,Integer>{
	
	public UserDetailsEntity findBystaffEmail(String staffEmail);

	public UserDetailsEntity findBystaffEmailAndNewPwd(String staffEmail,String newPwd);

}
