package com.it.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.it.entity.BilldrugEntity;
import com.it.entity.TreatmentEntity;
import com.it.entity.UserEntity;
import com.it.model.BilldrugResponse;
import com.it.model.TreatmentResponse;
import com.it.model.UserResponse;
import com.it.repository.BilldrugRepository;
import com.it.repository.TreatmentRepository;
import com.it.repository.UserRepository;
import com.it.service.ReportService;

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
	private TreatmentRepository treatmentRepository;
	
	@Autowired
	private UserRepository userRepository;

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

				//parameter
				Map<String, Object> parameters = new HashMap<>();
				parameters.put("BillId", String.valueOf(billId));
				parameters.put("userHnId", billdrugResponse.getTreatment().getUser().getUserHnId());
				parameters.put("tmTime", billdrugResponse.getTreatment().getTmTime());
				parameters.put("userTitle", billdrugResponse.getTreatment().getUser().getUserTitle());
				parameters.put("userFirstname", billdrugResponse.getTreatment().getUser().getUserFirstname());
				parameters.put("userLastname", billdrugResponse.getTreatment().getUser().getUserLastname());
				parameters.put("userDisease", billdrugResponse.getTreatment().getUser().getUserDisease());
				parameters.put("userDepartment", billdrugResponse.getTreatment().getUser().getUserDepartment());
				
				//list bean
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(billdrugResponse.getBilldrugDetails());
				
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
			Optional<UserEntity> userEntity = userRepository.findById(Integer.parseInt(treatEntity.get().getUserId()));
			if (userEntity.isPresent()) {
				treatmentResponse.setUser(modelMapper.map(userEntity.get(), UserResponse.class));
			}
			response.setTreatment(treatmentResponse);
		}
		
		return response;
	}

}
