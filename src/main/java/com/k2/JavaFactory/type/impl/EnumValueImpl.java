package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IEnum;
import com.k2.JavaFactory.type.IEnumValue;
import com.k2.JavaFactory.type.IField;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.ClassUtil;
import com.k2.Util.classes.Dependencies;
import com.k2.Util.classes.Dependency;

public class EnumValueImpl implements IEnumValue, Comparable<IEnumValue> {
	
	public EnumValueImpl(IEnum declaringEnum, String name) { 
		this.declaringEnum = declaringEnum; 
		this.name = name; 
	}
		
	protected final IEnum declaringEnum;
	@Override public IEnum getDeclaringEnum() { return declaringEnum; }
	protected final String name;
	@Override public String getName() { return name; }

	private String title;
	@Override
	public String getTitle() { return title; }
	public EnumValueImpl setTitle(String title) {
		this.title = title;
		return this;
	}
	
	private String description;
	@Override
	public String getDescription() { return description; }
	public EnumValueImpl setDescription(String description) {
		this.description = description;
		return this;
	}
	
	private Boolean includeJavaDoc;
	@Override
	public boolean getIncludeJavaDoc() { return (includeJavaDoc==null) ? (StringUtil.isSet(title) || StringUtil.isSet(description)) : includeJavaDoc; }
	public EnumValueImpl setIncludeJavaDoc(Boolean includeJavaDoc) {
		this.includeJavaDoc = includeJavaDoc;
		return this;
	}
	
	protected Set<IAnnotation> annotations;
	@Override
	public Set<IAnnotation> getAnnotations() { return annotations; }
	public EnumValueImpl annotate(IAnnotation annotation) {
		if (annotations == null)
			annotations = new TreeSet<IAnnotation>();
		annotations.add(annotation);
		return this;
	}

	protected Object unwrap;
	@Override
	public Object getUnwrap() { return (unwrap==null) ? this : unwrap; }
	public EnumValueImpl wrap(Object wrap) { this.unwrap = wrap; return this; }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnumValueImpl other = (EnumValueImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(IEnumValue o) { return getCanonicalName().compareTo(o.getCanonicalName()); }

	@SuppressWarnings("unchecked")
	public <E extends IEnum> E up(Class<E> enumarationClass) {
		return (E)declaringEnum;
	}

	
	

}
