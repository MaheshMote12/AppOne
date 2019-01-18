package com.hurix.util;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.hurix.responses.Config;

@Component
public class AmazonUtil {
	
	
	public String uploadFile(String folderPath, Resource multipartFile, Config config) {
		
		AWSCredentials awsCredentials = new BasicAWSCredentials(config.getOnline_transfer().getAccessKey(), config.getOnline_transfer().getSecretKey());
		ClientConfiguration cc = new ClientConfiguration();
		cc.setProtocol(Protocol.HTTP);
		cc.setMaxConnections(3);
		cc.setMaxErrorRetry(5);
	
		AmazonS3 s3 = new AmazonS3Client(awsCredentials,cc);
		
		
		
		
		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
		if(null!=config.getOnline_transfer().getS3endpoint())
			s3.setEndpoint(config.getOnline_transfer().getS3endpoint());
		
		System.out.println("Amazon file path: "+multipartFile.getFilename());
		PutObjectResult result = null;
		try {
			result = s3.putObject(new PutObjectRequest(config.getOnline_transfer().getBucket(), folderPath, multipartFile.getFile()).withAccessControlList(acl));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return result.getETag();
		
	}
}
