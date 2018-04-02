package com.k2.JavaFactory.type;

import java.util.Set;

public interface IClass extends IType{

	public Visibility getVisibility();
	public IClass getExtendsClass();
	public Set<IInterface> getImplementsInterfaces();
	public Set<IAnnotation> getAnnotations();
	public Object getUnwrap();

}
