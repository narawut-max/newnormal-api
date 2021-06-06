package com.it.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface ReportService {
	public ByteArrayOutputStream generateReport() throws IOException;
}
