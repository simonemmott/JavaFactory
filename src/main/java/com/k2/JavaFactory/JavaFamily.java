package com.k2.JavaFactory;

import java.io.PrintWriter;

import com.k2.JavaFactory.spec.ClassWiget;
import com.k2.JavaFactory.spec.CompilationUnitWiget;
import com.k2.Wiget.WigetFamily;

/**
 * The JavaFammily for the Java factory defines the required wigets to create java source code for K2 applications
 * 
 * @author simon
 *
 */
public class JavaFamily extends WigetFamily<PrintWriter> {

	/**
	 * Create a template family specifying the TemplateSpecification and TemplateImplementation as required wigets
	 */
	public JavaFamily() {
		super("JavaFamily", PrintWriter.class, 
				CompilationUnitWiget.class,
				ClassWiget.class);
	}

}
