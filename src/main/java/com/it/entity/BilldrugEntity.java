package com.it.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_billdrug")
public class BilldrugEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer billId;
	private Date billDate;
	private Timestamp billTime;
	private String billNext;
	//private String drugId;
	private Integer tmId;
	
	@OneToMany(mappedBy="billdrug")
	private List<BilldrugDetailEntity> billdrugDetails;
}
