package com.k2.JavaFactory.type;

import java.util.Set;

public interface IInterface extends IType{

	public Set<IInterface> getExtendsInterfaces();
	public Set<IMethodSignature> getMethods();
}
