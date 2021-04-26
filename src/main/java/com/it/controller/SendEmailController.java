package com.it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.utils.SendEmailUtils;

@RestController
public class SendEmailController {
	
	@Autowired
	private SendEmailUtils sendEmailUtils;
	
	@GetMapping("/email-send")
	public ResponseEntity<String> sendEmail(@RequestParam(required = true) String sendTo, @RequestParam(required = true) String subject, @RequestParam(required = false) String text) {
		sendEmailUtils.sendSimpleMessage(sendTo, subject, text);
		return ResponseEntity.ok("");
	}

}
