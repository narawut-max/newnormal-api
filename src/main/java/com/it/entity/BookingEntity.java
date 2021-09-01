package com.it.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_booking")
public class BookingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer bkId;
	private String bkQueue;
	private Date bkDate;
	private Timestamp bkTime;
	private String bkSymptom;
	private String bkStatus;

}