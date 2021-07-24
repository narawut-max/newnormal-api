package com.it.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubdistrictResponse implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer sdtId;
	private String zipCode;
	private String sdtNameTh;
	private String sdtNameEng;
	private Integer disId;
	private DistrictResponse district;
	private ProvinceResponse province;

}
