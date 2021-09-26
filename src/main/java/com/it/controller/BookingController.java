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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.entity.BilldrugEntity;
import com.it.entity.BookingEntity;
import com.it.entity.RoleEntity;
import com.it.entity.TreatmentEntity;
import com.it.entity.UserEntity;
import com.it.enums.MailFlags;
import com.it.model.BilldrugResponse;
import com.it.model.BookingResponse;
import com.it.model.TreatmentResponse;
import com.it.model.UserResponse;
import com.it.repository.BookingRepository;
import com.it.repository.TreatmentRepository;
import com.it.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	private BookingResponse convertToResponse(BookingEntity entity) {
		BookingResponse response = modelMapper.map(entity, BookingResponse.class);
		
		//set user
		Optional<UserEntity> userEntity = userRepository.findById(Integer.valueOf(entity.getUserId()));
		if (userEntity.isPresent()) {
			response.setUser(modelMapper.map(userEntity.get(), UserResponse.class));
		}

		return response;
	}
	
	@GetMapping("/bookings")
	public ResponseEntity<List<BookingResponse>> getAllBookings(){
		List<BookingEntity> entities = bookingRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/bookings/{bkId}")
	public ResponseEntity<BookingResponse> getBookingByBkId(@PathVariable("bkId") Integer bkId){
		Optional<BookingEntity> entity = bookingRepository.findById(bkId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(convertToResponse(entity.get()));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/bookings/by-user")
	public ResponseEntity<List<BookingResponse>> getbookingsByUserId(@RequestParam(name = "userId") Integer userId) {
		List<BookingEntity> entities = bookingRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().filter(data -> (Integer.parseInt(data.getUserId())) == userId)
					.map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}
	
	@GetMapping("/bookings/by-Department")
	public ResponseEntity<List<BookingResponse>> getbookingBydepartment(@RequestParam(name = "bkDepartment") String bkDepartment) {
		List<BookingEntity> entities = bookingRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().filter(data -> data.getBkDepartment().equals(bkDepartment))
					.map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}
	
	@PostMapping("/bookings/save")
	public ResponseEntity<BookingEntity> saveBooking(@RequestBody BookingEntity request) {
		if (request !=null) {
			BookingEntity entity = new BookingEntity();
			entity.setBkQueue(request.getBkQueue());
			entity.setBkDate(request.getBkDate());
			entity.setBkTime(request.getBkTime());
			entity.setBkSymptom(request.getBkSymptom());
			entity.setBkStatus(request.getBkStatus());
			entity.setBkDepartment(request.getBkDepartment());
			entity.setUserId(request.getUserId());
			entity.setMailFlag(MailFlags.NOT_SEND.value);
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
				updateEntity.setBkQueue(request.getBkQueue());
				updateEntity.setBkSymptom(request.getBkSymptom());
				updateEntity.setBkStatus(request.getBkStatus());
				updateEntity.setBkDepartment(request.getBkDepartment());
				updateEntity.setUserId(request.getUserId());
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
