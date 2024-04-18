package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.config.JwtProvider;
import com.app.exception.UserException;
import com.app.model.User;
import com.app.repository.UserRepository;
@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtProvider jwtProvider;
	@Override
	public User findUserById(Long userId) throws UserException {
		// TODO Auto-generated method stub
		Optional<User>user=userRepository.findById(userId);
		if(user.isPresent())
		{
			return user.get();
		}
		throw new UserException("user not found with id "+userId);

	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		// TODO Auto-generated method stub
		String email=jwtProvider.getEmailFromToken(jwt);
		User user=userRepository.findByEmail(email);
		if(user==null)
		throw new UserException("user not found with email "+email);
		return user;
	}
	

}
