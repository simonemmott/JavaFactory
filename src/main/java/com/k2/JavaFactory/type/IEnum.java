package com.k2.JavaFactory.type;

import java.util.Set;

public interface IEnum extends IType{

	public Visibility getVisibility();
	public Set<IInterface> getImplementsInterfaces();
	public Set<IEnumValue> getValues();

}
