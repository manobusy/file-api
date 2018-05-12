package com.fileapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fileapi.response.FileUploadResponse;
import com.fileapi.service.FileOperationService;

@RestController
@RequestMapping("/fileapi/v1")
public class FileController {
	
	private static final Logger log = LoggerFactory.getLogger(FileController.class);

	/*@RequestMapping("/landing")
	public String landing(){
		return "Welcome to File Operation API Page";
	}*/
	
	@Autowired
    private FileOperationService fileOperationService;
	
	@PostMapping("/upload")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileOperationService.saveFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloads/")
                .path(fileName)
                .toUriString();

        return new FileUploadResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
}
