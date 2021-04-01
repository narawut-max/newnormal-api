package com.it.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_subdistrict")
public class SubdistrictEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer sdtId;
	private String zipCode;
	private String sdtNameTh;
	private String sdtNameEng;
	private Integer disId;
	
	//Get-Set
	public Integer getSdtId() {
		return sdtId;
	}
	public void setSdtId(Integer sdtId) {
		this.sdtId = sdtId;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getSdtNameTh() {
		return sdtNameTh;
	}
	public void setSdtNameTh(String sdtNameTh) {
		this.sdtNameTh = sdtNameTh;
	}
	public String getSdtNameEng() {
		return sdtNameEng;
	}
	public void setSdtNameEng(String sdtNameEng) {
		this.sdtNameEng = sdtNameEng;
	}
	public Integer getDisId() {
		return disId;
	}
	public void setDisId(Integer disId) {
		this.disId = disId;
	}
	
	
}
