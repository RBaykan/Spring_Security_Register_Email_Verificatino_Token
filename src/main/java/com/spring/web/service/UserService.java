package com.spring.web.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.web.dto.UserDTO;


@Service
public interface UserService {
	List<UserDTO> getAllUser();
	UserDTO registerNewUser(UserDTO user);
	String confirmRegistration(String token);


}
