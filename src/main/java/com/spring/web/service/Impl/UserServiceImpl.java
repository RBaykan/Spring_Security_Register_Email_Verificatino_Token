package com.spring.web.service.Impl;




import java.util.Calendar;
import java.util.List;

import java.util.stream.Collectors;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.spring.web.dto.UserDTO;
import com.spring.web.event.OnRegistrationCompleteEvent;
import com.spring.web.exceptions.EmailAlReady;
import com.spring.web.exceptions.UserAlReady;
import com.spring.web.mapper.UserMapper;
import com.spring.web.model.User;
import com.spring.web.model.VerificationToken;
import com.spring.web.repository.UserRepository;
import com.spring.web.repository.VerificationTokenRepository;
import com.spring.web.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepostiory;
	private final ApplicationEventPublisher publisher;
	
	public UserServiceImpl(UserRepository userRepository,
						   VerificationTokenRepository verificationTokenRepostiory,
			ApplicationEventPublisher publisher) {
		
		this.userRepository = userRepository;
		this.verificationTokenRepostiory = verificationTokenRepostiory;
		this.publisher = publisher;
	}
	
	

	@Override
	public List<UserDTO> getAllUser() {
		
		List<User> users = userRepository.findAll();
		return users.stream().map(
				(user) -> UserMapper.ModeltoDto(user)
				).collect(Collectors.toList());
	}


	@Override
	public UserDTO registerNewUser(UserDTO user) throws EmailAlReady, UserAlReady{
		
		if(emailExit(user.getEmail())) {
			
			
			throw new EmailAlReady();
			 
		}
		
		if(userExit(user.getUsername())) {
		
			throw new UserAlReady();
		}
		
		User newUser = UserMapper.DTOtoModel(user);
		
		
		
		publisher.publishEvent(new OnRegistrationCompleteEvent(newUser, "http://localhost:8080/api/user/registrationConfirm"));
		
		userRepository.save(newUser);
		
		return UserMapper.ModeltoDto(newUser);
		
	}
	

	
	private boolean emailExit(String email) {
	
		return userRepository.findByEmail(email) != null; 
		
	}
	
	

	
	private boolean userExit(String username) {
		
		return userRepository.findByUsername(username) != null; 
		
		
	}
	
	@Override
	public String confirmRegistration(String token){

		VerificationToken t = verificationTokenRepostiory.findByToken(token);
		
		if(t == null) {
			return "invaild token";
		}
			
		
		
		Calendar cal = Calendar.getInstance();
		
		if(t.getExpiryDate().getTime() - cal.getTime().getTime() <=0) {
			
			return "expired token";
		}
		
		User user = t.getUser();
		user.setEnabled(true);
		userRepository.save(user);
		
		return user.toString() + "Accounnt is enabled";
	}







}
