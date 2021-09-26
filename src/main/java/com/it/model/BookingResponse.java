 package com.it.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer bkId;
	private String bkQueue;
	private String bkDate;
	private String bkTime;
	private String bkSymptom;
	private String bkStatus;
	private String bkDepartment;
	private String userId;
	private TreatmentResponse treatment;
	private UserResponse user;
}
