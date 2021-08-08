package com.it.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_treatment")
public class TreatmentEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String tmId;
	private Date tmDate;
	private Timestamp tmTime;
	private BigDecimal tmMoney;
	private String tmSlip;
	private String tmStatus;
	private String userId;
	private Integer bkId;
	private Integer billId;
	
	//GET-SET
	public String getTmId() {
		return tmId;
	}
	public void setTmId(String tmId) {
		this.tmId = tmId;
	}
	public Date getTmDate() {
		return tmDate;
	}
	public void setTmDate(Date tmDate) {
		this.tmDate = tmDate;
	}
	public Timestamp getTmTime() {
		return tmTime;
	}
	public void setTmTime(Timestamp tmTime) {
		this.tmTime = tmTime;
	}
	public BigDecimal getTmMoney() {
		return tmMoney;
	}
	public void setTmMoney(BigDecimal tmMoney) {
		this.tmMoney = tmMoney;
	}
	public String getTmSlip() {
		return tmSlip;
	}
	public void setTmSlip(String tmSlip) {
		this.tmSlip = tmSlip;
	}
	public String getTmStatus() {
		return tmStatus;
	}
	public void setTmStatus(String tmStatus) {
		this.tmStatus = tmStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getBkId() {
		return bkId;
	}
	public void setBkId(Integer bkId) {
		this.bkId = bkId;
	}
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	

}
