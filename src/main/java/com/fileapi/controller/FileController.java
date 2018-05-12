package com.fileapi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        return new FileUploadResponse(fileName, file.getContentType(), file.getSize());
    }
	
	@GetMapping("/download/{fileName:.+}")
 	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileOperationService.getFile(fileName);

        // Try to determine file's content type to check valid or invalid file
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
