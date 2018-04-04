package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IEnum;
import com.k2.JavaFactory.type.IEnumValue;
import com.k2.JavaFactory.type.IField;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethod;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.Dependency;

public class EnumImpl extends TypeImpl implements IEnum {
	
	public EnumImpl(String name) { super(name); }

	@Override public EnumImpl setAuthor(String author) { super.setAuthor(author); return this; }
	@Override public EnumImpl setTitle(String title) { super.setTitle(title); return this; }
	@Override public EnumImpl setDescription(String description) { super.setDescription(description); return this; }
	@Override public EnumImpl setIncludeJavaDoc(Boolean includeJavaDoc) { super.setIncludeJavaDoc(includeJavaDoc); return this; }
	@Override public EnumImpl annotate(IAnnotation annotation) { super.annotate(annotation); return this; }
	@Override public EnumImpl wrap(Object wrap) { super.wrap(wrap); return this; }
	@Override public EnumImpl add(Dependency dependency) { super.add(dependency); return this; }
	
	private Visibility visibility;
	@Override
	public Visibility getVisibility() { return visibility; }
	public EnumImpl visibility(Visibility visibility) {
		this.visibility = visibility;
		return this;
	}

	private Set<IInterface> implementsInterfaces;
	@Override
	public Set<IInterface> getImplementsInterfaces() { return implementsInterfaces; }
	public EnumImpl implementsInterface(IInterface implementsInterface) {
		if (implementsInterfaces == null)
			implementsInterfaces = new TreeSet<IInterface>();
		implementsInterfaces.add(implementsInterface);
		return this;
	}
	public EnumImpl implementsInterface(String interfaceName) {
		return implementsInterface(new InterfaceImpl(interfaceName));
	}

	private final Set<IEnumValue> values = new TreeSet<IEnumValue>();
	@Override
	public Set<IEnumValue> getValues() { return values; }
	public EnumValueImpl defineValue(String name) {
		EnumValueImpl value = new EnumValueImpl(this, name);
		values.add(value);
		return value;
	}


	
}
