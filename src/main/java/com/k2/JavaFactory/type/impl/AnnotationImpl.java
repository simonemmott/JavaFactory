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
	
	public AnnotationImpl(String name) { 
		super(name); 
	}

	private boolean inline = false;
	@Override
	public boolean getInline() { return inline; }
	public AnnotationImpl inline() { inline=true; return this; }

	@Override
	public AnnotationImpl add(Dependency dependency) {
		return (AnnotationImpl) super.add(dependency);
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


	@Override
	public boolean getHasParameters() { return parameters.size() > 0; }
	
	@Override 
	public int getParameterCount() { return parameters.size(); }
	
	private final Map<String,IParameter> parameters = new HashMap<String, IParameter>();
	@Override
	public IParameter getParameter(String name) {
		return parameters.get(name);
	}
	public AnnotationImpl add(IParameter parameter) {
		parameters.put(parameter.getName(), parameter);
		return this;
	}
	public ParameterImpl define(IType type, String name, boolean isVarArgs) {
		ParameterImpl parm = new ParameterImpl(this, type, name);
		if (isVarArgs)
			parm.varArgs();
		add(parm);
		return parm;
	}
}
