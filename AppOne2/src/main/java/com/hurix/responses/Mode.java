package com.hurix.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Mode {
	
	private String mode;
	private String ip;
	private String username;
	private String password;
	private String location;
	private String bucket;
	private String accessKey;
	private String secretKey;
	private String csUrl;
	private String s3endpoint;
	
}