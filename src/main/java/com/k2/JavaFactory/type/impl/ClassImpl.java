package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IField;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethod;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.Dependency;

public class ClassImpl extends TypeImpl implements IClass {
	
	public ClassImpl(String name) { super(name); }

	@Override public ClassImpl setAuthor(String author) { super.setAuthor(author); return this; }
	@Override public ClassImpl setTitle(String title) { super.setTitle(title); return this; }
	@Override public ClassImpl setDescription(String description) { super.setDescription(description); return this; }
	@Override public ClassImpl setIncludeJavaDoc(Boolean includeJavaDoc) { super.setIncludeJavaDoc(includeJavaDoc); return this; }
	@Override public ClassImpl annotate(IAnnotation annotation) { super.annotate(annotation); return this; }
	@Override public ClassImpl wrap(Object wrap) { super.wrap(wrap); return this; }
	@Override public ClassImpl add(Dependency dependency) { super.add(dependency); return this; }
	
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
	public ClassImpl extendsClass(String className) { 
		return extendsClass(new ClassImpl(className)); 
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
	public ClassImpl implementsInterface(String interfaceName) {
		return implementsInterface(new InterfaceImpl(interfaceName));
	}

	private final Set<IField> fields = new TreeSet<IField>();
	public Set<IField> getFields() { return fields; }
	public FieldImpl defineField(Visibility visibility, IType javaType, String name) {
		FieldImpl field = new FieldImpl(this, visibility, javaType, name);
		fields.add(field);
		return field;
	}
	public FieldImpl defineField(IType javaType, String name) {
		FieldImpl field = new FieldImpl(this, javaType, name);
		fields.add(field);
		return field;
	}

	private final Set<IMethod> methods = new TreeSet<IMethod>();
	public Set<IMethod> getMethods() { return methods; }
	public MethodImpl defineMethod(Visibility visibility, IType javaType, String name) {
		MethodImpl method = new MethodImpl(this, visibility, javaType, name);
		methods.add(method);
		return method;
	}
	public MethodImpl defineMethod(IType javaType, String name) {
		MethodImpl method = new MethodImpl(this, javaType, name);
		methods.add(method);
		return method;
	}
	public MethodImpl defineMethod(String name) {
		MethodImpl method = new MethodImpl(this, name);
		methods.add(method);
		return method;
	}
	public MethodImpl defineMethod(Visibility visibility, String name) {
		MethodImpl method = new MethodImpl(this, visibility, name);
		methods.add(method);
		return method;
	}

	
}
