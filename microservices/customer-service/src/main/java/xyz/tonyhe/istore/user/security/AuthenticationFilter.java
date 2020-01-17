package xyz.tonyhe.istore.user.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import xyz.tonyhe.istore.user.model.LoginRequestModel;
import xyz.tonyhe.istore.user.service.UserService;
import xyz.tonyhe.istore.user.shared.UserDto;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private UserService userService;
	private Environment env;
	
	
	public AuthenticationFilter(
			UserService userService, 
			Environment env, 
			AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
		this.userService = userService;
		this.env = env;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
		try {
			LoginRequestModel requestModel = new ObjectMapper()
					.readValue(request.getInputStream(), LoginRequestModel.class);
			return this.getAuthenticationManager().authenticate(
						new UsernamePasswordAuthenticationToken(
								requestModel.getEmail(), 
								requestModel.getPassword(),
								new ArrayList<>())
					);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, 
											HttpServletResponse res,
											FilterChain chain,
											Authentication auth) throws IOException, ServletException{
		String username = ((User) auth.getPrincipal()).getUsername();
		UserDto userDetail = userService.getUserDetailsByEmail(username);
		
		String token = Jwts.builder()
						.setSubject(userDetail.getUserId())
						.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
						.signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
						.compact();
		
		res.addHeader("token", token);
		res.addHeader("userId", userDetail.getUserId());
								
	}
}
