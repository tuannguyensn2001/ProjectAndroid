package com.example.deluxe.Enum;

public enum TransactionType {
	//	Nhan tien
	DEPOSIT("Nạp tiền"),
	RECEIVE("Nhận tiền"),

	//Mat tien
	TRANSFER("Chuyển tiền"),
	WITHDRAW("Rút tiền"),
	USE("Tiêu tiền");


	private final String value;

	TransactionType(String s) {
		this.value = s;
	}

	public String getValue() {
		return value;
	}
}
