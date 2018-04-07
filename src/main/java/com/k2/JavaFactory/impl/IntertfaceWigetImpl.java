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
import com.k2.JavaFactory.spec.InterfaceWiget;
import com.k2.JavaFactory.spec.MethodSignatureWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IEnum;
import com.k2.JavaFactory.type.IField;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethodSignature;
import com.k2.JavaFactory.type.IType;
import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

/**
 * This wiget writes a java type of interface
 * The wiget does not include the package clause of the list of dependencies to allow the interface wiget to be embedded in other wigets
 * 
 * @author simon
 *
 */
@WigetImplementation
public class IntertfaceWigetImpl extends AJavaWiget<IInterface> implements InterfaceWiget {
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, IInterface> a,
			PrintWriter out) {
		
		JavaAssembly ja = (JavaAssembly)a.assembly();	

		@SuppressWarnings("unchecked")
		AssembledWiget<JavaFamily, PrintWriter, MethodSignatureWiget, IMethodSignature> methWiget = ja.assemble(MethodSignatureWiget.class);
		
		// Write the javadoc for the interface
		if (a.get(InterfaceWiget.model.includeJavaDoc)) {
			out = outputTypeJavaDoc(ja, out, 
					a.get(InterfaceWiget.model.title), 
					a.get(InterfaceWiget.model.description), 
					a.get(InterfaceWiget.model.author));
		}
		
		// Write the annotations for the interface
		if (a.get(InterfaceWiget.model.annotations) != null) {
			@SuppressWarnings("unchecked")
			AssembledWiget<JavaFamily, PrintWriter, AnnotationWiget, IClass> annWiget = ja.assemble(AnnotationWiget.class);
			for (IAnnotation ann : a.get(InterfaceWiget.model.annotations)) {
				out = annWiget.output(ann, out);
				out.println();
			}
		}
		
		// Write the interface clause
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

		// Increase the indent
		ja.indent();
		
		// Write the types declared by this interface
		List<IType> declaredTypes = a.get(InterfaceWiget.model.declaredTypes);
		if (declaredTypes != null)
			for (IType type : declaredTypes)
				if (type instanceof IClass) {
					@SuppressWarnings("unchecked")
					AssembledWiget<JavaFamily, PrintWriter, ClassWiget, IField> classWiget = ja.assemble(ClassWiget.class);
					out = classWiget.output((IClass)type, out);
				}
				else if (type instanceof IInterface) {
					@SuppressWarnings("unchecked")
					AssembledWiget<JavaFamily, PrintWriter, InterfaceWiget, IField> interfaceWiget = ja.assemble(InterfaceWiget.class);
					out = interfaceWiget.output((IInterface)type, out);
				}
				else if (type instanceof IEnum) {
					@SuppressWarnings("unchecked")
					AssembledWiget<JavaFamily, PrintWriter, EnumWiget, IField> enumWiget = ja.assemble(EnumWiget.class);
					out = enumWiget.output((IEnum)type, out);
				}

		// Write the wigets added to the body of this interface wiget
		out = a.outputContents(InterfaceWiget.model.body, out);	
		
		// Write the method signatures of this interface
		Set<IMethodSignature> methods = a.get(InterfaceWiget.model.methods);
		if (methods != null) {
			for (IMethodSignature signature : methods) {
				out = methWiget.output(signature, out);
				out.println(";");
			}
		}
		
		// Reduce the indent and close the interface defintion
		ja.outdent();
		out.println(ja.getIndent()+"}");
		
		return out;
	}

	



	
	

}
