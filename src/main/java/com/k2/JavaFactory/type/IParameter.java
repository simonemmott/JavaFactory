package com.k2.JavaFactory.type;

import java.util.Set;

/**
 * This interface defines a parameter in java source code
 * 
 * @author simon
 *
 */
public interface IParameter{

	/**
	 * @return	The definition of the data type of this parameter
	 */
	public IType getType();
	
	/**
	 * @return	The name of the parameter
	 */
	public String getName();
	
	/**
	 * @return	True if this parameter is a varArg or Array parameter
	 */
	public boolean getIsVarArgs();
	
	/**
	 * @return	The set of annotations for the parameter. If this parameter is not annotated then an empty set is returned
	 */
	public Set<IAnnotation> getAnnotations();
	
	/**
	 * @return	The title of this parameter. This value should be used when identifying this parameter to the user
	 */
	public String getTitle();
	
	/**
	 * @return	The description of this parameter. The value should be used in contextual help about this parameter
	 */
	public String getDescription();

}
