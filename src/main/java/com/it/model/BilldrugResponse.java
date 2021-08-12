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
public class BilldrugResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer billId;
	private Date billDate;
	private Timestamp billTime;
	private String billNext;
	private String drugId;
	private Integer tmId;
	private TreatmentResponse treatment;
}
