package com.fileapi.dao;

import java.util.List;

import com.fileapi.dto.FileMetaData;
import com.fileapi.exception.FileOperationException;

public interface FileOperationDAO {
	
	public FileMetaData findById(String Id) throws FileOperationException;
	
	public List<FileMetaData> findAll() throws FileOperationException;
	
	public FileMetaData save(FileMetaData fileMetaData) throws FileOperationException;

}
