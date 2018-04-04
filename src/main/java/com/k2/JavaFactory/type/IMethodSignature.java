package com.k2.JavaFactory.type;

import java.util.List;
import java.util.Set;

public interface IMethodSignature extends Comparable<IMethodSignature>{
	
	public Visibility getVisibility();
	public IType getReturnType();
	public String getReturnTypeDescription();
	public String getName();
	public String getTitle();
	public String getDescription();
	public boolean getIncludeJavaDoc();
	public List<IParameter> getParameters();
	public Set<IAnnotation> getAnnotations();
	public boolean getHasParameters();
	public int getParameterCount();
	public IParameter getParameter(String name);
}
