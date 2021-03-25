package com.it.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_district")
public class DistrictEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer disID;
	private String disCode;
	private String disNameTh;
	private String disNameEng;
	
	//GET-SET
	public Integer getDisID() {
		return disID;
	}
	public void setDisID(Integer disID) {
		this.disID = disID;
	}
	public String getDisCode() {
		return disCode;
	}
	public void setDisCode(String disCode) {
		this.disCode = disCode;
	}
	public String getDisNameTh() {
		return disNameTh;
	}
	public void setDisNameTh(String disNameTh) {
		this.disNameTh = disNameTh;
	}
	public String getDisNameEng() {
		return disNameEng;
	}
	public void setDisNameEng(String disNameEng) {
		this.disNameEng = disNameEng;
	}
	
	
}
