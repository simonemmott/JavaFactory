package com.k2.JavaFactory.type.impl;

import com.k2.JavaFactory.type.IParameter;
import com.k2.JavaFactory.type.IParameterValue;

/**
 * A basic implementation of the IParameterValue interface
 * 
 * @author simon
 *
 */
public class ParameterValueImpl implements IParameterValue {

	private IParameter parameter;
	private Object value;
	
	/**
	 * Create a parameter value for the given parameter
	 * @param parameter	The parameter being valued
	 * @param value		The value of the parameter
	 */
	public ParameterValueImpl(IParameter parameter, Object value) {
		this.parameter = parameter;
		this.value = value;
	}
	
	@Override
	public IParameter getParameter() { return parameter; }

	@Override
	public Object getValue() { return value; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parameter == null) ? 0 : parameter.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParameterValueImpl other = (ParameterValueImpl) obj;
		if (parameter == null) {
			if (other.parameter != null)
				return false;
		} else if (!parameter.equals(other.parameter))
			return false;
		return true;
	}
	

	
	

}
