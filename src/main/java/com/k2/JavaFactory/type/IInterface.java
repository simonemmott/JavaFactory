package com.k2.JavaFactory.type;

import java.util.Set;

/**
 * This interface defines an interface in java source code
 * 
 * @author simon
 *
 */
public interface IInterface extends IType{

	/**
	 * @return	The set of interfaces extended by this interface. If this interface does not extend any other interfaces then an empty set is returned
	 */
	public Set<IInterface> getExtendsInterfaces();
	
	/**
	 * @return	The set of methods signatures implemented by this interface. If this interface does not define any method signatures then an empty set is returned
	 */
	public Set<IMethodSignature> getMethods();
}
