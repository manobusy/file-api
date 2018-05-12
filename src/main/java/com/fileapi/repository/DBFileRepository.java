package com.fileapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fileapi.dto.FileMetaData;

@Repository
public interface DBFileRepository extends MongoRepository <FileMetaData,String> {
    
	public FileMetaData findById(int id);	
	
	public FileMetaData findFileMetaDataById(String id);	
	
}
