package com.fileapi.exception;

public class FileOperationException extends RuntimeException {

	public FileOperationException(String message) {
        super(message);
    }

    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
