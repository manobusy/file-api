package com.fileapi.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.fileapi.exception.FileOperationException;

public interface FileOperationService {
	
	public String saveFile(MultipartFile file) throws FileOperationException;
	
	public Resource getFile(String fileName) throws FileOperationException;

}
