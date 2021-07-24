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
public class ProvinceResponse implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer pvnId;
	private String pvnCode;
	private String pvnNameTh;
	private String pvnNameEng;
	private String area;

}
