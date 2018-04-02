package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethodSignature;
import com.k2.Util.classes.Dependency;

public class InterfaceImpl extends TypeImpl implements IInterface {
	
	public InterfaceImpl(String name) { super(name); }


	private Set<IInterface> extendsInterfaces;
	@Override
	public Set<IInterface> getExtendsInterfaces() { return extendsInterfaces; }
	public InterfaceImpl extendsInterface(IInterface extendsInterface) {
		if (extendsInterfaces == null)
			extendsInterfaces = new TreeSet<IInterface>();
		extendsInterfaces.add(extendsInterface);
		return this;
	}

	private Set<IAnnotation> annotations;
	@Override
	public Set<IAnnotation> getAnnotations() { return annotations; }
	public InterfaceImpl addAnnotation(IAnnotation annotation) {
		if (annotations == null)
			annotations = new TreeSet<IAnnotation>();
		annotations.add(annotation);
		return this;
	}

	@Override
	public InterfaceImpl addDependency(Dependency dependency) {
		return (InterfaceImpl) super.addDependency(dependency);
	}
	
	private Set<IMethodSignature> methods;
	@Override
	public Set<IMethodSignature> getMethods() { return methods; }
	public InterfaceImpl addMethod(IMethodSignature method) {
		if (methods == null)
			methods = new TreeSet<IMethodSignature>();
		methods.add(method);
		return this;
	}

}
