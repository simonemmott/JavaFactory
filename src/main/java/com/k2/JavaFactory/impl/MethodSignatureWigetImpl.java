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
import com.k2.JavaFactory.spec.InterfaceWiget;
import com.k2.JavaFactory.spec.MethodSignatureWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethodSignature;
import com.k2.JavaFactory.type.IParameter;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.Dependency;
import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

@WigetImplementation
public class MethodSignatureWigetImpl extends AJavaWiget<IMethodSignature> implements MethodSignatureWiget{
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, IMethodSignature> a,
			PrintWriter out) {
		
		JavaAssembly ja = (JavaAssembly)a.assembly();	

		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, AnnotationWiget, IAnnotation> annWiget = ja.assemble(AnnotationWiget.class);
		
		List<IParameter> parms = a.get(MethodSignatureWiget.model.parameters);
		Iterator<IParameter> i = parms.iterator();

		out.println();

		if (a.get(MethodSignatureWiget.model.includeJavaDoc)) {
			out = outputMethodJavaDoc(ja, out, 
					a.get(MethodSignatureWiget.model.title), 
					a.get(MethodSignatureWiget.model.description), 
					parms,
					a.get(MethodSignatureWiget.model.returnTypeDescription));
		}
			
		if (a.get(MethodSignatureWiget.model.annotations) != null) 
			for (IAnnotation ann : a.get(MethodSignatureWiget.model.annotations)) {
				out = annWiget.output(ann, out);
				out.println();
			}
		
		Visibility v = a.get(MethodSignatureWiget.model.visibility);

		out.print(ja.getIndent()+Visibility.toJava(v));
		IType returnType = a.get(MethodSignatureWiget.model.returnType);
		if (returnType == null) {
			out.print("void ");
		} else {
			ja.addDependencyFor(returnType.getName());
			out.print(returnType.getBasename()+" ");
		}
		
		out.print(a.get(MethodSignatureWiget.model.name)+"(");
		
		i = parms.iterator();
		annWiget.set(AnnotationWiget.model.inline, true);
		while (i.hasNext()) {
			IParameter parm = i.next();
			ja.addDependencyFor(parm.getType().getName());
			Set<IAnnotation> annotations = parm.getAnnotations();
			if (annotations != null)
				for (IAnnotation ann : parm.getAnnotations())
					out = annWiget.output(ann, out);
			out.print(parm.getType().getBasename());
			out.print((parm.getIsVarArgs()) ? " ... " : " ");
			out.print(parm.getName());
			
			if (i.hasNext())
				out.print(", ");
			}
		
		out.print(")");
		
		
		return out;
	}

	



	
	

}
