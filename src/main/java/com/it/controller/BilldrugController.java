package com.it.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.entity.BilldrugEntity;
import com.it.entity.TreatmentEntity;
import com.it.entity.UserEntity;
import com.it.model.BilldrugResponse;
import com.it.model.TreatmentResponse;
import com.it.model.UserResponse;
import com.it.repository.BilldrugRepository;
import com.it.repository.TreatmentRepository;
import com.it.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class BilldrugController {
	
	@Autowired
	private BilldrugRepository billdrugRepository;
	
	@Autowired
	private TreatmentRepository treatmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	private BilldrugResponse convertToResponse(BilldrugEntity entity) {
		BilldrugResponse response = modelMapper.map(entity, BilldrugResponse.class);
		
		//set Treatment
		Optional<TreatmentEntity> treatEntity = treatmentRepository.findById(entity.getTmId());
		if (treatEntity.isPresent()) {
			TreatmentResponse treatmentResponse = modelMapper.map(treatEntity.get(), TreatmentResponse.class);
			Optional<UserEntity> userEntity = userRepository.findById(Integer.parseInt(treatEntity.get().getUserId()));
			if (userEntity.isPresent()) {
				treatmentResponse.setUser(modelMapper.map(userEntity.get(), UserResponse.class));
			}
			response.setTreatment(treatmentResponse);
		}
		
		return response;
	}
	
	@GetMapping("/billdrugs")
	public ResponseEntity<List<BilldrugResponse>> getAllBilldrug(){
		List<BilldrugEntity> entities = billdrugRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/billdrugs/{billId}")
	public ResponseEntity<BilldrugResponse> getBilldrugByBillId(@PathVariable("billId") Integer billId){
		Optional<BilldrugEntity> entity = billdrugRepository.findById(billId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(convertToResponse(entity.get()));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}	
	
	@PostMapping("/billdrugs/save")
	public ResponseEntity<BilldrugEntity> saveBilldrug(@RequestBody BilldrugEntity request) {
		if (request != null) {
			log.info("saveBilldrug : " + request.toString());
			BilldrugEntity entity = new BilldrugEntity();
			entity.setBillId(request.getBillId());
			entity.setDrugId(request.getDrugId());
			entity.setBillNext(request.getBillNext());
			entity.setTmId(request.getTmId());
			entity.setBillDate(entity.getBillDate() != null ? entity.getBillDate() : new Date());
			entity.setBillTime(entity.getBillTime() != null ? entity.getBillTime() :Timestamp.valueOf( LocalDateTime.now()));
			return ResponseEntity.ok(billdrugRepository.save(entity));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/billdrugs/update")
	public ResponseEntity<BilldrugEntity> updateBilldrug(@RequestBody BilldrugEntity request) {
		if(request.getBillId() != null) {
			Optional<BilldrugEntity> entity = billdrugRepository.findById(request.getBillId());
			if (entity.isPresent()) {
				//set update data form request				
				BilldrugEntity updateEntity = entity.get();
				updateEntity.setDrugId(request.getDrugId());
				updateEntity.setTmId(request.getTmId());
				updateEntity.setBillNext(request.getBillNext());
				if (request.getBillDate() != null) {
					updateEntity.setBillDate(request.getBillDate());
				}				
				if (request.getBillTime() != null) {
					updateEntity.setBillTime(request.getBillTime());
				}				
				return ResponseEntity.ok(billdrugRepository.save(updateEntity));
			} else {
				return ResponseEntity.badRequest().body(null);
			}			
		} else {
			return ResponseEntity.badRequest().body(null);
		}		
	}

}
