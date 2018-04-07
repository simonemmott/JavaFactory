package com.k2.JavaFactory.type;

/**
 * This interface defines a value given to a parameter in java source
 * 
 * @author simon
 *
 */
public interface IParameterValue{

	/**
	 * @return	The definition of the parameter the is being set
	 */
	public IParameter getParameter();
	
	/**
	 * @return	The value set on the parameter
	 */
	public Object getValue();
}
