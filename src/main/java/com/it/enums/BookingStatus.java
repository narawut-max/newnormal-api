package com.it.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BookingStatus {
	NEXT("N"), // ข้าม
	WAIT("W"), //รอรักษา
	SUCCESS("S"), //รักษาแล้ว
	CANCEL("C"); //ยกเลิกจอง
	
	public String value;
}

