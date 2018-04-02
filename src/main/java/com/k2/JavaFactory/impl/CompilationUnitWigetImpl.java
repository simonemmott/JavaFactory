package com.k2.JavaFactory.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

import com.k2.Wiget.annotation.WigetImplementation;
import com.k2.JavaFactory.AJavaWiget;
import com.k2.JavaFactory.JavaAssembly;
import com.k2.JavaFactory.JavaFamily;
import com.k2.JavaFactory.spec.CompilationUnitWiget;
import com.k2.JavaFactory.type.IType;
import com.k2.Util.classes.Dependency;
import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

@WigetImplementation
public class CompilationUnitWigetImpl extends AJavaWiget<IType> implements CompilationUnitWiget{
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, IType> a,
			PrintWriter out) {
		
		JavaAssembly ja = (JavaAssembly)a.assembly();	
		ja.dependenciesForName(a.get(CompilationUnitWiget.model.name));
		
		out.println("package "+a.get(CompilationUnitWiget.model.packageName)+";");
		out.println();
		if (a.get(CompilationUnitWiget.model.dependencies) != null)
			for (Dependency d : a.get(CompilationUnitWiget.model.dependencies))
				out.println(d.getImportClause());
		out.println();
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = a.outputContents(CompilationUnitWiget.model.body, new PrintWriter(sw));
		
		@SuppressWarnings("unchecked")
		Set<Dependency> ds = ja.getDependencies();
		for (Dependency d : ds)
			out.println(d.getImportClause());
		out.println();
		
		out.print(sw.toString());
		
		return out;
	}




	
	

}
