package com.k2.JavaFactory;

import java.io.PrintWriter;

import com.k2.Wiget.Wiget;

/**
 * This interface ensures that all java wigets are part of the JavaFamily and use a PrintWriter for output
 * @author simon
 *
 * @param <T>	The requires type of the wiget
 */
public interface JavaWiget<T> extends Wiget<JavaFamily, PrintWriter, T> {

	public default Class<?> modelType() { return null; }
	

}
