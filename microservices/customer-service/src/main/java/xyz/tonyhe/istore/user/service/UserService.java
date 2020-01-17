package xyz.tonyhe.istore.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import xyz.tonyhe.istore.user.model.UserRequestModel;
import xyz.tonyhe.istore.user.shared.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto userDetails);
	
	UserDto getUserDetailsByEmail(String email);

	UserDto getUserByUserId(String userId);
}
