package com.it.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BilldrugDetailReponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer billdrugDetailId;
	private Integer billId;
	private Integer drugId;
	private String drugName;
	private String drugCount;
	private BigDecimal drugPrice;
	private BigDecimal drugTotalPrice;
}
