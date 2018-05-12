package com.java.fileapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.java.fileapi.property.FileOperationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileOperationProperties.class
})
public class FileApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileApiApplication.class, args);
	}
}
