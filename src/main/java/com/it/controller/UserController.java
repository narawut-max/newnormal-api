package com.it.controller;

import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.entity.TreatmentEntity;
import com.it.entity.UserEntity;
import com.it.model.TreatmentResponse;
import com.it.model.UserResponse;
import com.it.repository.TreatmentRepository;
import com.it.repository.UserRepository;
import com.it.utils.PasswordEncryptorUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class UserController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TreatmentRepository treatmentRepository;

	@Autowired
    private ModelMapper modelMapper;
	
	private UserResponse convertToResponse(UserEntity entity) {
		UserResponse response = modelMapper.map(entity, UserResponse.class);
		
		//set treatment
		Optional<TreatmentEntity> treatmentEntity = treatmentRepository.findById(entity.getTmId());
		if (treatmentEntity.isPresent()) {
			response.setTreatment(modelMapper.map(treatmentEntity.get(), TreatmentResponse.class));
		}
		
		return response;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserEntity>> getAllUsers() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserEntity> getUserByUserId(@PathVariable("userId") String userId) {
		Optional<UserEntity> entity = userRepository.findById(Integer.valueOf(userId));
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PostMapping("/users/save")
	public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity request) {
		if (request != null) {
			log.info("saveUser : " + request.toString());
			UserEntity entity = new UserEntity();
			entity.setUserId(request.getUserId());
			entity.setUserUsername(request.getUserUsername());
			entity.setUserPassword(PasswordEncryptorUtils.passwordEncryptor(request.getUserPassword()));
			entity.setUserCardId(request.getUserCardId());
			entity.setUserTitle(request.getUserTitle());
			entity.setUserFirstname(request.getUserFirstname());
			entity.setUserLastname(request.getUserLastname());
			entity.setUserGender(request.getUserGender());
			entity.setUserBirthday(request.getUserBirthday());
			entity.setUserBlood(request.getUserBlood());
			entity.setUserDisease(request.getUserDisease());
			entity.setUserAllergy(request.getUserAllergy());
			entity.setUserDepartment(request.getUserDepartment());
			entity.setUserGraduate(request.getUserGraduate());
			entity.setUserProfession(request.getUserProfession());
			entity.setUserPosition(request.getUserPosition());
			entity.setUserPhone(request.getUserPhone());
			entity.setUserEmail(request.getUserEmail());
			entity.setUserStatus(request.getUserStatus());
			entity.setUserAddrass(request.getUserAddrass());
			entity.setTmId(request.getTmId());
			entity.setZipCode(request.getZipCode());
			entity.setRoleId(request.getRoleId());
			return ResponseEntity.ok(userRepository.save(entity));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PostMapping("/users/update")
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity request) {
		if (request.getUserId() != null) {
			Optional<UserEntity> entity = userRepository.findById(request.getUserId());
			if (entity.isPresent()) {
				UserEntity updateEntity = entity.get();
				updateEntity.setUserUsername(request.getUserUsername());
				// updateEntity.setUserPassword(request.getUserPassword()); not update password
				updateEntity.setUserCardId(request.getUserCardId());
				updateEntity.setUserTitle(request.getUserTitle());
				updateEntity.setUserFirstname(request.getUserFirstname());
				updateEntity.setUserLastname(request.getUserLastname());
				updateEntity.setUserGender(request.getUserGender());
				updateEntity.setUserBirthday(request.getUserBirthday());
				updateEntity.setUserBlood(request.getUserBlood());
				updateEntity.setUserDisease(request.getUserDisease());
				updateEntity.setUserAllergy(request.getUserAllergy());
				updateEntity.setUserDepartment(request.getUserDepartment());
				updateEntity.setUserGraduate(request.getUserGraduate());
				updateEntity.setUserProfession(request.getUserProfession());
				updateEntity.setUserPosition(request.getUserPosition());
				updateEntity.setUserPhone(request.getUserPhone());
				updateEntity.setUserEmail(request.getUserEmail());
				updateEntity.setUserStatus(request.getUserStatus());
				updateEntity.setUserAddrass(request.getUserAddrass());
				updateEntity.setTmId(request.getTmId());
				updateEntity.setZipCode(request.getZipCode());
				updateEntity.setRoleId(request.getRoleId());
				return ResponseEntity.ok(userRepository.save(updateEntity));
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> deleteUserByUserId(@PathVariable("userId") String userId) {
		userRepository.deleteById(Integer.valueOf(userId));
		return ResponseEntity.ok("SUCCESS");
	}

}// end
