package xyz.tonyhe.istore.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRequestModel {
	
	@NotNull(message="Username cannot be empty")
	@Size(min=2, message="Username must not be less than two characters")
	private String username;
	
	@NotNull(message="First Name cannot be empty")
	@Size(min=2, message="First name must not be less than two characters")
	private String firstName;
	
	@NotNull(message="Last Name cannot be empty")
	@Size(min=2, message="Last name must not be less than two characters")
	private String lastName;
	
	@NotNull(message="Password cannot be empty")
	@Size(min=8, max=16, message="Password is invalid")
	private String password;
	
	@NotNull(message="Password cannot be empty")
	@Email(message="invlaid email format")
	private String email;
	

}
