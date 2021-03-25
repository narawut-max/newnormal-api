package com.it.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_province")
public class ProvinceEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer pvnId;
	private String pvnCode;
	private String pvnNameTh;
	private String pvnNameEng;
	private String area;
	
	//GET-SET
	public Integer getPvnId() {
		return pvnId;
	}
	public void setPvnId(Integer pvnId) {
		this.pvnId = pvnId;
	}
	public String getPvnCode() {
		return pvnCode;
	}
	public void setPvnCode(String pvnCode) {
		this.pvnCode = pvnCode;
	}
	public String getPvnNameTh() {
		return pvnNameTh;
	}
	public void setPvnNameTh(String pvnNameTh) {
		this.pvnNameTh = pvnNameTh;
	}
	public String getPvnNameEng() {
		return pvnNameEng;
	}
	public void setPvnNameEng(String pvnNameEng) {
		this.pvnNameEng = pvnNameEng;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
}
