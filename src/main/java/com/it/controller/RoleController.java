package com.it.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.entity.RoleEntity;
import com.it.repository.RoleRepository;

@RestController
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/roles")
	public ResponseEntity<List<RoleEntity>> getAllRole() {
		return ResponseEntity.ok(roleRepository.findAll());
	}
	
	@GetMapping("/roles/{roleId}")
	public ResponseEntity<RoleEntity> getroleByRoleId(@PathVariable("roleId") String roleId){
		Optional<RoleEntity> entity = roleRepository.findById(roleId);
		if(entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/roles/save")
	public ResponseEntity<RoleEntity> saveRole(@RequestBody RoleEntity request) {
		if (request != null) {
			RoleEntity entity = new RoleEntity();
			entity.setRoleId(request.getRoleId());
			entity.setRoleName(request.getRoleName());
			entity.setRoleDescription(request.getRoleDescription());
			entity.setRoleStatus(request.getRoleStatus());
			return ResponseEntity.ok(roleRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/roles/update")
	public ResponseEntity<RoleEntity> updateRole(@RequestBody RoleEntity request) {
		if (request.getRoleId() != null) {
			Optional<RoleEntity> entity = roleRepository.findById(request.getRoleId());
			if (entity.isPresent()) {
				RoleEntity updateEntity = entity.get();
				updateEntity.setRoleName(request.getRoleName());
				updateEntity.setRoleDescription(request.getRoleDescription());
				updateEntity.setRoleStatus(request.getRoleStatus());
				return ResponseEntity.ok(roleRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
}//end
