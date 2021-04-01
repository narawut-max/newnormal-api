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

import com.it.entity.SubdistrictEntity;
import com.it.repository.SubdistrictRepository;

@RestController
public class SubdistrictController {

	@Autowired
	private SubdistrictRepository subdistrictRepository;
	
	@GetMapping("/subdistricts")
	public ResponseEntity<List<SubdistrictEntity>> getAllSubdistrict(){
		return ResponseEntity.ok(subdistrictRepository.findAll());
	}
	
	@GetMapping("/subdistricts/{sdtId}")
	public ResponseEntity<SubdistrictEntity> getSubdistrictBySdtId(@PathVariable("sdtId") Integer sdtId){
		Optional<SubdistrictEntity> entity = subdistrictRepository.findById(sdtId);
		if(entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/subdistricts/save")
	public ResponseEntity<SubdistrictEntity> saveSubdistrict(@RequestBody SubdistrictEntity request){
		if (request != null) {
			SubdistrictEntity entity = new SubdistrictEntity();
			entity.setSdtId(request.getSdtId());
			entity.setZipCode(request.getZipCode());
			entity.setSdtNameTh(request.getSdtNameTh());
			entity.setSdtNameEng(request.getSdtNameEng());
			entity.setDisId(request.getDisId());
			return ResponseEntity.ok(subdistrictRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/subdistricts/update")
	public ResponseEntity<SubdistrictEntity> updateSubdistrict(@RequestBody SubdistrictEntity request){
		if (request.getSdtId() != null) {
			Optional<SubdistrictEntity> entity = subdistrictRepository.findById(request.getSdtId());
			if (entity.isPresent()) {
				SubdistrictEntity updateEntity = entity.get();
				updateEntity.setZipCode(request.getZipCode());
				updateEntity.setSdtNameTh(request.getSdtNameTh());
				updateEntity.setSdtNameEng(request.getSdtNameEng());
				updateEntity.setDisId(request.getDisId());
				return ResponseEntity.ok(subdistrictRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	
}//end
