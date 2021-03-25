package com.it.entity;

import java.io.Serializable;
import java.security.Timestamp;
import java.text.DecimalFormat;
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
	private DecimalFormat tmMoney;
	private String tmSlip;
	private String tmStatus;
	
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
	public DecimalFormat getTmMoney() {
		return tmMoney;
	}
	public void setTmMoney(DecimalFormat tmMoney) {
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
	
	

}
