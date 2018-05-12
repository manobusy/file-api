package com.java.fileapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fileapi/v1")
public class FileController {

	@RequestMapping("/landing")
	public String landing(){
		return "Welcome to File Operation API Page";
	}
}
