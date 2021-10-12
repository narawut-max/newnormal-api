package com.it.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.it.entity.BilldrugEntity;
import com.it.entity.BookingEntity;
import com.it.entity.TreatmentEntity;
import com.it.entity.UserEntity;
import com.it.model.BilldrugDetailReponse;
import com.it.model.BilldrugResponse;
import com.it.model.BookingResponse;
import com.it.model.TreatmentResponse;
import com.it.model.UserResponse;
import com.it.repository.BilldrugDetailRepository;
import com.it.repository.BilldrugRepository;
import com.it.repository.BookingRepository;
import com.it.repository.TreatmentRepository;
import com.it.repository.UserRepository;
import com.it.service.ReportService;
import com.it.utils.QRPromptPayUtils;

import javassist.bytecode.ByteArray;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportServiceImpl implements ReportService {

	private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private BilldrugRepository billdrugRepository;
	
	@Autowired
	private BilldrugDetailRepository billdrugDetailRepository;
	
	@Autowired
	private TreatmentRepository treatmentRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QRPromptPayUtils qrPromptPayUtils;

	@Override
	public ByteArrayOutputStream generateReport() throws IOException {
		log.info("generateReport : Start");
		ByteArrayOutputStream out = null;
		try {
			ClassPathResource reportFile = new ClassPathResource("report/Test.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getInputStream());

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("TEST", "REPORT TEST");

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
			out = new ByteArrayOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, out);

		} catch (Exception e) {
			log.error("generateReport Error : {} ", e);
		}
		log.info("generateReport : End");
		return out;
	}

	//******** generateBilldrugReport ***********
	@Override
	public ByteArrayOutputStream generateBilldrugReport(Integer billId) throws IOException {
		log.info("generateBilldrugReport : Start :: billId : " + billId);
		ByteArrayOutputStream out = null;
		try {

			Optional<BilldrugEntity> entity = billdrugRepository.findById(billId);
			if (entity.isPresent()) {
				BilldrugResponse billdrugResponse = convertToResponse(entity.get());
				ClassPathResource reportFile = new ClassPathResource("report/BilldrugReport.jasper");
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getInputStream());

				// parameter
				Map<String, Object> parameters = new HashMap<>();
				parameters.put("BillId", String.valueOf(billId));
				parameters.put("billId", billdrugResponse.getTreatment().getBillId());
				parameters.put("userHnId", billdrugResponse.getTreatment().getUser().getUserHnId());
				parameters.put("tmTime", billdrugResponse.getTreatment().getTmTime());
				parameters.put("billNext", billdrugResponse.getBillNext());
				parameters.put("userTitle", billdrugResponse.getTreatment().getUser().getUserTitle());
				parameters.put("userFirstname", billdrugResponse.getTreatment().getUser().getUserFirstname());
				parameters.put("userLastname", billdrugResponse.getTreatment().getUser().getUserLastname());
				parameters.put("userDisease", billdrugResponse.getTreatment().getUser().getUserDisease());
				parameters.put("userDepartment", billdrugResponse.getTreatment().getUser().getUserDepartment());
//				parameters.put("tmMoney", billdrugResponse.getTreatment().getTmMoney());
				byte[] array = qrPromptPayUtils.generateQRCodeToByteArray(billdrugResponse.getTreatment().getTmMoney());
			    parameters.put("tmMoney", new ByteArrayInputStream(array));

				// list bean
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						billdrugResponse.getBilldrugDetails());

				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
				out = new ByteArrayOutputStream();

				JasperExportManager.exportReportToPdfStream(jasperPrint, out);
				
			}

		} catch (Exception e) {
			log.error("generateBilldrugReport Error : {} ", e);
		}
		log.info("generateBilldrugReport : End");
		return out;
	}
	
	private BilldrugResponse convertToResponse(BilldrugEntity entity) {
		BilldrugResponse response = modelMapper.map(entity, BilldrugResponse.class);
		
		//set Treatment
		Optional<TreatmentEntity> treatEntity = treatmentRepository.findById(entity.getTmId());
		if (treatEntity.isPresent()) {
			TreatmentResponse treatmentResponse = modelMapper.map(treatEntity.get(), TreatmentResponse.class);
			
			Optional<UserEntity> userEntity = userRepository.findById(treatEntity.get().getUserId());
			if (userEntity.isPresent()) {
				treatmentResponse.setUser(modelMapper.map(userEntity.get(), UserResponse.class));
			}
			response.setTreatment(treatmentResponse);
		}
		
		return response;
	}

	//******** generateTreatmentReport ***********
	
	@Override
	public ByteArrayOutputStream generateTreatmentReport(Integer userId) throws IOException {
		log.info("generateTreatmentReport : Start :: userId : " + userId);
		ByteArrayOutputStream out = null;
		try {
			
			Optional<List<TreatmentEntity>> entity = treatmentRepository.findByUserId(userId);
			if (entity.isPresent()) {
				List<TreatmentResponse> treatments = entity.get().stream().map(this::convertsToResponse).collect(Collectors.toList());
				ClassPathResource reportFile = new ClassPathResource("report/TreatmentReport.jasper");
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getInputStream());

				//parameter
				Optional<UserEntity> userEntity = userRepository.findById(userId);
				if (userEntity.isPresent()) {
					Map<String, Object> parameters = new HashMap<>();
					parameters.put("userId", String.valueOf(userId));
					parameters.put("userTitle", userEntity.get().getUserTitle());
					parameters.put("userFirstname", userEntity.get().getUserFirstname());
					parameters.put("userLastname", userEntity.get().getUserLastname());
					parameters.put("userHnId", userEntity.get().getUserHnId());
					parameters.put("userCardId", userEntity.get().getUserCardId());
					parameters.put("userBirthday", userEntity.get().getUserBirthday());
					parameters.put("userDisease", userEntity.get().getUserDisease());
					parameters.put("userDepartment", userEntity.get().getUserDepartment());

					//list bean
					JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(treatments);
					
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
					out = new ByteArrayOutputStream();

					JasperExportManager.exportReportToPdfStream(jasperPrint, out);
				}
			}

		} catch (Exception e) {
			log.error("generateTreatmentReport Error : {} ", e);
		}
		log.info("generateTreatmentReport : End");
		return out;
	}

	private TreatmentResponse convertsToResponse(TreatmentEntity entity) {
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

	
}//end

