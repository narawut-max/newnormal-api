package com.it.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.service.ReportService;
import com.it.utils.ReportUtils;

@RestController
@RequestMapping("/report")
public class ReportController {
	
	private static final Logger log = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping(path = "/generateReport")
	public ResponseEntity<InputStreamResource> generateReport() throws IOException{
		log.info("generateReport : Start");
		ResponseEntity<InputStreamResource> response = null;
		try (ByteArrayOutputStream out = reportService.generateReport()){
			if(out != null) {
				response = new  ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())),
						ReportUtils.createResponseHeader(MediaType.APPLICATION_PDF, "test.pdf", null),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("generateReport Error : {}" , e);
		}
		log.info("generateReport : End");
		return response;
	}
	
	//********** generateBilldrugReport ************
	
	@GetMapping(path = "/generateBilldrugReport")
	public ResponseEntity<InputStreamResource> generateBilldrugReport(@RequestParam(name = "billId", required = true) Integer billId) throws IOException{
		log.info("generateReport : Start :: billId : " + billId);
		ResponseEntity<InputStreamResource> response = null;
		try (ByteArrayOutputStream out = reportService.generateBilldrugReport(billId)){
			if(out != null) {
				response = new  ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())),
						ReportUtils.createResponseHeader(MediaType.APPLICATION_PDF, "BilldrugReport.pdf", null),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("generateReport Error : {}" , e);
		}
		log.info("generateReport : End");
		return response;
	}
	
	//********** generateTreatmentReport ************
	@GetMapping(path = "/generateTreatmentReport")
	public ResponseEntity<InputStreamResource> generateTreatmentReport(@RequestParam(name = "userId", required = true) Integer userId) throws IOException{
		log.info("generateReport : Start :: userId : " + userId);
		ResponseEntity<InputStreamResource> response = null;
		try (ByteArrayOutputStream out = reportService.generateTreatmentReport(userId)){
			if(out != null) {
				response = new  ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())),
						ReportUtils.createResponseHeader(MediaType.APPLICATION_PDF, "TreatmentReport.pdf", null),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("generateReport Error : {}" , e);
		}
		log.info("generateReport : End");
		return response;
	}


}//end
