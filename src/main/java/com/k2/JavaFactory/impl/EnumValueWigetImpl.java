package com.k2.JavaFactory.impl;

import java.io.PrintWriter;

import com.k2.Wiget.annotation.WigetImplementation;
import com.k2.JavaFactory.AJavaWiget;
import com.k2.JavaFactory.JavaAssembly;
import com.k2.JavaFactory.JavaFamily;
import com.k2.JavaFactory.spec.AnnotationWiget;
import com.k2.JavaFactory.spec.EnumValueWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IEnumValue;
import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

/**
 * This wiget output the java source code for an enumeration value
 * 
 * This wiget should only be included within an enumeration wiget
 * 
 * @author simon
 *
 */
@WigetImplementation
public class EnumValueWigetImpl extends AJavaWiget<IEnumValue> implements EnumValueWiget{
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, IEnumValue> a,
			PrintWriter out) {
		
		JavaAssembly ja = (JavaAssembly)a.assembly();	
		
		// Write the field java doc for the enum value 
		if (a.get(EnumValueWiget.model.includeJavaDoc)) {
			out = outputFieldJavaDoc(ja, out, 
					a.get(EnumValueWiget.model.title), 
					a.get(EnumValueWiget.model.description));
		}
			
		// Write the annotations for the enum value
		if (a.get(EnumValueWiget.model.annotations) != null) {
			// Get an annotation wiget to write the annotations
			@SuppressWarnings("unchecked")
			AssembledWiget<JavaFamily, PrintWriter, AnnotationWiget, IAnnotation> annWiget = ja.assemble(AnnotationWiget.class);
			for (IAnnotation ann : a.get(EnumValueWiget.model.annotations)) {
				out = annWiget.output(ann, out);
				out.println();
			}
		}
		
		// Write the enum value name
		out.print(ja.getIndent()+a.get(EnumValueWiget.model.name));
		
		return out;
	}

	



	
	

}
