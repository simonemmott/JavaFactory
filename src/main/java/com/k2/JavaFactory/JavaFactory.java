package com.k2.JavaFactory;

import java.io.PrintWriter;

import com.k2.JavaFactory.type.impl.TypeImpl;
import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetFactory;

/**
 * This is the extension of the raw wiget factory for the JavaFamily wigets
 * @author simon
 *
 */
public class JavaFactory extends WigetFactory<JavaFamily, PrintWriter> {

	/**
	 * Create a Java factory drawing template wiget assemblies from the given packages
	 * 
	 * @param packageNames	The packages and their sub packages to scan for java wiget assemblies
	 */
	public JavaFactory(String ... packageNames) {
		super(new JavaFamily(), PrintWriter.class, packageNames);
	}

	/**
	 * Create a JavaAssembly with the given wiget type as the root wiget of the assembly
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <W extends Wiget,T> JavaAssembly<W,T> getAssembly(Class<W> wigetType) {
		return (JavaAssembly<W,T>) super.getAssembly(JavaAssembly.class, wigetType);
	}
	
	public static TypeImpl INT = new TypeImpl("int");
	public static TypeImpl LONG = new TypeImpl("long");
	public static TypeImpl BOOLEAN = new TypeImpl("boolean");
	public static TypeImpl FLOAT = new TypeImpl("float");
	public static TypeImpl DOUBLE = new TypeImpl("double");
	public static TypeImpl SHORT = new TypeImpl("short");
	public static TypeImpl BYTE = new TypeImpl("byte");
	public static TypeImpl CHAR = new TypeImpl("char");
	public static TypeImpl String = new TypeImpl("java.lang.String");
	public static TypeImpl Integer = new TypeImpl("java.lang.Integer");
	public static TypeImpl Long = new TypeImpl("java.lang.Long");
	public static TypeImpl Boolean = new TypeImpl("java.lang.Boolean");
	public static TypeImpl FLoat = new TypeImpl("java.lang.Float");
	public static TypeImpl Double = new TypeImpl("java.lang.Double");
	public static TypeImpl Character = new TypeImpl("java.lang.Character");
	public static TypeImpl Short = new TypeImpl("java.lang.Short");
	public static TypeImpl Byte = new TypeImpl("java.lang.Byte");
	public static TypeImpl Date = new TypeImpl("java.util.Date");
	public static TypeImpl Class = new TypeImpl("java.lang.Class");
	public static TypeImpl Enum = new TypeImpl("java.lang.Enum");
	public static TypeImpl Annotation = new TypeImpl("java.lang.annotation.Annotation");

}
