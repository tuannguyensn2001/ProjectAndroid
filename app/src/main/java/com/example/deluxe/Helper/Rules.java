package com.example.deluxe.Helper;

public class Rules {

	public static boolean required(String s) {
		return s.length() > 0;
	}

	public static boolean min(String value, int length) {
		return value.length() >= length;

	}

	public static boolean isEmail(String value) {
		String emailPattern = "\\w+@\\w+[.]\\w+";
		return value.matches(emailPattern);
	}

	public static boolean isPassword(String value) {
		String passwordPattern = "\\w+";
		return value.matches((passwordPattern));
	}

	public static boolean isSameDate(String str1, String str2) {
		String[] date1 = str1.split("\\s");
		String[] date2 = str2.split("\\s");

		return date1[1].equals(date2[1]) && date1[2].equals(date2[2]) && date1[5].equals(date2[5]);
	}

	public static boolean stringLength(String serialInput, int i) {
		return serialInput.length() == i;
	}

	public static boolean isSpace(String s) {
		return s.replaceAll("[\\n\\t ]", "").isEmpty();
	}

}