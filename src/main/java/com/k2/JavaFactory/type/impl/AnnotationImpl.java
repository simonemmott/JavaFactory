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

/**
 * A basic implementation of the IAnnotation interface
 * 
 * @author simon
 *
 */
public class AnnotationImpl extends TypeImpl implements IAnnotation {
	
	public AnnotationImpl(String name) { 
		super(name); 
	}

	private boolean inline = false;
	@Override
	public boolean getInline() { return inline; }
	/**
	 * Make this annotation inline
	 * @return	This annotation
	 */
	public AnnotationImpl inline() { inline=true; return this; }

	@Override
	public AnnotationImpl add(Dependency dependency) {
		return (AnnotationImpl) super.add(dependency);
	}


	List<IParameterValue> values;
	@Override
	public List<IParameterValue> getParameterValues() { return (values==null) ? new ArrayList<IParameterValue>(0) : values; }
	/**
	 * Set the value of the given parameter
	 * @param parameter	The parameter to set
	 * @param value	The value for the parameter
	 * @return	This annotation for method chaining
	 */
	public AnnotationImpl set(IParameter parameter, Object value) {
		if (values == null)
			values = new ArrayList<IParameterValue>();
		values.add(new ParameterValueImpl(parameter, value));
		return this;
	}
	/**
	 * Set the value of the named parameter
	 * @param name	The name of the parameter to set
	 * @param value	The value to set on the parameter
	 * @return	This annotation for method chaining
	 */
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
	/**
	 * Add the given parameter to the annotation
	 * @param parameter	The new parameter for the annotation
	 * @return	This annotation for method chaining
	 */
	public AnnotationImpl add(IParameter parameter) {
		parameters.put(parameter.getName(), parameter);
		return this;
	}
	/**
	 * Define a new parameter as a parameter of this annotation with the given values
	 * @param type	The type of the parameter
	 * @param name	The name of the parameter
	 * @param isVarArgs	True is the parameter is an Array of values
	 * @return	THe newly created parameter for method chaining
	 */
	public ParameterImpl define(IType type, String name, boolean isVarArgs) {
		ParameterImpl parm = new ParameterImpl(this, type, name);
		if (isVarArgs)
			parm.varArgs();
		add(parm);
		return parm;
	}
}
