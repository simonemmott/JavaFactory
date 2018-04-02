package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.classes.Dependency;

public class ClassImpl extends TypeImpl implements IClass {
	
	public ClassImpl(String name) { super(name); }

	private Visibility visibility;
	@Override
	public Visibility getVisibility() { return visibility; }
	public ClassImpl visibility(Visibility visibility) {
		this.visibility = visibility;
		return this;
	}

	private IClass extendsClass;
	@Override
	public IClass getExtendsClass() { return extendsClass; }
	public ClassImpl extendsClass(IClass extendsClass) { 
		this.extendsClass = extendsClass; 
		return this; 
	}

	private Set<IInterface> implementsInterfaces;
	@Override
	public Set<IInterface> getImplementsInterfaces() { return implementsInterfaces; }
	public ClassImpl implementsInterface(IInterface implementsInterface) {
		if (implementsInterfaces == null)
			implementsInterfaces = new TreeSet<IInterface>();
		implementsInterfaces.add(implementsInterface);
		return this;
	}

	private Set<IAnnotation> annotations;
	@Override
	public Set<IAnnotation> getAnnotations() { return annotations; }
	public ClassImpl addAnnotation(IAnnotation annotation) {
		if (annotations == null)
			annotations = new TreeSet<IAnnotation>();
		annotations.add(annotation);
		return this;
	}

	Object unwrap;
	@Override
	public Object getUnwrap() { return (unwrap==null) ? this : unwrap; }
	public ClassImpl wrap(Object wrap) { this.unwrap = wrap; return this; }

	@Override
	public ClassImpl addDependency(Dependency dependency) {
		return (ClassImpl) super.addDependency(dependency);
	}
}
