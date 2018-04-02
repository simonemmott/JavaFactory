package com.k2.JavaFactory.type.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.k2.JavaFactory.JavaFactoryError;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IParameter;
import com.k2.JavaFactory.type.IParameterValue;
import com.k2.JavaFactory.type.IType;
import com.k2.Util.classes.Dependency;

public class AnnotationImpl extends TypeImpl implements IAnnotation {
	
	public AnnotationImpl(String name) { super(name); }


	@Override
	public AnnotationImpl addDependency(Dependency dependency) {
		return (AnnotationImpl) super.addDependency(dependency);
	}


	List<IParameterValue> values;
	@Override
	public List<IParameterValue> getParameterValues() { return values; }
	public AnnotationImpl set(IParameter parameter, Object value) {
		if (values == null)
			values = new ArrayList<IParameterValue>();
		values.add(new ParameterValueImpl(parameter, value));
		return this;
	}
	public AnnotationImpl set(String name, Object value) {
		if (values == null)
			values = new ArrayList<IParameterValue>();
		IParameter parm = getParameter(name);
		if (parm == null)
			throw new JavaFactoryError("No parameter with name {} defined for annotation {}", name, this.name);
		values.add(new ParameterValueImpl(parm, value));
		return this;
	}


	private Map<String,IParameter> parameters;
	@Override
	public IParameter getParameter(String name) {
		if (parameters == null)
			return null;
		return parameters.get(name);
	}
	public AnnotationImpl add(IParameter parameter) {
		if (parameters == null)
			parameters = new HashMap<String, IParameter>();
		parameters.put(parameter.getName(), parameter);
		return this;
	}
	public AnnotationImpl define(IType type, String name, boolean isVarArgs) {
		if (isVarArgs)
			add(new ParameterImpl(this, type, name).varArgs());
		else
			add(new ParameterImpl(this, type, name));
		return this;
	}
}
