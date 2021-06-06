package com.it.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.it.service.ReportService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportServiceImpl implements ReportService {

	private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

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

}
