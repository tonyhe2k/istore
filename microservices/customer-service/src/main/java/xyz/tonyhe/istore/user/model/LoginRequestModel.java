package xyz.tonyhe.istore.user.model;

import lombok.Data;

@Data
public class LoginRequestModel {

	private String email;
	private String password;
}
