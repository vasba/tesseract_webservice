package com.ocr.tesseractservice.rest.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Controller
public class RestController {

		public RestController() {
	    }

	    @PostMapping("/ocr")	    
	    public  @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException, TesseractException {
	    	InputStream imageStream = file.getInputStream();
	    	ITesseract instance = new Tesseract();  // JNA Interface Mapping
	    	String tessdataPath = System.getProperty("tessdata");
	    	String tesslang = System.getProperty("tesslang");
	    	instance.setLanguage(tesslang);	    	
	    	instance.setDatapath(tessdataPath);
	    	BufferedImage imBuff = ImageIO.read(imageStream);
	    	String content = instance.doOCR(imBuff);
	        
	        return content;
	    }
}
