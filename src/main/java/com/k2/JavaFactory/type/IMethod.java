package com.k2.JavaFactory.type;

import java.util.Set;

import com.k2.Util.classes.Dependency;

/**
 * This interface defines a method in java source
 * 
 * @author simon
 *
 */
public interface IMethod extends IMethodSignature{
	
	/**
	 * @return	The definition of the type declaring this method signature
	 */
	public IClass getDeclaringClass();
	
	/**
	 * @return	The string representing the java source of the method body. 
	 */
	public String getMethodBody();
	
	/**
	 * @return	The set of dependencies for the body of this method
	 */
	public Set<Dependency> getDependencies();
	
	/**
	 * @return If this interface has been implemented through an adapter or similar this method returns the underlying object. Allowing embedded wigets access to all the methods of the underlying object.
	 */
	public Object getUnwrap();
	
	/**
	 * @return	A short identification of this method for logging and other internal uses
	 */
	public default String getFullName() { return getDeclaringClass().getBasename()+"::"+getName()+"()"; }
	
	/**
	 * @return	A long string uniquely identifying this method for logging and other internal uses
	 */
	public default String getCanonicalName() { return getDeclaringClass().getName()+"::"+getName()+"()"; }
}
