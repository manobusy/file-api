package com.fileapi.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fileapi.exception.FileNotFoundException;
import com.fileapi.exception.FileOperationException;
import com.fileapi.property.FileOperationProperties;

@Service
public class FileOperationServiceImpl implements FileOperationService{
	
	private final Path fileSaveLocation;

    @Autowired
    public FileOperationServiceImpl(FileOperationProperties fileOperationProperties) {
        this.fileSaveLocation = Paths.get(fileOperationProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileSaveLocation);
        } catch (Exception ex) {
            throw new FileOperationException("Cannot create the directory where the uploaded files will be saved.", ex);
        }
    }

	@Override
	public String saveFile(MultipartFile file) throws FileOperationException {
		// Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileOperationException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileSaveLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileOperationException("Could not save file " + fileName + ". Please try again!", ex);
        }
	}

	@Override
	public Resource getFile(String fileName) throws FileOperationException {
		try {
            Path filePath = this.fileSaveLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
	}

}
