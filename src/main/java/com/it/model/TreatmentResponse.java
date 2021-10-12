package com.it.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class TreatmentResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer tmId;
	private Date tmDate;
	private Timestamp tmTime;
	private BigDecimal tmMoney;
	private String tmSlip;
	private String tmStatus;
	private String tmProcess;
	private Integer userId;
	private Integer bkId;
	private Integer billId;
	private BilldrugResponse billdrug;
	private BookingResponse booking;
	private UserResponse user;
	
}
