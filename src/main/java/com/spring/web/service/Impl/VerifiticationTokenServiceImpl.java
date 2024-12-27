package com.spring.web.service.Impl;

import org.springframework.stereotype.Service;

import com.spring.web.exceptions.TokenNotFound;
import com.spring.web.model.User;
import com.spring.web.model.VerificationToken;
import com.spring.web.repository.VerificationTokenRepository;
import com.spring.web.service.VerificationTokenService;


@Service
public class VerifiticationTokenServiceImpl implements VerificationTokenService {

	private final VerificationTokenRepository repository;
	
	public VerifiticationTokenServiceImpl(VerificationTokenRepository repository) {
		
		this.repository = repository;
	}
	
	
	@Override
	public VerificationToken getToken(String token) {
		
		
		
		try {
			return repository.findByToken(token);
		}catch(TokenNotFound e)  {
			return null;
		}
		
	}

	@Override
	public VerificationToken createToken(User user, String token) {
		VerificationToken t = new VerificationToken();
		
		t.setUser(user);
		t.setToken(token);
		repository.save(t);
		return t;
		
	}

	
}
