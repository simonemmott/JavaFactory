package com.k2.JavaFactory.type;

import java.util.Set;

/**
 * This interface defines the source code for a java class
 * @author simon
 *
 */
public interface IClass extends IType{

	/**
	 * @return	The visibility of the java class
	 */
	public Visibility getVisibility();
	
	/**
	 * @return	The definition of the class that this class extends. Null if this class does not extend another class
	 */
	public IClass getExtendsClass();
	
	/**
	 * @return	The set of interfaces implemented by this class. If this class does not implement any interfaces then an empty set is returned
	 */
	public Set<IInterface> getImplementsInterfaces();
	
	/**
	 * @return	The set of fields defined by this class. If this class does not define any fields then an empty set is returned
	 */
	public Set<IField> getFields();
	
	/**
	 * @return	The set of methods defined by this class. If this class does not define any methods then an empty set is returned
	 */
	public Set<IMethod> getMethods();

}
