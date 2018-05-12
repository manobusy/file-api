package com.fileapi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fileapi.dto.FileMetaData;
import com.fileapi.exception.FileOperationException;
import com.fileapi.repository.DBFileRepository;


@Component
public class FileOperationDAOImpl implements FileOperationDAO{
	
		@Autowired
		private DBFileRepository dbFileRepository;	
		
		//Find All
		public List<FileMetaData> findAll() throws FileOperationException {
			List<FileMetaData> fileMetaData = dbFileRepository.findAll(); 
			return fileMetaData;
		}
		
		//Find By Id
		public FileMetaData findById(String id) throws FileOperationException {
			FileMetaData fileMetaData = dbFileRepository.findFileMetaDataById(id);
			return fileMetaData;
		}
		
		//Save
		public FileMetaData save(FileMetaData fileMetaData)  throws FileOperationException{
			dbFileRepository.save(fileMetaData);
			return fileMetaData;
		}

}
