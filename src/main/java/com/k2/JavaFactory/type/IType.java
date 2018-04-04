package com.k2.JavaFactory.type;

import java.util.List;
import java.util.Set;

import com.k2.Util.classes.Dependency;

public interface IType extends Comparable<IType>{

	public String getPackageName();
	public String getBasename();
	public String getName();
	public String getAuthor();
	public String getTitle();
	public String getDescription();
	public boolean getIncludeJavaDoc();
	public Set<IAnnotation> getAnnotations();
	public Set<Dependency> getDependencies();
	public List<IType> getDeclaredTypes();
	public IType getDeclaringType();
	public IType setDeclaringType(IType declaringType);
	public Object getUnwrap();

}
