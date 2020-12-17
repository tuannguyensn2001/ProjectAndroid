package com.example.deluxe.Helper;

import java.text.DecimalFormat;

public class ConvertData {
	public static String normalizeString(String s) {
		s = s.replaceAll("^\\s*", "");
		s = s.replaceAll("\\s+$", "");
		return s;
	}

	public static String moneyToString(double money) {
		return new DecimalFormat("#,###,###").format(money);
	}
}
