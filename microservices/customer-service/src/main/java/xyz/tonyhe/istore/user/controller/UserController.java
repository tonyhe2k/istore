package xyz.tonyhe.istore.user.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import xyz.tonyhe.istore.user.model.UserRequestModel;
import xyz.tonyhe.istore.user.model.UserResponseModel;
import xyz.tonyhe.istore.user.service.UserService;
import xyz.tonyhe.istore.user.shared.UserDto;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserService userService;

	@GetMapping("/status/check")
	public String status() {
		log.info("user working on port " + env.getProperty("local.server.port"));
		return "user working on port " + env.getProperty("local.server.port") 
				+ ", with token = " + env.getProperty("token.secret");
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserRequestModel userRequest) {
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = mapper.map(userRequest, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);
		
		UserResponseModel responseModel = mapper.map(createdUser, UserResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
	}
	
	@GetMapping(value="/{userId}")
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {
		log.info("calling getUser");
		UserDto userDto = userService.getUserByUserId(userId);
		UserResponseModel returnValue = new ModelMapper().map(userDto, UserResponseModel.class);
				
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
}
