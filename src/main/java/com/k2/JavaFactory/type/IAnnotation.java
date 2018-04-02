package com.k2.JavaFactory.type;

import java.util.List;

public interface IAnnotation extends IType{

	public IParameter getParameter(String name);
	public List<IParameterValue> getParameterValues();
}
