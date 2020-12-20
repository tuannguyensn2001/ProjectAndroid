package com.example.deluxe.Enum;

public enum ErrorMessage {
	//	Dang ky ---------------------------
	ERR000000("Trống trường"),
	ERR000001("Mật khẩu không khớp"),
	ERR000002("Ngắn quá!"),
	ERR000003("Phải là email bạn ơi?"),
	ERR000004("Không được chứa kí tự đặc biệt"),

	//	---------
	ERR010000("Tên tài khoản có rồi"),
	ERR010001("Email trùng rồi"),
	ERR010002("Mạng lag, thử lại!"),

	//	Dang nhap ---------------------------
	ERR100000("Trống trường"),
	ERR100001("Nhập email bạn ơi."),
	ERR100002("Ngắn quá!"),
	ERR100003("Không được chứa kí tự đặc biệt"),

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

	//	Chuyen tien --------------------------
	ERR300000("Trống trường"),
	ERR300001("Sồ tiền phải lớn hơn 1000 nhé!"),
	//	---------
	ERR310000("Số tiền bạn có không đủ"),
	ERR310001("Mạng lag, đợi nha."),
	ERR310002("Thất bại, vui lòng thử lại!"),

	//	Rut tien --------------------------
	ERR400000("Trống trường"),
	ERR400001("Số tiền phải lớn hơn 1000 nhé!"),
	//	---------
	ERR410000("Số tiền bạn có không đủ"),
	ERR410001("Mạng lag, đợi nha."),

	// Xac nhan mat khau------
	ERR500000("Trống trường"),
	ERR500001("Mật khẩu xác nhận ngắn quá, độ dài tối thiểu 6 kí tự nha"),
	ERR500002("Mật khẩu không được chứa kí tự đặc biệt"),
	ERR500003("Nhập sai rồi, vui lòng nhập lại!"),

	// Doi thong tin nguoi dung-----
	ERR600000("Trống trường"),
	ERR600001("Ngắn quá"),
	ERR600002("Mật khẩu mới không được trùng cái cũ"),
	//
	ERR710001("Lỗi rồi, thử lại");

	private final String value;

	ErrorMessage(String s) {
		this.value = s;
	}

	public String getValue() {
		return value;
	}
}