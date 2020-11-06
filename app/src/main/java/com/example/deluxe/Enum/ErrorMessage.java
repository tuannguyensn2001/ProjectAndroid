package com.example.deluxe.Enum;

public enum ErrorMessage {
	//	Dang ky ---------------------------
	ERR000000("Trống trường"),
	ERR000001("Có khớp quái đâu mà đòi đăng ký?"),
	ERR000002("Ngắn quá!"),
	ERR000003("Đấy mà là email à???"),
	//	---------
	ERR010000("Tên tài khoản có rồi"),
	ERR010001("Email trùng rồi"),
	ERR010002("Mạng lag, thử lại!"),

	//	Dang nhap ---------------------------
	ERR100000("Trống trường"),
	ERR100001("Nhập email má ơi."),
	ERR100002("Ngắn quá!"),
	//	---------
	ERR110000("Sai tài khoản hoặc mật khẩu"),
	ERR110001("Mạng lag, thử lại!"),

	//	Nap the ---------------------------
	ERR200000("Trống trường"),
	ERR200001("Ngắn quá!"),
	ERR200002("Sai cú pháp, xem lại thẻ đi!"),
	//	---------
	ERR210000("Nạp quá nhiều rồi."),
	ERR210001("Thẻ không tồn tại!"),
	ERR210002("Thẻ nạp rồi. Chúc bạn may mắn lần sau"),
	ERR210003("Mạng lag, đợi nha."),
	//	Rut tien --------------------------
	ERR300000("Trống trường"),
	ERR300001("Ki bo vừa thôi!"),
	//	---------
	ERR310000("Có đủ đâu mà đòi chuyển?"),
	ERR310001("Mạng lag, đợi nha."),
	//	Rut tien --------------------------
	ERR400000("Trống trường"),
	ERR400001("Ki bo vừa thôi!"),
	//	---------
	ERR410000("Có đủ đâu mà đòi rút?"),
	ERR410001("Mạng lag, đợi nha.");

	private final String value;

	ErrorMessage(String s) {
		this.value = s;
	}

	public String getValue() {
		return value;
	}
}
