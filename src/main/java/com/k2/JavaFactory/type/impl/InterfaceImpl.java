package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethodSignature;
import com.k2.Util.classes.Dependency;
/**
 * A basic implementation of the IInterface interface
 * 
 * @author simon
 *
 */
public class InterfaceImpl extends TypeImpl implements IInterface {
	
	/**
	 * Create a new interface for the given canonical name
	 * @param name	The canonical name of the interface
	 */
	public InterfaceImpl(String name) { super(name); }

	@Override public InterfaceImpl setAuthor(String author) { super.setAuthor(author); return this; }
	@Override public InterfaceImpl setTitle(String title) { super.setTitle(title); return this; }
	@Override public InterfaceImpl setDescription(String description) { super.setDescription(description); return this; }
	@Override public InterfaceImpl setIncludeJavaDoc(Boolean includeJavaDoc) { super.setIncludeJavaDoc(includeJavaDoc); return this; }
	@Override public InterfaceImpl annotate(IAnnotation annotation) { super.annotate(annotation); return this; }
	@Override public InterfaceImpl wrap(Object wrap) { super.wrap(wrap); return this; }
	@Override public InterfaceImpl add(Dependency dependency) { super.add(dependency); return this; }

	

	private Set<IInterface> extendsInterfaces;
	@Override
	public Set<IInterface> getExtendsInterfaces() { return extendsInterfaces; }
	/**
	 * Identify that this interface extends the given interface
	 * @param extendsInterface	The interface that this interface extends
	 * @return		This interface for method chaining
	 */
	public InterfaceImpl extendsInterface(IInterface extendsInterface) {
		if (extendsInterfaces == null)
			extendsInterfaces = new TreeSet<IInterface>();
		extendsInterfaces.add(extendsInterface);
		return this;
	}
	/**
	 * Identify that this interface extends the interface with the given canonical name
	 * @param interfaceName	The canonical name of the interface that this interface extends
	 * @return		This interface for method chaining
	 */
	public InterfaceImpl extendsInterface(String interfaceName) {
		return extendsInterface(new InterfaceImpl(interfaceName));
	}

	
	private Set<IMethodSignature> methods;
	@Override
	public Set<IMethodSignature> getMethods() { return methods; }
	/**
	 * Add the method signature to the interface definition
	 * @param method		The method signature to add to the interface
	 * @return		This interface for method chaining
	 */
	public InterfaceImpl add(IMethodSignature method) {
		if (methods == null)
			methods = new TreeSet<IMethodSignature>();
		methods.add(method);
		return this;
	}


}
