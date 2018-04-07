package com.k2.JavaFactory.type;

import java.util.List;

import com.k2.JavaFactory.type.wigetModel.ParameterContainer;

/**
 * This interface represents an annotation in java code
 * @author simon
 *
 */
public interface IAnnotation extends IType, ParameterContainer {

	/**
	 * Get the parameter for the given name
	 * @param name	The name of the parameter to get
	 * @return	The parameter identified by the given name
	 */
	public IParameter getParameter(String name);
	
	/**
	 * @return	True if the annotation should be output in-line
	 */
	public boolean getInline();
	
	/**
	 * @return	The list of parameter values set on this annotation
	 */
	public List<IParameterValue> getParameterValues();
}
