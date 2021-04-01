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

import com.it.entity.DistrictEntity;
import com.it.repository.DistrictRepository;

@RestController
public class DistrictController {
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@GetMapping("/districts")
	public  ResponseEntity<List<DistrictEntity>> getAllDistrict() {
		return ResponseEntity.ok(districtRepository.findAll());
	}
	
	@GetMapping("/districts/{disId}")
	public ResponseEntity<DistrictEntity> getdistrictBydisId(@PathVariable("disId") Integer disId){
		Optional<DistrictEntity> entity = districtRepository.findById(disId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/districts/save")
	public ResponseEntity<DistrictEntity> saveDistrict(@RequestBody DistrictEntity request) {
		if (request != null) {
			DistrictEntity entity = new DistrictEntity();
			entity.setDisId(request.getDisId());
			entity.setDisCode(request.getDisCode());
			entity.setDisNameTh(request.getDisNameTh());
			entity.setDisNameEng(request.getDisNameEng());
			entity.setPvnId(request.getPvnId());
			return ResponseEntity.ok(districtRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/districts/update")
	public ResponseEntity<DistrictEntity> updateDistrict(@RequestBody DistrictEntity request){
		if (request.getDisId() != null) {
			Optional<DistrictEntity> entity = districtRepository.findById(request.getDisId());
			if (entity.isPresent()) {
				DistrictEntity updateEntity = entity.get();
				updateEntity.setDisCode(request.getDisCode());
				updateEntity.setDisNameTh(request.getDisNameTh());
				updateEntity.setDisNameEng(request.getDisNameEng());
				updateEntity.setPvnId(request.getPvnId());
				return ResponseEntity.ok(districtRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
} //endclass
