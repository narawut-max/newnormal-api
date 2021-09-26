package com.it.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MailFlags {

	SENDTED("S"),
	NOT_SEND("N");
	
	public String value;
}
