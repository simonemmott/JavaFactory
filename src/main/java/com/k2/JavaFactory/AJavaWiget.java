package com.k2.JavaFactory;

import java.io.PrintWriter;

import com.k2.Wiget.AWiget;

/**
 * This abstract class defines the Family and Output type that is used by all Template witer wigets
 * @author simon
 *
 * @param <T>	The required data type of the wiget
 */
public abstract class AJavaWiget<T> extends AWiget<JavaFamily, PrintWriter, T> {

}
