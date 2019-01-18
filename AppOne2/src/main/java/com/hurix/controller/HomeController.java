package com.hurix.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.hurix.client.AuthClient;
import com.hurix.service.IFileUploadService;
import com.hurix.util.FileStorage;

@Controller
//@RequestMapping("/")
public class HomeController {

	private RestTemplate restTemplate;
	private IFileUploadService fileUploadService;
	private AuthClient auth;

	@Autowired
	FileStorage fileStorage;

	public HomeController(RestTemplate restTemplate, IFileUploadService fileUploadService, AuthClient auth) {
		super();
		this.restTemplate = restTemplate;
		this.fileUploadService = fileUploadService;
		this.auth = auth;
	}

	@GetMapping(value = { "/", "/login" })
	public String indexPage(@RequestParam(value = "error", required = false) boolean error,
			@RequestParam(value = "logout", required = false) boolean logout, Model model) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return "index";
	}


	@GetMapping("/uploadFile")
	public String uploadPage(Model model) {
		return "fileUpload";
	}

	@PostMapping("/uploadFile")
	public String uploadFile(Model model, @RequestParam("file") MultipartFile multipartFile, HttpSession session) {

		if (multipartFile != null && !multipartFile.getOriginalFilename().contains("xlsx")
				&& !multipartFile.getOriginalFilename().contains("xls")) {
			model.addAttribute("invalidFile", "Please enter valid file format. Only .xlsx, .xls are valid.");
			System.out.println(multipartFile.getOriginalFilename());
			return "fileUpload";
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		System.out
				.println("file name and size: " + multipartFile.getOriginalFilename() + ": " + multipartFile.getSize());
		fileUploadService.uploadFile((Long) session.getAttribute("cliendID"), multipartFile);

		model.addAttribute("success", "File Uploaded");
		return "fileUpload";
	}

	@GetMapping("/files")
	public String getListFiles(Model model) {

		List<FileInfo> fileInfos = fileStorage.loadFiles().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(this.getClass(), "downloadFile", path.getFileName().toString()).build().toString();

//						 return new FileInfo(filename, url); 
			return new FileInfo();
		}).collect(Collectors.toList());

		model.addAttribute("files", fileInfos);
		return "multipartfile/listfiles";
	}

	/*
	 * Download Files
	 */

	@GetMapping("/files/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
		Resource file = fileStorage.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
