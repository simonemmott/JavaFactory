package com.k2.JavaFactory.type;

import java.util.Set;


/**
 * This interface represents the source code for a field on a java class
 * 
 * @author simon
 *
 */
public interface IField extends Comparable<IField>{

	/**
	 * @return	The the definition of the class declaring this field
	 */
	public IClass getDeclaringClass();
	
	/**
	 * @return	The visibility of the field
	 */
	public Visibility getVisibility();
	
	/**
	 * @return	The java type of the field
	 */
	public IType getJavaType();
	
	/**
	 * @return	The name of the field
	 */
	public String getName();
	
	/**
	 * @return	The title of the field. This value should be used to identify the field to the user
	 */
	public String getTitle();
	
	/**
	 * @return	The description of the field. This value should be used to describe the field in contextual help
	 */
	public String getDescription();
	
	/**
	 * @return	A short identification of this field for logging and other internal uses
	 */
	public default String getFullName() { return getDeclaringClass().getBasename()+":"+getName(); }
	
	/**
	 * @return	A long string uniquely identifying this field for logging and other internal uses
	 */
	public default String getCanonicalName() { return getDeclaringClass().getName()+":"+getName(); }
	
	/**
	 * @return	True if the javadoc should be included with this field
	 */
	public boolean getIncludeJavaDoc();
	
	/**
	 * @return	True if this field should included a getter to get values for this field
	 */
	public boolean getIncludeGetter();
	
	/**
	 * @return	True if this field should include a setter to set values for this field
	 */
	public boolean getIncludeSetter();
	
	/**
	 * @return	The annotations on this field. If there are no annotations then an empty set is returned
	 */
	public Set<IAnnotation> getAnnotations();
	
	/**
	 * @return If this interface has been implemented through an adapter or similar this method returns the underlying object. Allowing embedded wigets access to all the methods of the underlying object.
	 */
	public Object getUnwrap();

}
