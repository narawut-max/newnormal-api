package com.it.controller;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.it.utils.QRPromptPayUtils;

@RestController
@RequestMapping("/promptpay-qr")
public class QRPromptPayController {
	
	@Autowired
	private QRPromptPayUtils qrPromptPayUtils;
	
	@GetMapping("/generate-to-base64")
	public ResponseEntity<String> generateQRCodeToBase64(@RequestParam BigDecimal amount) throws IOException, WriterException {
		String base64Res = qrPromptPayUtils.generateQRCodeToBase64(amount);
		return ResponseEntity.ok(base64Res);
	}
	
	@GetMapping("/generate-to-byte-array")
	public ResponseEntity<byte[]> generateQRCodeToByteArray(@RequestParam BigDecimal amount) throws IOException, WriterException {
		byte[] byteRes = qrPromptPayUtils.generateQRCodeToByteArray(amount);
		return ResponseEntity.ok(byteRes);
	}

}
