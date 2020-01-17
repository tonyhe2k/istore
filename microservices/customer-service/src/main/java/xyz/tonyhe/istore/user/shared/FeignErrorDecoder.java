package xyz.tonyhe.istore.user.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder{

	@Autowired
	Environment environment;
	
	@Override
	public Exception decode(String methodKey, Response response) {
		switch(response.status()) {
		case 400:
			break;
		case 404:
		{
			if(methodKey.contains("getAlbums")) {
				return new ResponseStatusException(
							HttpStatus.valueOf(response.status()), "user album not found"
						);
			}
			break;
		}
		}
		return null;
	}

}
