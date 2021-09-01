package com.it.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.custom.repository.CustomDrugRepository;
import com.it.entity.DrugEntity;
import com.it.repository.DrugRepository;

@RestController
@CrossOrigin(origins = "*")
public class DrugController {
	
	@Autowired
	private DrugRepository drugRepository;
	
	@Autowired
	private CustomDrugRepository customDrugRepository;
	
	@GetMapping("/drugs")
	public ResponseEntity<List<DrugEntity>> getAllDrugs(){
		return ResponseEntity.ok(drugRepository.findAll());
	}
	
	@GetMapping("/drugs/{drugId}")
	public ResponseEntity<DrugEntity> getDrugByDrugId(@PathVariable("drugId") String drugId){
		Optional<DrugEntity> entity = drugRepository.findById(drugId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/drugs/search/{drugName}")
	public ResponseEntity<DrugEntity> getSearchByDrugName(@PathVariable("drugName") String drugName) {
		Optional<DrugEntity> entity = drugRepository.findByDrugName(drugName);
		if (entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/drugs/search-by-criteria")
	public ResponseEntity<List<DrugEntity>> getSearchByCriteria(
			@RequestParam(name = "drugId", required =  false) String drugId,
			@RequestParam(name = "drugName", required =  false) String drugName,
			@RequestParam(name = "drugTrademark", required =  false) String drugTrademark) {
		List<DrugEntity> entities = customDrugRepository.searchDrugByCriteria(drugId, drugName, drugTrademark);
		if (entities != null && entities.size() > 0) {
			return ResponseEntity.ok(entities);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/drugs/save")
	public ResponseEntity<DrugEntity> saveDrug(@RequestBody DrugEntity request) {
		if (request != null) {
			DrugEntity entity = new DrugEntity();
			entity.setDrugId(request.getDrugId());
			entity.setDrugName(request.getDrugName());
			entity.setDrugTrademark(request.getDrugTrademark());
			entity.setDrugActive(request.getDrugActive());
			entity.setDrugMfg(request.getDrugMfg());
			entity.setDrugExp(request.getDrugExp());
			entity.setDrugPrice(request.getDrugPrice());
			entity.setDrugAmount(request.getDrugAmount());
			entity.setCtgId(request.getCtgId());
			return ResponseEntity.ok(drugRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/drugs/update")
	public ResponseEntity<DrugEntity> updateDrug(@RequestBody DrugEntity request) {
		if (request.getDrugId() != null) {
			Optional<DrugEntity> entity = drugRepository.findById(request.getDrugId());
			if (entity.isPresent()) {
				
				DrugEntity updateEntity = entity.get();
				updateEntity.setDrugName(request.getDrugName());
				updateEntity.setDrugTrademark(request.getDrugTrademark());
				updateEntity.setDrugActive(request.getDrugActive());
				updateEntity.setDrugMfg(request.getDrugMfg());
				updateEntity.setDrugExp(request.getDrugExp());
				updateEntity.setDrugPrice(request.getDrugPrice());
				updateEntity.setDrugAmount(request.getDrugAmount());
				updateEntity.setCtgId(request.getCtgId());
				return ResponseEntity.ok(drugRepository.save(updateEntity));
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
		
}//endclass
