package com.k2.JavaFactory;

import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Util.classes.Dependencies;
import com.k2.Util.classes.Dependency;
import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetAssembly;
import com.k2.Wiget.WigetFactory;

/**
 * The template assembly that is used to assemble template wigets
 * 
 * The indentation functionality is additional to the functionality required by the raw wiget assembly and are used by all java wigets
 * 
 * @author simon
 *
 * @param <W>	The type of the root wiget in the assembly
 * @param <T>	The required type of the root wiget in the assembly
 */
@SuppressWarnings("rawtypes")
public class JavaAssembly<W extends Wiget,T> extends WigetAssembly<JavaFamily,PrintWriter, W,T> {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	/**
	 * Create a java assembly for the given factory and root wiget type, using an indent string of '\t'
	 * @param factory	The JavaFactory instance being used
	 * @param wigetType	The wiget type of the root assembly
	 */
	public JavaAssembly(
			WigetFactory<JavaFamily, PrintWriter> factory, 
			Class<W> wigetType) {
		super(factory, wigetType);
		this.indentStr = "\t";
	}
	
	/**
	 * Create a java assembly for the given java factory, root wiget type and indent string
	 * @param factory	The JavaFactory instance being used
	 * @param wigetType	The wiget type of the root assembly
	 * @param indentStr	The String to use for each indentation
	 */
	public JavaAssembly(
			WigetFactory<JavaFamily, PrintWriter> factory, 
			Class<W> wigetType, String indentStr) {
		super(factory, wigetType);
		this.indentStr = indentStr;
	}
	
	private int indent = 0;
	private final String indentStr;
	private String currentIndent = "";
	
	/**
	 * Increase the current indent
	 */
	public void indent() { 
		indent++; 
		currentIndent = currentIndent+indentStr;
	}
	/**
	 * Decrease the current indent
	 */
	public void outdent() { 
		if (indent>0) {
			indent--;
			currentIndent = currentIndent.substring(0, currentIndent.length()-indentStr.length());
		} else {
			indent = 0;
			currentIndent = "";
		}
	}
	/**
	 * @return	The current indent as a string
	 */
	public String getIndent() { return currentIndent; }
	
	public String getIndent(boolean inline) { return (inline) ? "" : currentIndent; }
	
	private Dependencies dependencies;
	/**
	 * This method setup the dependencies of this assembly to manage the dependencies for the named type.
	 * @param name	The name (including package) of the java type being assembled
	 */
	public void dependenciesForName(String name) {
		dependencies = Dependencies.forName(name);
		logger.trace("Setting dependnecies on java factory {} to {}", this, dependencies);
	}
	
	/**
	 * @return	The dependencies of the assembled java type if a java type was named using `depemdenciesForName()` or the full list of classes
	 * included in the java assembly if no type has been named
	 */
	public Set<Dependency> getDependencies() {
		if (dependencies!=null)
			return dependencies.getDependencies();
		return new HashSet<Dependency>(0);
	}
	
	/**
	 * The the given class as a dependency for this assembly
	 * @param cls	The class on which this assembly depends
	 */
	public void addDependencyFor(Class<?> cls) {
		if (dependencies==null)
			dependencies = new Dependencies();
		dependencies.add(cls);
	}
	
	/**
	 * The name of a class on which this java assembly will depend
	 * @param className	The dependency class name
	 */
	public void addDependencyFor(String className) {
		if (dependencies==null)
			dependencies = new Dependencies();
		logger.trace("Adding dependency for {} to Java assembly {} with dependencies {}", className, this, dependencies );
		dependencies.add(Dependency.fromString(className));
	}
	

}
