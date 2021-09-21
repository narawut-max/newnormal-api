package com.it.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.custom.repository.CustomUserRepository;
import com.it.entity.RoleEntity;
import com.it.entity.TreatmentEntity;
import com.it.entity.UserEntity;
import com.it.model.RoleResponse;
import com.it.model.TreatmentResponse;
import com.it.model.UserResponse;
import com.it.repository.RoleRepository;
import com.it.repository.UserRepository;
import com.it.utils.PasswordEncryptorUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class UserController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private CustomUserRepository customUserRepository;
	
	private UserResponse convertToResponse(UserEntity entity) {
		UserResponse response = modelMapper.map(entity, UserResponse.class);
		
		//set treatment
		Optional<RoleEntity> roleEntity = roleRepository.findById(entity.getRoleId());
		if (roleEntity.isPresent()) {
			response.setRole(modelMapper.map(roleEntity.get(), RoleResponse.class));
		}
		
		return response;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserResponse>> getAllUsers(){
		List<UserEntity> entities = userRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserResponse> getUserByUserId(@PathVariable("userId") Integer userId){
		Optional<UserEntity> entity = userRepository.findById(userId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(convertToResponse(entity.get()));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/users/by-user")
	public ResponseEntity<List<UserResponse>> getUserByRoleId(@RequestParam(name = "roleId") Integer roleId) {
		List<UserEntity> entities = userRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().filter(data -> (Integer.parseInt(data.getRoleId())) == roleId)
					.map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}
	
	@GetMapping("/users/by-userId")
	public ResponseEntity<List<UserResponse>> getUserByUser(@RequestParam(name = "userId") Integer userId) {
		List<UserEntity> entities = userRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().filter(data -> data.getUserId() == userId)
					.map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}
	
//	@GetMapping("/users/by-Department")
//	public ResponseEntity<List<UserResponse>> gettreatBydepartment(@RequestParam(name = "userDepartment") String userDepartment) {
//		List<UserEntity> entities = userRepository.findAll();
//		if (CollectionUtils.isNotEmpty(entities)) {
//			return ResponseEntity.ok(entities.stream().filter(data -> data.getUserDepartment() == userDepartment)
//					.map(this::convertToResponse).collect(Collectors.toList()));
//		} else {
//			return ResponseEntity.badRequest().body(null);
//		}
//
//	}
	
	@GetMapping("/users/search-by-criteria")
	public ResponseEntity<List<UserResponse>> getSearchUserByCriteria(
			@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "userHnId", required =  false) String userHnId,
			@RequestParam(name = "userCardId", required = false) String userCardId,
			@RequestParam(name = "userFirstname", required =  false) String userFirstname,
			@RequestParam(name = "userLastname", required =  false) String userLastname){
		List<UserEntity> entities = customUserRepository.searchUserByCriteria(userId, userHnId, userCardId, userFirstname, userLastname);
		if (entities != null && entities.size() > 0) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	
	@PostMapping("/users/save")
	public ResponseEntity<UserEntity> saveUser(@Valid @RequestBody UserEntity request) {
		if (request != null) {
			log.info("saveUser : " + request.toString());
			UserEntity entity = new UserEntity();
			entity.setUserId(request.getUserId());
			entity.setUserUsername(request.getUserUsername());
			entity.setUserPassword(PasswordEncryptorUtils.passwordEncryptor(request.getUserPassword()));
			entity.setUserCardId(request.getUserCardId());
			entity.setUserHnId(request.getUserHnId());
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
			entity.setUserProfessionId(request.getUserProfessionId());
			entity.setUserProfession(request.getUserProfession());
			entity.setUserPosition(request.getUserPosition());
			entity.setUserPhone(request.getUserPhone());
			entity.setUserEmail(request.getUserEmail());
			entity.setUserStatus(request.getUserStatus());
			entity.setUserAddrass(request.getUserAddrass());
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
				updateEntity.setUserHnId(request.getUserHnId());
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
				updateEntity.setUserProfessionId(request.getUserProfessionId());
				updateEntity.setUserProfession(request.getUserProfession());
				updateEntity.setUserPosition(request.getUserPosition());
				updateEntity.setUserPhone(request.getUserPhone());
				updateEntity.setUserEmail(request.getUserEmail());
				updateEntity.setUserStatus(request.getUserStatus());
				updateEntity.setUserAddrass(request.getUserAddrass());
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
