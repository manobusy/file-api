package com.fileapi.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fileapi.exception.FileOperationException;
import com.fileapi.repository.DBFileRepository;

@Service
public class DataLoaderService {
	
	private static final Logger log = LoggerFactory.getLogger(DataLoaderService.class);
	
	@Autowired
	private DBFileRepository dbFileRepository;
	
	//constructor
	public DataLoaderService() {};

	@PostConstruct
	public void init() throws FileOperationException{
		deleteReordsInDB();
	}
	
	//Load the products in DB
	private void deleteReordsInDB() {
		log.info("Deleting all the product records");
		dbFileRepository.deleteAll();
	}

}
