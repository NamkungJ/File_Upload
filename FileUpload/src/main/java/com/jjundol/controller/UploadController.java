package com.jjundol.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		
		// 일일 디렉토리 생성
		File uploadPath = new File(uploadDir, getDailyDirName());
		if(!uploadPath.exists()) {
			uploadPath.mkdir();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("-------------------uploadAjaxAction---------------------");			
			log.info("File Name : " + multipartFile.getOriginalFilename());
			log.info("File Size : " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			
			// 파일명 중복방지
			UUID uuid = UUID.randomUUID();					
			uploadFileName =	uuid.toString() + "_" + uploadFileName;
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException e) {			
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private String getDailyDirName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String dirName = sdf.format(date);
		return dirName; // 20200306
	}
	
}
