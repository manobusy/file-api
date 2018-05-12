package com.fileapi.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.fileapi.dto.FileMetaData;
import com.fileapi.exception.FileOperationException;

public interface FileOperationService {
	
	public String saveFile(MultipartFile file) throws FileOperationException;
	
	public Resource getFile(String fileName) throws FileOperationException;
	
	public boolean saveFileMetaData(FileMetaData fileMetaData) throws FileOperationException;
	
	public List<FileMetaData> getAllFileMetaData() throws FileOperationException;
	
	public FileMetaData getFileMetaDataById(String id) throws FileOperationException;

}
