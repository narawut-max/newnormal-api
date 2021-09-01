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

import com.it.entity.BilldrugDetailEntity;
import com.it.entity.RoleEntity;
import com.it.repository.BilldrugDetailRepository;

@RestController
public class BilldrugDetailController {

	@Autowired
	private BilldrugDetailRepository billdrugDetailRepository;
	
	@GetMapping("/billdrugs-detail")
	public ResponseEntity<List<BilldrugDetailEntity>> getAllDetail() {
		return ResponseEntity.ok(billdrugDetailRepository.findAll());
	}
	
	@GetMapping("/billdrugs-detail/{billdrugDetailId}")
	public ResponseEntity<BilldrugDetailEntity> getDetailBybilldrugDetailId(@PathVariable("billdrugDetailId") Integer billdrugDetailId){
		Optional<BilldrugDetailEntity> entity = billdrugDetailRepository.findById(billdrugDetailId);
		if(entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/billdrugs-detail/save")
	public ResponseEntity<BilldrugDetailEntity> saveDetail(@RequestBody BilldrugDetailEntity request) {
		if (request != null) {
			BilldrugDetailEntity entity = new BilldrugDetailEntity();
			entity.setBilldrugDetailId(request.getBilldrugDetailId());
			entity.setBillId(request.getBillId());
			entity.setDrugName(request.getDrugName());
			entity.setDrugCount(request.getDrugCount());
			return ResponseEntity.ok(billdrugDetailRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/billdrugdetail/update")
	public ResponseEntity<BilldrugDetailEntity> updateDetail(@RequestBody BilldrugDetailEntity request) {
		if (request.getBilldrugDetailId() != null) {
			Optional<BilldrugDetailEntity> entity = billdrugDetailRepository.findById(request.getBilldrugDetailId());
			if (entity.isPresent()) {
				BilldrugDetailEntity updateEntity = entity.get();
				updateEntity.setBillId(request.getBillId());
				updateEntity.setDrugName(request.getDrugName());
				updateEntity.setDrugCount(request.getDrugCount());
				return ResponseEntity.ok(billdrugDetailRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
}
