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

import com.it.entity.BilldrugEntity;
import com.it.repository.BilldrugRepository;

@RestController
public class BilldrugController {
	
	@Autowired
	private BilldrugRepository billdrugRepository;
	
	@GetMapping("/billdrugs")
	public ResponseEntity<List<BilldrugEntity>> getAllBilldrugs(){
		return ResponseEntity.ok(billdrugRepository.findAll());
	}
	
	@GetMapping("/billdrugs/{billId}")
	public ResponseEntity<BilldrugEntity> getBilldrugByBillId(@PathVariable("billId") Integer billId){
		Optional<BilldrugEntity> entity = billdrugRepository.findById(billId);
		if(entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/billdrugs/save")
	public ResponseEntity<BilldrugEntity> saveBilldrug(@RequestBody BilldrugEntity request) {
		if (request != null) {
			BilldrugEntity entity = new BilldrugEntity();
			entity.setBillId(request.getBillId());
			entity.setDrugId(request.getDrugId());
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
