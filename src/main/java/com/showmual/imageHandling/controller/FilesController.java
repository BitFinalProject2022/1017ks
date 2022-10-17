package com.showmual.imageHandling.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.showmual.imageHandling.model.Files;
import com.showmual.imageHandling.service.FilesService;

@Controller
public class FilesController {
	
	@Autowired
	FilesService filesService;

	@RequestMapping("/upload")
	public String Insert() {
		
		return "upload";
	}
	
	@RequestMapping("/fileUpload")
	public String fileinsert(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception{
		Files file = new Files();
		
		String sourceFileName = files.getOriginalFilename(); 
        		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
        		File destinationFile; 
        		String destinationFileName;
        		String fileUrl = "E:/Project/images/";
        		
        		do { 
            			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension; 
            			destinationFile = new File(fileUrl + destinationFileName); 
        		} while (destinationFile.exists()); 
        
        		destinationFile.getParentFile().mkdirs(); 
        		files.transferTo(destinationFile);
        
        		file.setFilename(destinationFileName);
        		file.setFileOriName(sourceFileName);
        		file.setFileurl(fileUrl);
        		filesService.save(file);
			return "redirect:/upload";
	}
	
	// 사진 보기
//	@RequestMapping("/show")
//	public String index5(Model model) {
//		model.addAttribute("file", filesService.findByFno(8));
//		return "showImage";
//	}
}
