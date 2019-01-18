package com.hurix.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hurix.model.dto.ClientAuthDTO;

@Component
public class AuthClientImpl implements AuthClient {

	
	private RestTemplate restTemplate;
	
	public AuthClientImpl(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}



	@Override
	public ClientAuthDTO authenticateClient(String username, String password) {
		
		
		String requestEntity= "{\"user\":{ \"userName\":\""+username+ "\", \"password\":\""+password+"\" } }";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		ResponseEntity<ClientAuthDTO> clientResponse = null;
		URI uri;
		try {
		
			String url = "http://qc.kitaboo.com/DistributionServices/services/api/educator/user/123234234/IPAD/authenticateUser";
				uri = new URI(url);
			HttpEntity<String> request = new HttpEntity<String>(requestEntity, headers);
			
			clientResponse = restTemplate.postForEntity(uri, request, ClientAuthDTO.class);
			System.out.println("Client Responose: "+clientResponse.getBody());

		} catch (URISyntaxException e) {
//			e.printStackTrace();
		}
		
		return clientResponse.getBody();
	}

}
