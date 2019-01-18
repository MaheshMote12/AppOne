package com.hurix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.hurix.repository.ClientRepository;
import com.hurix.service.IFileUploadService;

@SpringBootApplication
@EntityScan(basePackages="com.hurix.model")
public class AppOneApplication implements ApplicationRunner {

	@Autowired
	private ClientRepository repo;
	
	@Autowired
	IFileUploadService s;
	
	public static void main(String[] args) {
		SpringApplication.run(AppOneApplication.class, args);
	}  

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Transactional
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	 
		
		
		repo.findAll().stream().findAny().ifPresent(a -> System.out.println(a.getTransfer_details()));
	
	}
	
}

