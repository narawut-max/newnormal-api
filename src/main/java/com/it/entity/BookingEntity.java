package com.it.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_booking")
public class BookingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer bkId;
	private String bkQueue;
	private Date bkDate;
	private Timestamp bkTime;
	private String bkSymptom;
	private String bkStatus;
	private String bkProcess;
	private String tmId;
	public Integer getBkId() {
		return bkId;
	}
	public void setBkId(Integer bkId) {
		this.bkId = bkId;
	}
	public String getBkQueue() {
		return bkQueue;
	}
	public void setBkQueue(String bkQueue) {
		this.bkQueue = bkQueue;
	}
	public Date getBkDate() {
		return bkDate;
	}
	public void setBkDate(Date bkDate) {
		this.bkDate = bkDate;
	}
	public Timestamp getBkTime() {
		return bkTime;
	}
	public void setBkTime(Timestamp bkTime) {
		this.bkTime = bkTime;
	}
	public String getBkSymptom() {
		return bkSymptom;
	}
	public void setBkSymptom(String bkSymptom) {
		this.bkSymptom = bkSymptom;
	}
	public String getBkStatus() {
		return bkStatus;
	}
	public void setBkStatus(String bkStatus) {
		this.bkStatus = bkStatus;
	}
	public String getBkProcess() {
		return bkProcess;
	}
	public void setBkProcess(String bkProcess) {
		this.bkProcess = bkProcess;
	}
	public String getTmId() {
		return tmId;
	}
	public void setTmId(String tmId) {
		this.tmId = tmId;
	}
	
	//GET-SET
	
}
