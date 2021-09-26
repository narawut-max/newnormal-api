package com.it.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.entity.BilldrugDetailEntity;
import com.it.entity.RoleEntity;
import com.it.entity.TreatmentEntity;
import com.it.model.BilldrugDetailReponse;
import com.it.model.TreatmentResponse;
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
	public ResponseEntity<List<BilldrugDetailEntity>> saveDetail(@RequestBody List<BilldrugDetailEntity> request) {
		if (request != null) {
			return ResponseEntity.ok(billdrugDetailRepository.saveAll(request));
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
