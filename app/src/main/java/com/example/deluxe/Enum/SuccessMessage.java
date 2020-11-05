package com.example.deluxe.Enum;

public enum SuccessMessage {
	SUC000001("Dang ky thanh cong!"),
	SUC000002("Dang nhap thanh cong!"),
	SUC000003("Nap tien thanh cong!");

	private final String value;
	SuccessMessage(String s) {
		this.value = s;
	}

	public String getValue() {
		return value;
	}
}
