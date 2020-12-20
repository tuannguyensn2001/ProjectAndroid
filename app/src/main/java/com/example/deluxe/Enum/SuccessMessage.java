package com.example.deluxe.Enum;

public enum SuccessMessage {
	SUC000001("Đăng ký thành công!"),
	SUC000002("Đăng nhập thành công!"),
	SUC000003("Nạp tiền thành công!"),
	SUC000005("Mật khẩu chính xác!"),
	SUC000007("Đổi mật khẩu thành công");

	private final String value;

	SuccessMessage(String s) {
		this.value = s;
	}

	public String getValue() {
		return value;
	}
}
