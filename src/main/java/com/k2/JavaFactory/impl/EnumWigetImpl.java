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
import com.k2.JavaFactory.spec.EnumValueWiget;
import com.k2.JavaFactory.spec.EnumWiget;
import com.k2.JavaFactory.spec.FieldWiget;
import com.k2.JavaFactory.spec.InterfaceWiget;
import com.k2.JavaFactory.spec.MethodWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IEnum;
import com.k2.JavaFactory.type.IEnumValue;
import com.k2.JavaFactory.type.IField;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethod;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.Dependency;
import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

@WigetImplementation
public class EnumWigetImpl extends AJavaWiget<IEnum> implements EnumWiget{
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, IEnum> a,
			PrintWriter out) {
		
		JavaAssembly ja = (JavaAssembly)a.assembly();	

		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, AnnotationWiget, IAnnotation> annWiget = ja.assemble(AnnotationWiget.class);
		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, EnumValueWiget, IEnumValue> valueWiget = ja.assemble(EnumValueWiget.class);
		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, InterfaceWiget, IField> interfaceWiget = ja.assemble(InterfaceWiget.class);
		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, ClassWiget, IField> classWiget = ja.assemble(ClassWiget.class);
		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, EnumWiget, IField> enumWiget = ja.assemble(EnumWiget.class);
		
		if (a.get(EnumWiget.model.includeJavaDoc)) {
			out = outputTypeJavaDoc(ja, out, 
					a.get(EnumWiget.model.title), 
					a.get(EnumWiget.model.description), 
					a.get(EnumWiget.model.author));
		}
		if (a.get(EnumWiget.model.annotations) != null) 
			for (IAnnotation ann : a.get(EnumWiget.model.annotations)) {
				out = annWiget.output(ann, out);
				out.println();
			}
		out.print(ja.getIndent()+Visibility.toJava(a.get(EnumWiget.model.visibility))+"enum "+a.get(EnumWiget.model.basename)+" ");
		Set<IInterface> interfaces = a.get(EnumWiget.model.implementsInterfaces);
		if (interfaces != null && interfaces.size() > 0) {
			out.print("implements ");
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
		out.println("{");
		out.println();
		ja.indent();

		
		List<IType> declaredTypes = a.get(EnumWiget.model.declaredTypes);
		if (declaredTypes != null)
			for (IType type : declaredTypes)
				if (type instanceof IClass)
					out = classWiget.output((IClass)type, out);
				else if (type instanceof IInterface)
					out = interfaceWiget.output((IInterface)type, out);
				else if (type instanceof IEnum)
					out = enumWiget.output((IEnum)type, out);
		
		for (IEnumValue value : a.get(EnumWiget.model.values))
			out = valueWiget.output(value, out);
		out = a.outputContents(EnumWiget.model.body, out);		

		ja.outdent();
		out.println(ja.getIndent()+"}");
		
		return out;
	}

	



	
	

}
