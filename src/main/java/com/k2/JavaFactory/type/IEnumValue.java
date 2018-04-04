package com.k2.JavaFactory.type;

import java.util.Set;

import com.k2.Util.classes.Dependency;

public interface IEnumValue extends Comparable<IEnumValue>{

	public IEnum getDeclaringEnum();
	public String getName();
	public String getTitle();
	public String getDescription();
	public default String getFullName() { return getDeclaringEnum().getBasename()+"."+getName(); }
	public default String getCanonicalName() { return getDeclaringEnum().getName()+"."+getName(); }
	public boolean getIncludeJavaDoc();
	public Set<IAnnotation> getAnnotations();
	public Object getUnwrap();

}
