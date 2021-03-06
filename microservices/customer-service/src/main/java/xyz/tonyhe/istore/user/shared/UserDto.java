package xyz.tonyhe.istore.user.shared;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import xyz.tonyhe.istore.user.model.AlbumResponseModel;

@Data
public class UserDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;

	private String username;
	
	private String firstName;

	private String lastName;

	private String password;
	
	private String email;
	
	private String encryptedPassword;
	
	private List<AlbumResponseModel> albums; 
}
