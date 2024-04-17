package com.app.service;

import javax.persistence.Entity;

import com.app.exception.UserException;
import com.app.model.User;


public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
    

}
