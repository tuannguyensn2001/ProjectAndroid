package com.example.deluxe.Helper;

public class ConvertData {
	public static String normalizeString(String s) {
		s = s.replaceAll("^\\s*", "");
		s = s.replaceAll("\\s+$", "");
		return s;
	}
}
