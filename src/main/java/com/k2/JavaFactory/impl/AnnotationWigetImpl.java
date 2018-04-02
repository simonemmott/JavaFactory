package com.k2.JavaFactory.impl;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import com.k2.Wiget.annotation.WigetImplementation;
import com.k2.JavaFactory.AJavaWiget;
import com.k2.JavaFactory.JavaAssembly;
import com.k2.JavaFactory.JavaFactory;
import com.k2.JavaFactory.JavaFamily;
import com.k2.JavaFactory.spec.AnnotationWiget;
import com.k2.JavaFactory.spec.ClassWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

@WigetImplementation
public class AnnotationWigetImpl extends AJavaWiget<IAnnotation> implements AnnotationWiget{
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, IAnnotation> a,
			PrintWriter out) {
		
		JavaAssembly ja = (JavaAssembly)a.assembly();
		
		ja.addDependencyFor(a.get(AnnotationWiget.model.name));
		
		out.println(ja.getIndent()+"@"+a.get(AnnotationWiget.model.basename));
		
		return out;
	}

	




	
	

}
