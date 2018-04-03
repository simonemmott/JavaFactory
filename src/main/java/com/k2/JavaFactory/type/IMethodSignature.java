package com.k2.JavaFactory.type;

import java.util.List;
import java.util.Set;

public interface IMethodSignature extends Comparable<IMethodSignature>{
	
	/**
	 * 
	 * @return
	 */
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
	/**
	 * 
	 * @param name
	 * @return
	 */
	public IParameter getParameter(String name);
}
