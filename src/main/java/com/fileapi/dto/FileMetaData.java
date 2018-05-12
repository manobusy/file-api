package com.fileapi.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "filemetadata" )
public class FileMetaData {
	
	@Id
	private String id;
	
	private String fileName;
	
	private String fileContentType;
	
	private long fileSize;
	
	
	public FileMetaData(){
		super();
	}
	
	public FileMetaData(String id, String fileName, String fileContentType, long fileSize) {
		this.id = id;
		this.fileName = fileName;
		this.fileContentType = fileContentType;
		this.fileSize = fileSize; 
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

		
}
