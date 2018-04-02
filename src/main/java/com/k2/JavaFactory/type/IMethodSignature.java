package com.k2.JavaFactory.type;

import java.util.List;
import java.util.Set;

public interface IMethodSignature{
	
	public Visibility getVisibility();
	public IType getReturnsType();
	public String getName();
	public List<IParameter> getParameters();
	public Set<IAnnotation> getAnnotations();
}
