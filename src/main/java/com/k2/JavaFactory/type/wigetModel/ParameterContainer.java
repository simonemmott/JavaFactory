package com.k2.JavaFactory.type.wigetModel;

/**
 * This interface defines the methods required to define java source that requires parameters
 * 
 * @author simon
 *
 */
public interface ParameterContainer {

	/**
	 * Parameter containers must implement hashCode and Equals
	 * @return	The int hashCode for this instance
	 */
	public int hashCode();

	/**
	 * Parameter containers must implement hashCode and Equals
	 * @param obj	The object to compare this object to for equality
	 * @return	True if this parameter container is equal to the given object
	 */
	public boolean equals(Object obj);
	
	/**
	 * @return	True if this parameter container has parameters
	 */
	public boolean getHasParameters();
	
	/**
	 * @return	The number of parameters required by this parameter container
	 */
	public int getParameterCount();
}
