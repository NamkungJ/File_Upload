package com.jjundol.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {
	
	// form을 활용한 파일업로드
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("[UploadController]uploadForm......");
	}
	
	// Ajax를 활용한 파일업로드
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("[UploadController]uploadAjax......");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile, Model model) {
		
		String uploadDir = "C:\\upload";
		
		for(MultipartFile multipartFile : uploadFile) {			
			log.info("-------------------uploadFormAction---------------------");
			log.info("File Name : " + multipartFile.getOriginalFilename());
			log.info("File Size : " + multipartFile.getSize());
			
			File saveFile = new File(uploadDir, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException e) {			
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxAction(MultipartFile[] uploadFile) {
		
		String uploadDir = "C:\\upload\\temp";
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("-------------------uploadAjaxAction---------------------");			
			log.info("File Name : " + multipartFile.getOriginalFilename());
			log.info("File Size : " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			
			File saveFile = new File(uploadDir, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException e) {			
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
