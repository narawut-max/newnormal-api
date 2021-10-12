package com.it.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MoneyStatus {

	WAIT("W"), //รอชำระ
	WAITCHECK("WC"), //รอตรวจสอบ
	SUCESS("S"), //ชำระแล้ว
	CANCEL("C"), //แพทย์:ยกเลิก
	NEWFILE("N"); //แนบไฟล์ใหม่
	
	public String value;
}