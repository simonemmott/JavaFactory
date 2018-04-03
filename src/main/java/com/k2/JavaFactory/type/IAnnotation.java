package com.k2.JavaFactory.type;

import java.util.List;

import com.k2.JavaFactory.type.impl.AnnotationImpl;
import com.k2.JavaFactory.type.impl.ClassImpl;
import com.k2.JavaFactory.type.impl.ParameterImpl;
import com.k2.JavaFactory.type.impl.TypeImpl;
import com.k2.JavaFactory.type.wigetModel.ParameterContainer;

public interface IAnnotation extends IType, ParameterContainer {

	public IParameter getParameter(String name);
	public boolean getInline();
	public List<IParameterValue> getParameterValues();
}
