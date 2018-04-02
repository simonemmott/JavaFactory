package com.k2.JavaFactory;

import java.io.PrintWriter;

import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetFactory;

/**
 * This is the extension of the raw wiget factory for the TemplateFamily wigets
 * @author simon
 *
 */
public class JavaFactory extends WigetFactory<JavaFamily, PrintWriter> {

	/**
	 * Create a Template factory drawing template wiget assemblies from the given packages
	 * 
	 * @param packageNames	The packages and thier sub packages to scan for template wiget assemblies
	 */
	public JavaFactory(String ... packageNames) {
		super(new JavaFamily(), PrintWriter.class, packageNames);
	}

	/**
	 * Create a TemplateAssembly with the giver wiget type as the root wiget of the assembly
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <W extends Wiget,T> JavaAssembly<W,T> getAssembly(Class<W> wigetType) {
		return (JavaAssembly<W,T>) super.getAssembly(JavaAssembly.class, wigetType);
	}

}
