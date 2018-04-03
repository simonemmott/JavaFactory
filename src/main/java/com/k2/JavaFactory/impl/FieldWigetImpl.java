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
import com.k2.JavaFactory.spec.FieldWiget;
import com.k2.JavaFactory.spec.InterfaceWiget;
import com.k2.JavaFactory.spec.MethodSignatureWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IField;
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
public class FieldWigetImpl extends AJavaWiget<IField> implements FieldWiget{
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, IField> a,
			PrintWriter out) {
		
		JavaAssembly ja = (JavaAssembly)a.assembly();	

		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, AnnotationWiget, IAnnotation> annWiget = ja.assemble(AnnotationWiget.class);
		
		out.println();

		if (a.get(FieldWiget.model.includeJavaDoc)) {
			out = outputFieldJavaDoc(ja, out, 
					a.get(FieldWiget.model.title), 
					a.get(FieldWiget.model.description));
		}
			
		if (a.get(FieldWiget.model.annotations) != null) 
			for (IAnnotation ann : a.get(FieldWiget.model.annotations)) {
				out = annWiget.output(ann, out);
				out.println();
			}
		
		Visibility v = a.get(FieldWiget.model.visibility);

		out.print(ja.getIndent()+Visibility.toJava(v));
		IType javaType = a.get(FieldWiget.model.javaType);
		ja.addDependencyFor(javaType.getName());
		out.print(javaType.getBasename()+" ");
		out.println(a.get(FieldWiget.model.name)+";");
		
		if (a.get(FieldWiget.model.includeGetter)) {
			out.print(ja.getIndent()+"public ");
			out.print(javaType.getBasename()+" ");
			out.print("get"+StringUtil.initialUpperCase(a.get(FieldWiget.model.name))+"() ");
			out.println("{ return "+a.get(FieldWiget.model.name)+": }");
		}
		
		if (a.get(FieldWiget.model.includeSetter)) {
			out.print(ja.getIndent()+"public ");
			out.print(a.get(FieldWiget.model.declaringType).getBasename()+" ");
			out.print("set"+StringUtil.initialUpperCase(a.get(FieldWiget.model.name))+"( "+javaType.getBasename()+" "+a.get(FieldWiget.model.name)+" ) ");
			out.println("{ this."+a.get(FieldWiget.model.name)+" = "+a.get(FieldWiget.model.name)+": return this; }");
		}
		
		return out;
	}

	



	
	

}
