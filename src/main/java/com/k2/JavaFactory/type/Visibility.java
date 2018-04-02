package com.k2.JavaFactory.type;

public enum Visibility {
	PUBLIC,
	PACKAGE,
	PROTECTED,
	PRIVATE;
	
	public static String toJava(Visibility v) {
		if (v == null) return "";
		switch (v) {
		case PACKAGE:
			return "";
		case PRIVATE:
			return "private ";
		case PROTECTED:
			return "protected ";
		case PUBLIC:
			return "public ";
		default:
			return "";
		}
	}
}
