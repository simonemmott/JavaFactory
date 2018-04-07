package com.k2.JavaFactory.type;

import java.util.List;
import java.util.Set;

/**
 * This interface defines the java source for a  method signature
 * 
 * @author simon
 *
 */
public interface IMethodSignature extends Comparable<IMethodSignature>{
	
	/**
	 * @return	The visibility of the method
	 */
	public Visibility getVisibility();
	
	/**
	 * @return	The definition of the return type of this method
	 */
	public IType getReturnType();
	
	/**
	 * @return	The description of the return type of this method. This should be included in contextual help about this method
	 */
	public String getReturnTypeDescription();
	
	/**
	 * @return	The name of the method
	 */
	public String getName();
	
	/**	
	 * @return	The title of the method. This should be used when identifying this method to the user
	 */
	public String getTitle();
	
	/**
	 * @return	The description of the method. This should be used when describing this method through contextual help
	 */
	public String getDescription();
	
	/**
	 * @return	True if javadoc should be included in the java source
	 */
	public boolean getIncludeJavaDoc();
	
	/**
	 * @return	The list of parameters required by this method. If there are no parameters for the method then an empty list is returned
	 */
	public List<IParameter> getParameters();
	
	/**
	 * @return	The set of annotations of this method. If there are no annotations then this method will return an empty set.
	 */
	public Set<IAnnotation> getAnnotations();
	
	/**
	 * @return True if this methods requires parameter values
	 */
	public boolean getHasParameters();
	
	/**
	 * @return	The number of parameters values required by this method
	 */
	public int getParameterCount();
	
	/**
	 * @param name	The name of the parameter to get
	 * @return	The definition of the parameter identified by the given name. If the named parameter does not exist a null value is returned
	 */
	public IParameter getParameter(String name);
}
