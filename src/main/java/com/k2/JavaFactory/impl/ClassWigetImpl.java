package com.k2.JavaFactory.impl;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.k2.Wiget.annotation.WigetImplementation;
import com.k2.JavaFactory.AJavaWiget;
import com.k2.JavaFactory.JavaAssembly;
import com.k2.JavaFactory.JavaFamily;
import com.k2.JavaFactory.spec.AnnotationWiget;
import com.k2.JavaFactory.spec.ClassWiget;
import com.k2.JavaFactory.spec.EnumWiget;
import com.k2.JavaFactory.spec.FieldWiget;
import com.k2.JavaFactory.spec.InterfaceWiget;
import com.k2.JavaFactory.spec.MethodWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IEnum;
import com.k2.JavaFactory.type.IField;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethod;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

/**
 * This wiget writes a java type of class
 * The wiget does not include the package clause of the list of dependencies to allow the class wiget to be embedded in other wigets
 * 
 * @author simon
 *
 */
@WigetImplementation
public class ClassWigetImpl extends AJavaWiget<IClass> implements ClassWiget{
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, IClass> a,
			PrintWriter out) {
		
		JavaAssembly ja = (JavaAssembly)a.assembly();	

		// Prepare wiget implementations for other wigets that may be included in the class wiget
		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, AnnotationWiget, IAnnotation> annWiget = ja.assemble(AnnotationWiget.class);
		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, FieldWiget, IField> fieldWiget = ja.assemble(FieldWiget.class);
		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, MethodWiget, IField> methodWiget = ja.assemble(MethodWiget.class);
		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, InterfaceWiget, IField> interfaceWiget = ja.assemble(InterfaceWiget.class);
		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, ClassWiget, IField> classWiget = ja.assemble(ClassWiget.class);
		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, EnumWiget, IField> enumWiget = ja.assemble(EnumWiget.class);
		
		// Write the java doc for the class
		if (a.get(ClassWiget.model.includeJavaDoc)) {
			out = outputTypeJavaDoc(ja, out, 
					a.get(ClassWiget.model.title), 
					a.get(ClassWiget.model.description), 
					a.get(ClassWiget.model.author));
		}
		
		// Write the annotations for the class
		if (a.get(ClassWiget.model.annotations) != null) 
			for (IAnnotation ann : a.get(ClassWiget.model.annotations)) {
				out = annWiget.output(ann, out);
				out.println();
			}
		
		// Write the class clause including the extends clause and the implements clause
		out.print(ja.getIndent()+Visibility.toJava(a.get(ClassWiget.model.visibility))+"class "+a.get(ClassWiget.model.basename)+" ");
		if (a.get(ClassWiget.model.extendsClass) != null) {
			out.print("extends "+a.get(ClassWiget.model.extendsClass).getBasename()+" ");
			ja.addDependencyFor(a.get(ClassWiget.model.extendsClass).getName());
		}
		Set<IInterface> interfaces = a.get(ClassWiget.model.implementsInterfaces);
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
		out.println(ja.getIndent()+"{");
		out.println();
		
		// Increase the indent
		ja.indent();
		
		// Output the included types (Classes, Interfaces or Enumerations)
		List<IType> declaredTypes = a.get(ClassWiget.model.declaredTypes);
		if (declaredTypes != null)
			for (IType type : declaredTypes)
				if (type instanceof IClass)
					out = classWiget.output((IClass)type, out);
				else if (type instanceof IInterface)
					out = interfaceWiget.output((IInterface)type, out);
				else if (type instanceof IEnum)
					out = enumWiget.output((IEnum)type, out);
		
		// Include the wigets added to this class wigets body wiget container
		out = a.outputContents(ClassWiget.model.body, out);	
		
		// Include the fields of the class
		for (IField field : a.get(ClassWiget.model.fields))
			out = fieldWiget.output(field, out);
		
		// Include the methods of the class
		for (IMethod method : a.get(ClassWiget.model.methods))
			out = methodWiget.output(method, out);

		// reduce the indent and close the class definition
		ja.outdent();
		out.println(ja.getIndent()+"}");
		
		return out;
	}

	



	
	

}
