package com.hurix.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.hurix.client.AuthClient;
import com.hurix.model.dto.ClientAuthDTO;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
	@Autowired
	private AuthClient auth;
	
	@Autowired
	HttpSession session;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
  
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
         
        System.out.println("user name from security is: "+name);
		System.out.println("Password  from security is: "+password);
		
		ClientAuthDTO clientResponse = auth.authenticateClient(name, password);
		
        	if(clientResponse.getResponseCode() != 200) { 
        		 throw new BadCredentialsException("Invalid Credentials");
			}
		
        	session.setAttribute("cliendID", clientResponse.getClientID());
		 return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
}