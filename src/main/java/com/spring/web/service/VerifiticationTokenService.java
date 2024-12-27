package com.spring.web.service;

import org.springframework.stereotype.Service;

import com.spring.web.model.User;
import com.spring.web.model.VerificationToken;

@Service
public interface VerificationTokenService {

	VerificationToken getToken(String token);
	VerificationToken createToken(User user, String token);
}
