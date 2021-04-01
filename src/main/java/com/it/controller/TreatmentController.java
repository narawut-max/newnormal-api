package com.it.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.entity.TreatmentEntity;
import com.it.repository.TreatmentRepository;

@RestController
public class TreatmentController {

	@Autowired
	private TreatmentRepository treatmentRepository;
	
	@GetMapping("/treatments")
	public ResponseEntity<List<TreatmentEntity>> getAllTreatment(){
		return ResponseEntity.ok(treatmentRepository.findAll());
	}
	
	@GetMapping("/treatments/{tmId}")
	public ResponseEntity<TreatmentEntity> getTreatmentByTmId(@PathVariable("tmId") String tmId){
		Optional<TreatmentEntity> entity = treatmentRepository.findById(tmId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/treatments/save")
	public ResponseEntity<TreatmentEntity> saveTreatment(@RequestBody TreatmentEntity request){
		if (request != null) {
			TreatmentEntity entity = new TreatmentEntity();
			entity.setTmId(request.getTmId());
			entity.setTmDate(request.getTmDate() != null ? entity.getTmDate() : new Date());
			entity.setTmTime(request.getTmTime() != null ? entity.getTmTime() : Timestamp.valueOf(LocalDateTime.now()));
			entity.setTmMoney(request.getTmMoney());
			entity.setTmSlip(request.getTmSlip());
			entity.setTmStatus(request.getTmStatus());
			entity.setUserId(request.getUserId());
			entity.setBkId(request.getBkId());
			entity.setBillId(request.getBillId());
			return ResponseEntity.ok(treatmentRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/treatments/update")
	public ResponseEntity<TreatmentEntity> updateTreatment(@RequestBody TreatmentEntity request){
		if (request.getTmId() != null) {
			Optional<TreatmentEntity> entity = treatmentRepository.findById(request.getTmId());
			if (entity.isPresent()) {
				TreatmentEntity updateEntity = entity.get();
				updateEntity.setTmMoney(request.getTmMoney());
				updateEntity.setTmSlip(request.getTmSlip());
				updateEntity.setTmStatus(request.getTmStatus());
				updateEntity.setUserId(request.getUserId());
				updateEntity.setBkId(request.getBkId());
				updateEntity.setBillId(request.getBillId());
				if (request.getTmDate() != null) {
					updateEntity.setTmDate(request.getTmDate());
				}				
				if (request.getTmTime() != null) {
					updateEntity.setTmTime(request.getTmTime());
				}
				return ResponseEntity.ok(treatmentRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
}//end
