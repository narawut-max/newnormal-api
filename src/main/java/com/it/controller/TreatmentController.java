package com.it.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.lucene.search.Collector;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.custom.repository.CustomTreatmentRepository;
import com.it.entity.BilldrugEntity;
import com.it.entity.BookingEntity;
import com.it.entity.DrugEntity;
import com.it.entity.TreatmentEntity;
import com.it.entity.UserEntity;
import com.it.model.BilldrugResponse;
import com.it.model.BookingResponse;
import com.it.model.TreatmentResponse;
import com.it.model.UserResponse;
import com.it.repository.BilldrugRepository;
import com.it.repository.BookingRepository;
import com.it.repository.TreatmentRepository;
import com.it.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class TreatmentController {

	@Autowired
	private TreatmentRepository treatmentRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BilldrugRepository billdrugRepository;
	
	@Autowired
	private CustomTreatmentRepository customTreatmentRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	private TreatmentResponse convertToResponse(TreatmentEntity entity) {
		TreatmentResponse response = modelMapper.map(entity, TreatmentResponse.class);
		
		//set user
		Optional<UserEntity> userEntity = userRepository.findById(Integer.valueOf(entity.getUserId()));
		if (userEntity.isPresent()) {
			response.setUser(modelMapper.map(userEntity.get(), UserResponse.class));
		}
		
		//set bill drug
		Optional<BilldrugEntity> billEntity = billdrugRepository.findById(entity.getBillId());
		if (billEntity.isPresent()) {
			response.setBilldrug(modelMapper.map(billEntity.get(), BilldrugResponse.class));
		}
		
		//set booking
		Optional<BookingEntity> bookingEntity = bookingRepository.findById(entity.getBkId());
		if (bookingEntity.isPresent()) {
			response.setBooking(modelMapper.map(bookingEntity.get(), BookingResponse.class));
		}

		return response;
	}
	
	@GetMapping("/treatments")
	public ResponseEntity<List<TreatmentResponse>> getAllTreatment(){
		List<TreatmentEntity> entities = treatmentRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/treatments/{tmId}")
	public ResponseEntity<TreatmentResponse> getTreatmentByTmId(@PathVariable("tmId") Integer tmId){
		Optional<TreatmentEntity> entity = treatmentRepository.findById(tmId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(convertToResponse(entity.get()));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/treatments/by-user")
	public ResponseEntity<List<TreatmentResponse>> gettreatmentsByUserId(@RequestParam(name = "userId") Integer userId) {
		List<TreatmentEntity> entities = treatmentRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			return ResponseEntity.ok(entities.stream().filter(data -> (Integer.parseInt(data.getUserId())) == userId)
					.map(this::convertToResponse).collect(Collectors.toList()));
		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}
	
	@GetMapping("/treatments/search-by-criteria")
	public ResponseEntity<List<TreatmentResponse>> getSearchTreatByCriteria(
			@RequestParam(name = "billId", required = false) String billId,
			@RequestParam(name = "bkId", required =  false) String bkId,
			@RequestParam(name = "userHnId", required =  false) String userHnId,
			@RequestParam(name = "userCardId", required = false) String userCardId,
			@RequestParam(name = "userFirstname", required =  false) String userFirstname,
			@RequestParam(name = "userLastname", required =  false) String userLastname){
		List<TreatmentEntity> entities = customTreatmentRepository.searchTreatByCriteria(billId, bkId, userHnId, userCardId, userFirstname, userLastname);
		if (entities != null && entities.size() > 0) {
			return ResponseEntity.ok(entities.stream().map(this::convertToResponse).collect(Collectors.toList()));
		} else {
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
			entity.setTmProcess(request.getTmProcess());
			entity.setUserId(request.getUserId());
			entity.setBkId(request.getBkId());
			entity.setBillId(request.getBillId());
			return ResponseEntity.ok(treatmentRepository.save(entity));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/treatments/update")
	public ResponseEntity<List<TreatmentEntity>> updateTreatment(@RequestBody List<TreatmentEntity> request){
		if (request != null) {
			 List<TreatmentEntity> filterTreatments = request.stream().map(this::filterTreatmentForUpdate).collect(Collectors.toList());
			return ResponseEntity.ok(treatmentRepository.saveAll(filterTreatments));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	private TreatmentEntity filterTreatmentForUpdate(TreatmentEntity request) {
		TreatmentEntity response = new TreatmentEntity();
		Optional<TreatmentEntity> treatmentEntity = treatmentRepository.findById(request.getTmId());
		if (treatmentEntity.isPresent()) {
			response = TreatmentEntity.builder()
			.tmId(request.getTmId() != null ? request.getTmId() : treatmentEntity.get().getTmId())
			.tmDate(request.getTmDate() != null ? request.getTmDate() : treatmentEntity.get().getTmDate())
			.tmTime(request.getTmTime() != null ? request.getTmTime() : treatmentEntity.get().getTmTime())
			.tmMoney(request.getTmMoney() != null ? request.getTmMoney() : treatmentEntity.get().getTmMoney())
			.tmSlip(request.getTmSlip() != null ? request.getTmSlip() : treatmentEntity.get().getTmSlip())
			.tmStatus(request.getTmStatus() != null ? request.getTmStatus() : treatmentEntity.get().getTmStatus())
			.tmProcess(request.getTmProcess() != null ? request.getTmProcess() : treatmentEntity.get().getTmProcess())
			.userId(request.getUserId() != null ? request.getUserId() : treatmentEntity.get().getUserId())
			.bkId(request.getBkId() != null ? request.getBkId() : treatmentEntity.get().getBkId())
			.billId(request.getBillId() != null ? request.getBillId() : treatmentEntity.get().getBillId())
			.build();
		}		
		return response;
	}
	
}//end
