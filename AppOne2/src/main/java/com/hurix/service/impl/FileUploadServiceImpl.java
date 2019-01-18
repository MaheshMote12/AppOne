package com.hurix.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hurix.repository.ClientRepository;
import com.hurix.responses.Config;
import com.hurix.service.IFileUploadService;
import com.hurix.util.AmazonUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileUploadServiceImpl implements IFileUploadService {

	private final Path rootLocation = Paths.get("filestorage").resolve("files");

	private ClientRepository repo;
	private AmazonUtil amazonUtil;

	public FileUploadServiceImpl(ClientRepository repo, AmazonUtil amazonUtil) {
		super();
		this.repo = repo;
		this.amazonUtil = amazonUtil;
	}

	@Override
	public String uploadFile(Long clientId, MultipartFile multipartFile) {

		String transferDetails = repo.getOne(clientId).getTransfer_details();

		Config clientConfig;

		try {
			JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance(Config.class);
			Unmarshaller unmarshaller;
			unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(transferDetails.trim());
			clientConfig = (Config) unmarshaller.unmarshal(reader);

			log.info("Client json: " + transferDetails);
			log.info("Client Obj" + clientConfig);

			String folderPath = "/files/1/" +  multipartFile.getOriginalFilename();
			System.out.println("folder path: " + folderPath);

			Resource resource;

			try {

				if (!Files.exists(rootLocation)) {
					Files.createDirectories(rootLocation);
				}

				Files.copy(multipartFile.getInputStream(), rootLocation.resolve(multipartFile.getOriginalFilename()));

				String savedFileName = multipartFile.getOriginalFilename().toString();
				Path path = rootLocation.resolve(savedFileName);

				System.out.println("File name from path " + path.getFileName());

				resource = new UrlResource(path.toUri());
				
				String eTag = amazonUtil.uploadFile(folderPath, resource, clientConfig);
				
				System.out.println("eTag is: ===>>"+eTag);

				/**delete file after upload done **/
				
//				FileSystemUtils.deleteRecursively(rootLocation.resolve(multipartFile.getOriginalFilename()));
				
			} catch (IOException e) {
				throw new RuntimeException("FAIL! -> message! = " + e.getMessage());
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return "";
	}

}
