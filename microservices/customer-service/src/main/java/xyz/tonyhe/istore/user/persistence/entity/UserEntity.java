package xyz.tonyhe.istore.user.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="users")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5846545081628010569L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, length=50)
	private String firstName;
	
	@Column(nullable = false, length=50)
	private String lastName;
	
	@Column(nullable = false, length=120, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String userId;
	
	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String encryptedPassword;
	
}
