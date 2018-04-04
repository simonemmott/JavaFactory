package com.k2.JavaFactory.impl;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.k2.Wiget.annotation.WigetImplementation;
import com.k2.JavaFactory.AJavaWiget;
import com.k2.JavaFactory.JavaAssembly;
import com.k2.JavaFactory.JavaFactory;
import com.k2.JavaFactory.JavaFamily;
import com.k2.JavaFactory.spec.AnnotationWiget;
import com.k2.JavaFactory.spec.ClassWiget;
import com.k2.JavaFactory.spec.CompilationUnitWiget;
import com.k2.JavaFactory.spec.InterfaceWiget;
import com.k2.JavaFactory.spec.MethodSignatureWiget;
import com.k2.JavaFactory.spec.MethodWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethod;
import com.k2.JavaFactory.type.IMethodSignature;
import com.k2.JavaFactory.type.IParameter;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.Dependency;
import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

@WigetImplementation
public class MethodWigetImpl extends AJavaWiget<IMethod> implements MethodWiget{
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, IMethod> a,
			PrintWriter out) {
		
		JavaAssembly ja = (JavaAssembly)a.assembly();	

		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, MethodSignatureWiget, IAnnotation> signatureWiget = ja.assemble(MethodSignatureWiget.class);
		
		out = signatureWiget.output(a.get(MethodWiget.model.unwrap), out);
		out.println(" {");
		ja.indent();
		
		out = a.outputContents(MethodWiget.model.body, out);
		
		for (String line : StringUtil.lines(a.get(MethodWiget.model.methodBody)))
			out.println(ja.getIndent()+line);
		
		ja.outdent();
		out.println(ja.getIndent()+"}");
		
		return out;
	}

	



	
	

}
