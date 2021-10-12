package com.it.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BillDrugStatus {

	ORDER("O"), //สั่งยา
	SUCCESS("S"), //จ่ายยาแล้ว
	CANCEL("C"); //ยกเลิกการสั่ง
	
	public String value;
}
