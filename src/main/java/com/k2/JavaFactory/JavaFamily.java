package com.k2.JavaFactory;

import java.io.PrintWriter;

import com.k2.JavaFactory.spec.AnnotationWiget;
import com.k2.JavaFactory.spec.ClassWiget;
import com.k2.JavaFactory.spec.CompilationUnitWiget;
import com.k2.JavaFactory.spec.EnumValueWiget;
import com.k2.JavaFactory.spec.EnumWiget;
import com.k2.JavaFactory.spec.FieldWiget;
import com.k2.JavaFactory.spec.InterfaceWiget;
import com.k2.JavaFactory.spec.MethodSignatureWiget;
import com.k2.JavaFactory.spec.MethodWiget;
import com.k2.Wiget.WigetFamily;

/**
 * The JavaFammily for the Java factory defines the required wigets to create java source code for K2 applications
 * 
 * @author simon
 *
 */
public class JavaFamily extends WigetFamily<PrintWriter> {

	/**
	 * Create a java family specifying the JavaWigetSpecifications that must be implemented for this family to be complete
	 */
	public JavaFamily() {
		super("JavaFamily", PrintWriter.class, 
				AnnotationWiget.class,
				ClassWiget.class,
				CompilationUnitWiget.class,
				EnumValueWiget.class,
				EnumWiget.class,
				FieldWiget.class,
				InterfaceWiget.class,
				MethodSignatureWiget.class,
				MethodWiget.class);
	}

}
