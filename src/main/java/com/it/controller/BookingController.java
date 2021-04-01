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

import com.it.entity.BookingEntity;
import com.it.repository.BookingRepository;

@RestController
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@GetMapping("/bookings")
	public ResponseEntity<List<BookingEntity>> getAllBookings() {
		return ResponseEntity.ok(bookingRepository.findAll());
	}
	
	@GetMapping("/bookings/{bkId}")
	public ResponseEntity<BookingEntity> getBilldrugByBillId(@PathVariable("bkId") Integer bkId){
		Optional<BookingEntity> entity = bookingRepository.findById(bkId);
		if(entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/bookings/save")
	public ResponseEntity<BookingEntity> saveBooking(@RequestBody BookingEntity request) {
		if (request !=null) {
			BookingEntity entity = new BookingEntity();
			entity.setTmId(request.getTmId());
			entity.setBkDate(request.getBkDate() != null ? entity.getBkDate() : new Date());
			entity.setBkTime(request.getBkTime() != null ? entity.getBkTime() : Timestamp.valueOf(LocalDateTime.now()));
			entity.setBkSymptom(request.getBkSymptom());
			entity.setBkStatus(request.getBkStatus());
			return ResponseEntity.ok(bookingRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/bookings/update")
	public ResponseEntity<BookingEntity> updateBooking(@RequestBody BookingEntity request) {
		if (request.getBkId() !=null) {
			Optional<BookingEntity> entity = bookingRepository.findById(request.getBkId()); 
			if (entity.isPresent()) {
				BookingEntity updateEntity = entity.get();
				updateEntity.setTmId(request.getTmId());
				updateEntity.setBkSymptom(request.getBkSymptom());
				updateEntity.setBkStatus(request.getBkStatus());
				if (request.getBkDate() != null) {
					updateEntity.setBkDate(request.getBkDate());
				}
				if (request.getBkTime() != null) {
					updateEntity.setBkTime(request.getBkTime());
				}
				return ResponseEntity.ok(bookingRepository.save(updateEntity));
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	
	
}