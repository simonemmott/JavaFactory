package com.k2.JavaFactory;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import com.k2.JavaFactory.spec.InterfaceWiget;
import com.k2.JavaFactory.spec.MethodSignatureWiget;
import com.k2.JavaFactory.type.IParameter;
import com.k2.Util.StringUtil;
import com.k2.Wiget.AWiget;

/**
 * This abstract class defines the Family and Output type that is used by all Template witer wigets
 * @author simon
 *
 * @param <T>	The required data type of the wiget
 */
public abstract class AJavaWiget<T> extends AWiget<JavaFamily, PrintWriter, T> {
	
	protected PrintWriter outputTypeJavaDoc(JavaAssembly ja, PrintWriter out, String title, String description, String author) {
		out.println(ja.getIndent()+"/*************************************************************************");
		if (StringUtil.isSet(title))
			out.println(ja.getIndent()+" * <strong>"+title+"</stong>");
		for (String line : StringUtil.lines(description))
			out.println(ja.getIndent()+" * "+line);
		if (StringUtil.isSet(author))
			out.println(ja.getIndent()+" * @author "+author);
		out.println(ja.getIndent()+" */");
		return out;
	}
	
	protected PrintWriter outputFieldJavaDoc(JavaAssembly ja, PrintWriter out, String title, String description) {
		out.println(ja.getIndent()+"/*************************************************************************");
		if (StringUtil.isSet(title))
			out.println(ja.getIndent()+" * <strong>"+title+"</stong>");
		for (String line : StringUtil.lines(description))
			out.println(ja.getIndent()+" * "+line);
		out.println(ja.getIndent()+" */");
		return out;
	}
	
	
	
	
	
	protected PrintWriter outputMethodJavaDoc(JavaAssembly ja, PrintWriter out, String title, String description, List<IParameter> parameters, String returnTypeDescription) {
		out.println(ja.getIndent()+"/*************************************************************************");
		if (StringUtil.isSet(title))
			out.println(ja.getIndent()+" * <strong>"+title+"</strong>");
		for (String line : StringUtil.lines(description))
			out.println(ja.getIndent()+" * "+line);
		Iterator<IParameter> i = parameters.iterator();
		while (i.hasNext()) {
			IParameter p = i.next();
			out.println(ja.getIndent()+" * @param "+p.getName()+" "+p.getTitle());
			for (String line : StringUtil.lines(p.getDescription()))
				out.println(ja.getIndent()+" * \t"+line);
		}
		if (StringUtil.isSet(returnTypeDescription)) {
			out.print(ja.getIndent()+" * @return ");
			boolean firstLine = true;
			for (String line : StringUtil.lines(returnTypeDescription)) {
				if (firstLine) {
					out.println(line);
					firstLine = false;
				} else {
					out.println(ja.getIndent()+" * \t"+line);
				}
			}
			if (firstLine)
				out.println();
		}
		out.println(ja.getIndent()+" */");
		return out;
	}

}
