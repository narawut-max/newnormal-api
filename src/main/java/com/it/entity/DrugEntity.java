package com.it.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_drug")
public class DrugEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String drugId;
	private String drugName;
	private String drugTrademark;
	private String drugActive;
	private String drugMfg;
	private String drugExp;
	private BigDecimal drugPrice;
	private String drugAmount;
	private String ctgId;
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDrugTrademark() {
		return drugTrademark;
	}
	public void setDrugTrademark(String drugTrademark) {
		this.drugTrademark = drugTrademark;
	}
	public String getDrugActive() {
		return drugActive;
	}
	public void setDrugActive(String drugActive) {
		this.drugActive = drugActive;
	}
	public String getDrugMfg() {
		return drugMfg;
	}
	public void setDrugMfg(String drugMfg) {
		this.drugMfg = drugMfg;
	}
	public String getDrugExp() {
		return drugExp;
	}
	public void setDrugExp(String drugExp) {
		this.drugExp = drugExp;
	}
	public BigDecimal getDrugPrice() {
		return drugPrice;
	}
	public void setDrugPrice(BigDecimal drugPrice) {
		this.drugPrice = drugPrice;
	}
	public String getDrugAmount() {
		return drugAmount;
	}
	public void setDrugAmount(String drugAmount) {
		this.drugAmount = drugAmount;
	}
	public String getCtgId() {
		return ctgId;
	}
	public void setCtgId(String ctgId) {
		this.ctgId = ctgId;
	}
	
	
	
	
}
