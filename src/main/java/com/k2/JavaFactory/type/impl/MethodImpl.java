package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IMethod;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.classes.Dependency;

/**
 * A basic implementation of the IMethod interface
 * 
 * @author simon
 *
 */
public class MethodImpl extends MethodSignatureImpl implements IMethod {
	
	/**
	 * Create a package method for the declaring class with the given name that return a void
	 * @param declaringClass		The class declaring the method
	 * @param name				The name of the new method
	 */
	public MethodImpl(IClass declaringClass, String name) {
		super(name);
		this.declaringClass = declaringClass;
	}

	/**
	 * Create a method on the declaring class with the given visibility and name returning void
	 * @param declaringClass		The class declaring the method
	 * @param visibility			The visibility of the new class
	 * @param name				The name of the new class
	 */
	public MethodImpl(IClass declaringClass, Visibility visibility, String name) {
		super(visibility, name);
		this.declaringClass = declaringClass;
	}

	/**
	 * Create a method on the declaring class with the given visibility, return type and name
	 * @param declaringClass		The class declaring the method
	 * @param visibility			The visibility of the method
	 * @param returnType			The return type of the method
	 * @param name				The name of the method
	 */
	public MethodImpl(IClass declaringClass, Visibility visibility, IType returnType, String name) {
		super(visibility, returnType, name);
		this.declaringClass = declaringClass;
	}

	/**
	 * Create a package method on the declaring class with the given return type and name
	 * @param declaringClass		The declaring class
	 * @param returnType			The methods return type
	 * @param name				The method name
	 */
	public MethodImpl(IClass declaringClass, IType returnType, String name) {
		super(returnType, name);
		this.declaringClass = declaringClass;
	}

	private final IClass declaringClass;
	@Override
	public IClass getDeclaringClass() { return declaringClass; }
	
	private String methodBody;
	@Override
	public String getMethodBody() { return methodBody; }
	/**
	 * Set the java of the method body
	 * @param methodBody		The source code of the method body
	 * @return		This method for method chaining
	 */
	public MethodImpl setMethodBody(String methodBody) {
		this.methodBody = methodBody;
		return this;
	}
	
	private Set<Dependency> dependencies;
	@Override
	public Set<Dependency> getDependencies() { return dependencies; }
	/**
	 * Add a dependency of the given method. All dependencies of the method body must be included as dependencies of the method.
	 * Note only the method body needs have its dependencies explicitly set. The method signature dependencies are automatically added to the 
	 * assemblies dependencies
	 * @param dependency		A dependency for the method body
	 * @return		This method for method chaining
	 */
	public MethodImpl dependsOn(Dependency dependency) {
		if (dependencies == null)
			dependencies = new TreeSet<Dependency>();
		dependencies.add(dependency);
		return this;
	}
	public MethodImpl dependsOn(Class<?> dependsOnClass) {
		return dependsOn(new Dependency(dependsOnClass));
	}

	protected Object unwrap;
	@Override
	public Object getUnwrap() { return (unwrap==null) ? this : unwrap; }
	/**
	 * Wrap the given object to be returned as the unwrap parameter in the wiget
	 * @param wrap	The object to wrap
	 * @return	This enumerated value for method chaining
	 */
	public MethodImpl wrap(Object wrap) { this.unwrap = wrap; return this; }

	@SuppressWarnings("unchecked")
	public <C extends IClass> C up(Class<C> cls) {
		return (C)declaringClass;
	}



}
