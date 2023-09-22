package com.getinfy.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getinfy.dto.Loginform;
import com.getinfy.dto.SignUpform;
import com.getinfy.dto.Unlockform;
import com.getinfy.entity.UserDetailsEntity;
import com.getinfy.repo.UserDetailsRepo;
import com.getinfy.service.UserService;
import com.getinfy.utils.EmailUtils;
import com.getinfy.utils.Passwordutils;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	public UserDetailsRepo repo;

	@Autowired
	public Passwordutils pwd;

	@Autowired
	public EmailUtils email;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceimpl.class);

	@Override
	public String login(Loginform login) {
		
		
		logger.info("Attempting login for user: ", login.getStaffEmail());

		UserDetailsEntity entity = repo.findBystaffEmailAndNewPwd(login.getStaffEmail(), login.getNewPwd());
		
		if (entity == null) {
			logger.info("Login failed for user: ", login.getStaffEmail());
			return "Invalid Credentials";
		}
		if (entity.getAccountStatus().equals("Locked")) {
			 logger.info("User account is locked for:", login.getStaffEmail());
			return "your Account is Locked";

		}
		logger.info("User login successful for:", login.getStaffEmail());
		return "Success";

	}

	@Override
	public boolean UserSignup(SignUpform user) {

		UserDetailsEntity findBystaffEmail = repo.findBystaffEmail(user.getStaffEmail());

		if (findBystaffEmail != null) {
			return false;

		}

		UserDetailsEntity entity = new UserDetailsEntity();

		BeanUtils.copyProperties(user, entity);

		String generatePassword = pwd.generatePassword();

		entity.setNewPwd(generatePassword);
		entity.setAccountStatus("Locked");

		repo.save(entity);

		String staffEmail = entity.getStaffEmail();

		String to = staffEmail;

		String subject = "Thank you for register with us !!!";

		String body = "<h1> Please use below password to unlock your account</h1>"
				+ "<p>Your temporary password is:<strong>" + generatePassword + "</string></p>";

		email.sendEmail(subject, body, to);
		logger.info("User registered successfully:", user.getStaffEmail());
		return true;

	}

	public boolean Unlock(Unlockform unlock) {

		UserDetailsEntity entity = repo.findBystaffEmail(unlock.getStaffEmail());

		if (unlock.getTempPwd().equals(entity.getNewPwd())) {

			entity.setAccountStatus("unlock");

			entity.setNewPwd(unlock.getNewPwd());

			repo.save(entity);
			logger.info("User account unlocked:", unlock.getStaffEmail());
			return true;

		}

		return false;

	}

	@Override
	public boolean forgot(String emails) {

		UserDetailsEntity entity = repo.findBystaffEmail(emails);

		if (entity == null) {
			return false;

		}

		String subject = "Recover Password";
		String body = "your pwd:" + entity.getNewPwd();

		email.sendEmail(subject, body, emails);
		 logger.info("Password recovery email sent to:", emails);
		return true;

	}

}
