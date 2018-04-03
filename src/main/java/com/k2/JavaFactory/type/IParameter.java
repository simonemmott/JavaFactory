package com.k2.JavaFactory.type;

import java.util.Set;

public interface IParameter{

	public IType getType();
	public String getName();
	public boolean getIsVarArgs();
	public Set<IAnnotation> getAnnotations();
	public String getTitle();
	public String getDescription();

}
