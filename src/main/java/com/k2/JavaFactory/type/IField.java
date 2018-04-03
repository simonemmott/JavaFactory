package com.k2.JavaFactory.type;

import java.util.Set;

import com.k2.Util.classes.Dependency;

public interface IField extends Comparable<IField>{

	public IType getDeclaringType();
	public Visibility getVisibility();
	public IType getJavaType();
	public String getName();
	public String getTitle();
	public String getDescription();
	public default String getFullName() { return getDeclaringType().getBasename()+":"+getName(); }
	public default String getCanonicalName() { return getDeclaringType().getName()+":"+getName(); }
	public boolean getIncludeJavaDoc();
	public boolean getIncludeGetter();
	public boolean getIncludeSetter();
	public Set<IAnnotation> getAnnotations();
	public Object getUnwrap();

}
