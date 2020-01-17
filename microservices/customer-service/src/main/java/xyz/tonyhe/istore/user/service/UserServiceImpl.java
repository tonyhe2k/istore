package xyz.tonyhe.istore.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import xyz.tonyhe.istore.user.controller.UserController;
import xyz.tonyhe.istore.user.data.AlbumsServiceClient;
import xyz.tonyhe.istore.user.model.AlbumResponseModel;
import xyz.tonyhe.istore.user.model.UserRequestModel;
import xyz.tonyhe.istore.user.persistence.UserRepository;
import xyz.tonyhe.istore.user.persistence.entity.UserEntity;
import xyz.tonyhe.istore.user.shared.UserDto;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	AlbumsServiceClient albumsServiceClient;
	
	@Autowired
	Environment environment;

	@Override
	public UserDto createUser(UserDto userDetails) {
		
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(encoder.encode(userDetails.getPassword()));
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity entity = mapper.map(userDetails, UserEntity.class);
		
		repo.save(entity);
		
		UserDto returnValue = mapper.map(entity, UserDto.class);
		
		return returnValue;
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = repo.findByEmail(username);
		
		if(userEntity == null) throw new UsernameNotFoundException(username);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity = repo.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = repo.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException("User not found");
		
		UserDto userDto =new ModelMapper().map(userEntity, UserDto.class);
		
		List<AlbumResponseModel> albumsList;

		log.info("Before calling albums Microservice");
		albumsList = albumsServiceClient.getAlbums(userId);
		log.info("After calling albums Microservice");

		userDto.setAlbums(albumsList);
		
		return userDto;
	}

}
