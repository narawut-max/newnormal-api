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

import com.it.entity.ProvinceEntity;
import com.it.repository.ProvinceRepository;

@RestController
public class ProvinceController {

	@Autowired
	private ProvinceRepository provinceRepository;
	
	@GetMapping("/provinces")
	public  ResponseEntity<List<ProvinceEntity>> getAllProvince() {
		return ResponseEntity.ok(provinceRepository.findAll());
	}
	
	@GetMapping("/provinces/{pvnId}")
	public ResponseEntity<ProvinceEntity> getprovinceByPvnId(@PathVariable("pvnId") Integer pvnId){
		Optional<ProvinceEntity> entity = provinceRepository.findById(pvnId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/provinces/save")
	public ResponseEntity<ProvinceEntity> saveProvince(@RequestBody ProvinceEntity request) {
		if (request != null) {
			ProvinceEntity entity = new ProvinceEntity();
			entity.setPvnId(request.getPvnId());
			entity.setPvnCode(request.getPvnCode());
			entity.setPvnNameTh(request.getPvnNameTh());
			entity.setPvnNameEng(request.getPvnNameEng());
			entity.setArea(request.getArea());
			return ResponseEntity.ok(provinceRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/provinces/update")
	public ResponseEntity<ProvinceEntity> updateProvince(@RequestBody ProvinceEntity request) {
		if (request.getPvnId() != null) {
			Optional<ProvinceEntity> entity = provinceRepository.findById(request.getPvnId());
			if (entity.isPresent()) {
				ProvinceEntity updateEntity = entity.get();
				updateEntity.setPvnCode(request.getPvnCode());
				updateEntity.setPvnNameTh(request.getPvnNameTh());
				updateEntity.setPvnNameEng(request.getPvnNameEng());
				updateEntity.setArea(request.getArea());
				return ResponseEntity.ok(provinceRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
}//end
