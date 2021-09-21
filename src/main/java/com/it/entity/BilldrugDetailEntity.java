package com.it.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_billdrug_detail")
public class BilldrugDetailEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer billdrugDetailId;
	private Integer billId;
	private String drugId;
	private String drugName;
	private String drugCount;
	private BigDecimal drugPrice;
	private BigDecimal drugTotalPrice;
	
	@ManyToOne
    @JoinColumn(name="billId", nullable=true, insertable = false, updatable = false)
	private BilldrugEntity billdrug;

}
