package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IField;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.ClassUtil;
import com.k2.Util.classes.Dependencies;
import com.k2.Util.classes.Dependency;

public class FieldImpl implements IField {
	
	public FieldImpl(IClass declaringType, Visibility visibility, IType javaType, String name) { 
		this.declaringType = declaringType; 
		this.visibility = visibility; 
		this.javaType = javaType; 
		this.name = name; 
	}
	
	public FieldImpl(IClass declaringType, IType javaType, String name) { 
		this.declaringType = declaringType; 
		this.visibility = Visibility.PRIVATE; 
		this.javaType = javaType; 
		this.name = name; 
	}
	
	protected final IClass declaringType;
	@Override public IType getDeclaringType() { return declaringType; }
	protected final Visibility visibility;
	@Override public Visibility getVisibility() { return visibility; }
	protected final IType javaType;
	@Override public IType getJavaType() { return javaType; }
	protected final String name;
	@Override public String getName() { return name; }

	private String title;
	@Override
	public String getTitle() { return title; }
	public FieldImpl setTitle(String title) {
		this.title = title;
		return this;
	}
	
	private String description;
	@Override
	public String getDescription() { return description; }
	public FieldImpl setDescription(String description) {
		this.description = description;
		return this;
	}
	
	private Boolean includeJavaDoc;
	@Override
	public boolean getIncludeJavaDoc() { return (includeJavaDoc==null) ? (StringUtil.isSet(title) || StringUtil.isSet(description)) : includeJavaDoc; }
	public FieldImpl setIncludeJavaDoc(Boolean includeJavaDoc) {
		this.includeJavaDoc = includeJavaDoc;
		return this;
	}
	
	private Boolean includeGetter;
	@Override
	public boolean getIncludeGetter() { return (includeGetter==null) ? true: includeGetter; }
	public FieldImpl setIncludeGetter(Boolean includeGetter) {
		this.includeGetter = includeGetter;
		return this;
	}
	
	private Boolean includeSetter;
	@Override
	public boolean getIncludeSetter() { return (includeSetter==null) ? true : includeSetter; }
	public FieldImpl setIncludeSetter(Boolean includeSetter) {
		this.includeSetter = includeSetter;
		return this;
	}
	
	protected Set<IAnnotation> annotations;
	@Override
	public Set<IAnnotation> getAnnotations() { return annotations; }
	public FieldImpl annotate(IAnnotation annotation) {
		if (annotations == null)
			annotations = new TreeSet<IAnnotation>();
		annotations.add(annotation);
		return this;
	}

	protected Object unwrap;
	@Override
	public Object getUnwrap() { return (unwrap==null) ? this : unwrap; }
	public FieldImpl wrap(Object wrap) { this.unwrap = wrap; return this; }


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
		FieldImpl other = (FieldImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(IField o) { return getCanonicalName().compareTo(o.getCanonicalName()); }

	@SuppressWarnings("unchecked")
	public <C extends IClass> C up(Class<C> cls) {
		return (C)declaringType;
	}

	
	

}
