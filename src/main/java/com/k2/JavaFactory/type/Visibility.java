package com.k2.JavaFactory.type;

/**
 * This enumeration defines the possible visibility settings of types, methods and fields.
 * 
 * @author simon
 *
 */
public enum Visibility {
	PUBLIC,
	PACKAGE,
	PROTECTED,
	PRIVATE;
	
	/**
	 * @param v	The java source code for the given visibility. A null value will result in the empty string "", i.e. package visibility
	 * @return	The java source corresponding to the given visibility
	 */
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
