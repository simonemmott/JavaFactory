package com.k2.JavaFactory;

import java.io.PrintWriter;

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

}
