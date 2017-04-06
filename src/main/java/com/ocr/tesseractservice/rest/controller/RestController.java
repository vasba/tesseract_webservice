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
	 private Object storageService;


	    public RestController() {
	    }

//	    @GetMapping("/")
//	    public String listUploadedFiles(Model model) throws IOException {
//
//	        model.addAttribute("files", storageService
//	                .loadAll()
//	                .map(path ->
//	                        MvcUriComponentsBuilder
//	                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
//	                                .build().toString())
//	                .collect(Collectors.toList()));
//
//	        return "uploadForm";
//	    }

//	    @GetMapping("/files/{filename:.+}")
//	    @ResponseBody
//	    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//	        Resource file = storageService.loadAsResource(filename);
//	        return ResponseEntity
//	                .ok()
//	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
//	                .body(file);
//	    }

	    @PostMapping("/ocr")	    
	    public  @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException, TesseractException {

	        //storageService.store(file);
//	        redirectAttributes.addFlashAttribute("message",
//	                "You successfully uploaded " + file.getOriginalFilename() + "!");
	    	InputStream imageStream = file.getInputStream();
	    	ITesseract instance = new Tesseract();  // JNA Interface Mapping
//	    	instance.setLanguage("swe");
			instance.setDatapath("/usr/share/tesseract-ocr/tessdata");	    	
	    	BufferedImage imBuff = ImageIO.read(imageStream);
	    	String content = instance.doOCR(imBuff);
	        

	        return content;
//	        return ResponseEntity
//	                .ok()
//	                .header(HttpHeaders.CONTENT_DISPOSITION, "pic content=\""+ content +"\"")
//	                .body(content);
	    }

//	    @ExceptionHandler(StorageFileNotFoundException.class)
//	    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
//	        return ResponseEntity.notFound().build();
//	    }

}
