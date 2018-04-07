package com.k2.JavaFactory.impl;

import java.io.PrintWriter;
import java.util.Set;

import com.k2.Wiget.annotation.WigetImplementation;
import com.k2.JavaFactory.AJavaWiget;
import com.k2.JavaFactory.JavaAssembly;
import com.k2.JavaFactory.JavaFamily;
import com.k2.JavaFactory.spec.MethodSignatureWiget;
import com.k2.JavaFactory.spec.MethodWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IMethod;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.Dependency;
import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

/**
 * Write the java source for a method in a class
 * @author simon
 *
 */
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
		
		// Add the dependencies of the method to the assemblies dependencies
		Set<Dependency> dependencies = a.get(MethodWiget.model.dependencies);
		if (dependencies != null) 
			for (Dependency d : dependencies)
				ja.addDependencyFor(d.getName());
		
		
		// Write the method signature and add an '{'
		out = signatureWiget.output(a.get(MethodWiget.model.unwrap), out);
		out.println(" {");
		ja.indent();
		
		// Write any wigets added to the method wiget
		out = a.outputContents(MethodWiget.model.body, out);
		
		// For each line in the supplied method body add the line to the java source with the appropriate indent
		for (String line : StringUtil.lines(a.get(MethodWiget.model.methodBody)))
			out.println(ja.getIndent()+line);
		
		ja.outdent();
		out.println(ja.getIndent()+"}");
		
		return out;
	}

	



	
	

}
