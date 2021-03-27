package com.it.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_billdrug")
public class BilldrugEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer billId;
	private Date billDate;
	private Timestamp billTime;
	private String drugId;
	
	//GET-SET
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public Timestamp getBillTime() {
		return billTime;
	}
	public void setBillTime(Timestamp billTime) {
		this.billTime = billTime;
	}
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	
}
