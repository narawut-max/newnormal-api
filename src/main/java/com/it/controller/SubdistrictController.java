package com.it.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.entity.DistrictEntity;
import com.it.entity.ProvinceEntity;
import com.it.entity.SubdistrictEntity;
import com.it.model.DistrictResponse;
import com.it.model.ProvinceResponse;
import com.it.model.SubdistrictResponse;
import com.it.repository.DistrictRepository;
import com.it.repository.ProvinceRepository;
import com.it.repository.SubdistrictRepository;

@RestController
public class SubdistrictController {

	@Autowired
	private SubdistrictRepository subdistrictRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	private SubdistrictResponse convertToResponse(SubdistrictEntity entity) {
		SubdistrictResponse response = new SubdistrictResponse();
		if (ObjectUtils.isNotEmpty(entity)) {
			response = modelMapper.map(entity, SubdistrictResponse.class);

			Optional<DistrictEntity> districtEntity = districtRepository.findById(entity.getDisId());
			if (districtEntity.isPresent()) {
				response.setDistrict(modelMapper.map(districtEntity.get(), DistrictResponse.class));

				Optional<ProvinceEntity> provinceEntity = provinceRepository.findById(districtEntity.get().getPvnId());
				if (provinceEntity.isPresent()) {
					response.setProvince(modelMapper.map(provinceEntity.get(), ProvinceResponse.class));
				}
			}
		}
		return response;

	}
	
	@GetMapping("/subdistricts")
	public ResponseEntity<List<SubdistrictResponse>> getAllSubdistrict() {
		List<SubdistrictResponse> response = new ArrayList<>();
		List<SubdistrictEntity> entities = subdistrictRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			response = entities.stream().map(this::convertToResponse).collect(Collectors.toList());
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/subdistricts/zipCode")
	public ResponseEntity<List<SubdistrictResponse>> getAllSubdistrictByZipCode(@RequestParam("zipCode") String zipCode) {
		List<SubdistrictResponse> response = new ArrayList<>();
		List<SubdistrictEntity> entities = subdistrictRepository.findByZipCode(zipCode);
		if (CollectionUtils.isNotEmpty(entities)) {
			response = entities.stream().map(this::convertToResponse).collect(Collectors.toList());
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/subdistricts/sdtId")
	public ResponseEntity<SubdistrictResponse> getSubdistrictBySdtId(@RequestParam("sdtId") Integer sdtId){
		Optional<SubdistrictEntity> entity = subdistrictRepository.findById(sdtId);
		if(entity.isPresent()) {
			return ResponseEntity.ok(this.convertToResponse(entity.get()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/subdistricts/by_zip_code")
	public ResponseEntity<SubdistrictResponse> getSubdistrictBySdtId(@RequestParam("zipCode") String zipCode){
		List<SubdistrictEntity> entities = subdistrictRepository.findAll();
		if(CollectionUtils.isNotEmpty(entities)) {			
			Optional<SubdistrictEntity> response = entities.stream().filter(entity -> entity.getZipCode().equalsIgnoreCase(zipCode)).findFirst();
			if (response.isPresent()) {
				return ResponseEntity.ok(this.convertToResponse(response.get()));
			} else {
				return ResponseEntity.badRequest().body(null);
			}
			
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
