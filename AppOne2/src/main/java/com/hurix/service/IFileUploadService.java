package com.hurix.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {

	public String uploadFile(Long clientId , MultipartFile file);
} 
