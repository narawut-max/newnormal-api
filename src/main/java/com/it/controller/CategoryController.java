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

import com.it.entity.CategoryEntity;
import com.it.repository.CategoryRepository;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryEntity>> getAllCategories() {
		return ResponseEntity.ok(categoryRepository.findAll());
	}
	
	@GetMapping("/categories/{ctgId}")
	public ResponseEntity<CategoryEntity> getcategoryByctgId(@PathVariable("ctgId") String ctgId){
		Optional<CategoryEntity> entity = categoryRepository.findById(ctgId);
		if(entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/categories/save")
	public ResponseEntity<CategoryEntity> savecategory(@RequestBody CategoryEntity request) {
		if (request != null) {
			CategoryEntity entity = new CategoryEntity();
			entity.setCtgId(request.getCtgId());
			entity.setCtgName(request.getCtgName());
			return ResponseEntity.ok(categoryRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/categories/update")
	public ResponseEntity<CategoryEntity> updatecategory(@RequestBody CategoryEntity request) {
		if (request.getCtgId() !=null) {
			Optional<CategoryEntity> entity =categoryRepository.findById(request.getCtgId());
			if (entity.isPresent()) {
				CategoryEntity updateEntity = entity.get();
				updateEntity.setCtgName(request.getCtgName());
				return ResponseEntity.ok(categoryRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}	
}
