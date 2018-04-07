package com.k2.JavaFactory.type;

import java.util.Set;

/**
 * This interface defines the source code for an java enumeration
 * 
 * @author simon
 *
 */
public interface IEnum extends IType{

	/**
	 * @return	The visibility of the enumeration
	 */
	public Visibility getVisibility();
	
	/**
	 * @return	The set of interfaces implemented by the enumeration. If this enumeration does not implement any interfaces then an empty set is returned
	 */
	public Set<IInterface> getImplementsInterfaces();
	
	/**
	 * @return	The set of values in this enumeration
	 */
	public Set<IEnumValue> getValues();

}
