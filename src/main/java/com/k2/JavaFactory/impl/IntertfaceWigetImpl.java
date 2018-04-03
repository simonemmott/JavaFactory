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
import com.k2.JavaFactory.spec.InterfaceWiget;
import com.k2.JavaFactory.spec.MethodSignatureWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethodSignature;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.Dependency;
import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

@WigetImplementation
public class IntertfaceWigetImpl extends AJavaWiget<IInterface> implements InterfaceWiget{
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, IInterface> a,
			PrintWriter out) {
		
		JavaAssembly ja = (JavaAssembly)a.assembly();	

		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, AnnotationWiget, IClass> annWiget = ja.assemble(AnnotationWiget.class);
		AssembledWiget<JavaFamily, PrintWriter, MethodSignatureWiget, IMethodSignature> methWiget = ja.assemble(MethodSignatureWiget.class);
		
		if (a.get(InterfaceWiget.model.includeJavaDoc)) {
			out = outputTypeJavaDoc(ja, out, 
					a.get(InterfaceWiget.model.title), 
					a.get(InterfaceWiget.model.description), 
					a.get(InterfaceWiget.model.author));
		}
		if (a.get(InterfaceWiget.model.annotations) != null) 
			for (IAnnotation ann : a.get(InterfaceWiget.model.annotations)) {
				out = annWiget.output(ann, out);
				out.println();
			}
		out.print("public interface "+a.get(InterfaceWiget.model.basename)+" ");
		Set<IInterface> interfaces = a.get(InterfaceWiget.model.extendsInterfaces);
		if (interfaces != null && interfaces.size() > 0) {
			out.print("extends ");
			Iterator<IInterface> i = interfaces.iterator();
			while (i.hasNext()) {
				IInterface iFace = i.next();
				out.print(iFace.getBasename());
				ja.addDependencyFor(iFace.getName());
				if (i.hasNext())
					out.print(", ");
				else
					out.print(" ");
			}
		}
		out.println(ja.getIndent()+"{");

		ja.indent();
		out = a.outputContents(InterfaceWiget.model.body, out);	
		
		Set<IMethodSignature> methods = a.get(InterfaceWiget.model.methods);
		for (IMethodSignature signature : methods) {
			out = methWiget.output(signature, out);
			out.println(";");
			
		}
		
		ja.outdent();
		out.println("}");
		
		return out;
	}

	



	
	

}
