package com.k2.JavaFactory.type;

import java.util.List;
import java.util.Set;

import com.k2.Util.classes.Dependency;

public interface IMethod extends IMethodSignature{
	
	public IType getDeclaringType();
	public String getMethodBody();
	public Set<Dependency> getDependencies();
	public Object getUnwrap();
	public default String getFullName() { return getDeclaringType().getBasename()+"::"+getName()+"()"; }
	public default String getCanonicalName() { return getDeclaringType().getName()+"::"+getName()+"()"; }
}
