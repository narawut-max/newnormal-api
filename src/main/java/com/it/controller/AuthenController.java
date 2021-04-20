package com.it.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.dto.AuthenDto;
import com.it.entity.UserEntity;
import com.it.repository.UserRepository;
import com.it.utils.PasswordEncryptorUtils;

@RequestMapping("/authentication")
@RestController
public class AuthenController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	public ResponseEntity<UserEntity> loginByUsernamePassword(@RequestBody AuthenDto request) {
		UserEntity entity = userRepository.findByUserUsername(request.getUsername());
		if (ObjectUtils.isNotEmpty(entity) && PasswordEncryptorUtils.checkPassword(request.getPassword(), entity.getUserPassword())) {
			return ResponseEntity.ok(entity);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@PostMapping("/login-by-card-pass")
	public ResponseEntity<UserEntity> loginByCardIdPassword(@RequestBody AuthenDto request) {
		UserEntity entity = userRepository.findByUserCardId(request.getCardId());
		if (ObjectUtils.isNotEmpty(entity) && PasswordEncryptorUtils.checkPassword(request.getPassword(), entity.getUserPassword())) {
			return ResponseEntity.ok(entity);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@PostMapping("/login-by-card")
	public ResponseEntity<UserEntity> loginByCardId(@RequestBody AuthenDto request) {
		UserEntity entity = userRepository.findByUserCardId(request.getCardId());
		if (ObjectUtils.isNotEmpty(entity)) {
			return ResponseEntity.ok(entity);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

}
