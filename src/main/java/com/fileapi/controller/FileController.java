package com.fileapi.controller;

import java.io.IOException;
import java.util.List;

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

import com.fileapi.dto.FileMetaData;
import com.fileapi.exception.FileOperationException;
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
        //Upload the file to specified location
		String fileName = fileOperationService.saveFile(file);
		
		//Save the file into DB
		saveFileIntoDB(file);
		
		//Return the response
        return new FileUploadResponse(fileName, file.getContentType(), file.getSize());
    }
	
	private boolean saveFileIntoDB(MultipartFile file) {
		FileMetaData fileMetaData = new FileMetaData();
		
		fileMetaData.setFileName(file.getName());
		fileMetaData.setFileContentType(file.getContentType());
		fileMetaData.setFileSize(file.getSize());
		
		boolean saveStatus = fileOperationService.saveFileMetaData(fileMetaData);
		
		//Save DB status logging
		if(saveStatus){
			log.info("File Meta Data Saved successfully");
		}else{
			log.info("File Meta Data Not Saved successfully");
		}
		return saveStatus;
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
	
	@GetMapping(value = "/filemetadatas")
	public List<FileMetaData> getAllFileMetaData() throws FileOperationException{
		return fileOperationService.getAllFileMetaData();
	}
	
	@GetMapping(value = "/filemetadata/{id}")
	public FileMetaData getProductById(@PathVariable("id") String id) throws FileOperationException{
		return fileOperationService.getFileMetaDataById(id);
	}
}
